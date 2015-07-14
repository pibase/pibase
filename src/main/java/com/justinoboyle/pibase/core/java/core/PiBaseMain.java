package com.justinoboyle.pibase.core.java.core;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

import net.xeoh.plugins.base.PluginManager;
import net.xeoh.plugins.base.impl.PluginManagerFactory;
import net.xeoh.plugins.base.util.PluginManagerUtil;

import com.justinoboyle.pibase.backend.java.listener.BackendServerListener;
import com.justinoboyle.pibase.core.commands.CommandInterpreter;
import com.justinoboyle.pibase.core.java.plugin.PiPlugin;
import com.justinoboyle.pibase.core.java.plugin.PiPluginManager;
import com.justinoboyle.pibase.core.java.util.JarCopyUtil;
import com.justinoboyle.pibase.core.webserver.java.WebServer;
import com.justinoboyle.pibase.frontend.java.listener.FrontendServerListener;

public class PiBaseMain {
    
    private static WebServer frontend, backend;
    
    private static boolean shouldStayAlive = true;
    
    public static Collection<PiPlugin> plugins = null;

    public static void main(String[] args) {
        File fi = new File("./plugins/");
        fi.mkdirs();
        PluginManager pm = PluginManagerFactory.createPluginManager();
        pm.addPluginsFrom(fi.toURI());
        
        plugins = new PluginManagerUtil(pm).getPlugins(PiPlugin.class);
        
        for(PiPlugin p : plugins) {
            p.onEnable();
            try {
                PiPluginManager.extract(p);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
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
