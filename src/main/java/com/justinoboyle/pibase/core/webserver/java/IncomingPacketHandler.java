package com.justinoboyle.pibase.core.webserver.java;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

import com.justinoboyle.pibase.core.java.server.packet.PacketIncoming;
import com.justinoboyle.pibase.core.java.server.packet.PacketOutgoing;
import com.justinoboyle.pibase.core.java.util.StatusCode;

public class IncomingPacketHandler extends Thread {
    private Socket client;
    private BufferedReader inFromClient = null;
    private DataOutputStream outToClient = null;
    private WebServerThread ws = null;

    public IncomingPacketHandler(Socket c, WebServerThread w) {
        this.client = c;
        this.ws = w;
    }
    
    public void run() {
        try {
            inFromClient = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
            outToClient = new DataOutputStream(this.client.getOutputStream());
            String requestString = this.inFromClient.readLine();
            String headerLine = requestString;
            StringTokenizer tokenizer = new StringTokenizer(headerLine);
            String httpMethod = tokenizer.nextToken();
            String httpQueryString = tokenizer.nextToken();
            while (this.inFromClient.ready())
                requestString = this.inFromClient.readLine();

            PacketIncoming incoming = new PacketIncoming(client.getInetAddress(), httpMethod, httpQueryString);
            PacketOutgoing outgoing = ws.handler.listener.listen(incoming);
            sendResponse(outgoing.getStatusCode(), outgoing.getResponseString(), outgoing.isFile());
            
            return;
        } catch (Exception localException) {
        }
    }

    public void sendResponse(StatusCode statusCode, String responseString, boolean isFile) throws Exception {
        String statusLine = statusCode.getResponseText();
        String serverdetails = "Server: pibase Raspberry Pi";
        String contentLengthLine = null;
        String fileName = null;
        String contentTypeLine = "Content-Type: text/html\r\n";
        FileInputStream fin = null;
        if (isFile) {
            fileName = responseString;
            fin = new FileInputStream(fileName);
            contentLengthLine = "Content-Length: " + Integer.toString(fin.available()) + "\r\n";
            if ((!fileName.endsWith(".htm")) && (!fileName.endsWith(".html"))) { // TODO better content type management
                contentTypeLine = "Content-Type: application/zip\r\n";
            }
        } else {
            contentLengthLine = "Content-Length: " + responseString.length() + "\r\n";
        }
        if (!this.client.isClosed()) {
            outToClient.writeBytes(statusLine);
            outToClient.writeBytes(serverdetails);
            outToClient.writeBytes(contentTypeLine);
            outToClient.writeBytes(contentLengthLine);
            outToClient.writeBytes("Connection: close\r\n");
            outToClient.writeBytes("\r\n");
            if (isFile) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fin.read(buffer)) != -1) {
                    this.outToClient.write(buffer, 0, bytesRead);
                }
                fin.close();
            } else {
                this.outToClient.writeBytes(responseString);
            }
            this.outToClient.close();
        }
    }
}