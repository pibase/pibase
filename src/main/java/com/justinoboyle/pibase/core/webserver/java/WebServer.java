package com.justinoboyle.pibase.core.webserver.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.URL;
import java.net.UnknownHostException;

import com.justinoboyle.pibase.core.java.server.listener.ServerRequestListener;

public class WebServer {

    ServerSocket socket = null;
    private WebServerThread serverThread = null;
    public String ip = "";
    public ServerRequestListener listener;
    public final int port;
    
    public WebServer(int port, ServerRequestListener listener) {
        this.listener = listener;
        this.port = port;
    }

    public boolean checkServer(String ip) {
        try {
            URL u = new URL("http://" + ip + ":" + port + "/ping");
            HttpURLConnection huc = (HttpURLConnection) u.openConnection();
            huc.setRequestMethod("GET");
            huc.connect();
            if (huc.getResponseCode() == 222) {
                return true;
            }
        } catch (IOException localIOException) {
        }
        return false;
    }

    public void disable() {
        try {
            serverThread.interrupt();
            serverThread.running = false;
            serverThread = null;
            this.socket.close();
        } catch (IOException localIOException) {

        }
    }

    public String getExternalIP() {
        String i = "127.0.0.1";
        try {
            URL whatismyip = new URL("http://automation.whatismyip.com/n09230945.asp");
            BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
            i = in.readLine();
        } catch (Exception e) {
            return i;
        }
        return i;
    }

    public void enable() {
        setThread(new WebServerThread(this));
        serverThread.start();
        try {
            String i = getExternalIP();
            if ((i != "") && (checkServer(i))) {
                System.out.println("Got IP: " + InetAddress.getLocalHost().getHostAddress());
                System.out.println("Local network users only.");
                this.ip = InetAddress.getLocalHost().getHostAddress();
                return;
            }
        } catch (UnknownHostException localUnknownHostException) {
            try {
                if (checkServer(InetAddress.getLocalHost().getHostAddress())) {
                    System.out.println("Got IP: " + InetAddress.getLocalHost().getHostAddress());
                    System.out.println("Local network users only.");
                    this.ip = InetAddress.getLocalHost().getHostAddress();
                    return;
                }
            } catch (UnknownHostException localUnknownHostException1) {
                if (checkServer("localhost")) {
                    System.out.println("[Luna] Got IP: localhost");
                    System.out.println("[WARNING] Local usage only!");
                    this.ip = "localhost";
                    return;
                }
            }
        }
    }

    public WebServerThread getThread() {
        return serverThread;
    }

    public void setThread(WebServerThread ws) {
        this.serverThread = ws;
    }

}