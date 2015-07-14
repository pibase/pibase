package com.justinoboyle.pibase.core.java.core;

import java.util.Scanner;

import com.justinoboyle.pibase.backend.java.listener.BackendServerListener;
import com.justinoboyle.pibase.core.commands.CommandInterpreter;
import com.justinoboyle.pibase.core.webserver.java.WebServer;
import com.justinoboyle.pibase.frontend.java.listener.FrontendServerListener;

public class PiBaseMain {
    
    private static WebServer frontend, backend;
    
    private static boolean shouldStayAlive = true;

    public static void main(String[] args) {
        
        frontend = new WebServer(PiDefaults.FRONTEND_PORT, new FrontendServerListener());
        backend = new WebServer(PiDefaults.BACKEND_PORT, new BackendServerListener());
        
        //TODO move this garbage
        frontend.enable();
        backend.enable();
        Scanner sc = new Scanner(System.in);
        System.out.println("Starting up...");
        CommandInterpreter in = new CommandInterpreter();
        while(shouldStayAlive){
            shouldStayAlive = !in.interpret(sc);
        }
        System.out.println("Shutting down.");
        frontend.disable();
        backend.disable();
        
    }
    
}
