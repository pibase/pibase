package com.justinoboyle.pibase.core.webserver.java;

import java.net.InetAddress;
import java.net.ServerSocket;

public class WebServerThread extends Thread {
    public WebServer handler = null;
    boolean running = true;

    public WebServerThread(WebServer handler) {
        this.handler = handler;
    }

    public void run() {
        setup();
        while (running)
            try {
                watchForConnection();
            } catch (Exception ex) {
            }
    }

    private void setup() {
        try {
            handler.socket = new ServerSocket(handler.port, 10, InetAddress.getByName("0"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void watchForConnection() {
        try {
            new IncomingPacketHandler(handler.socket.accept(), this).start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
