/**
 * by Justin O'Boyle
 * www.justinoboyle.com
 * on Jul 18, 2015
 * @formatter-on
 */
package com.justinoboyle.pibase.core.java.core;

import com.justinoboyle.pibase.backend.java.listener.BackendServerListener;
import com.justinoboyle.pibase.core.commands.CommandInterpreter;
import com.justinoboyle.pibase.core.java.plugin.PiPlugin;
import com.justinoboyle.pibase.core.webserver.java.WebServer;
import com.justinoboyle.pibase.frontend.java.listener.FrontendServerListener;

import net.xeoh.plugins.base.annotations.PluginImplementation;

@PluginImplementation
public class CorePlugin implements PiPlugin {
    public static final String AUTHOR = "www.justinoboyle.com";

    private static WebServer frontend, backend;

    @Override
    public void onEnable() {
        backend = new WebServer(PiDefaults.BACKEND_PORT, new BackendServerListener());
        frontend = new WebServer(PiDefaults.FRONTEND_PORT, new FrontendServerListener());
        new Thread() {
            public void run() {
                System.out.println("Starting servers...");
                try {
                    frontend.enable();
                    backend.enable();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onDisable() {
        if (frontend != null)
            frontend.disable();
        if (backend != null)
            backend.disable();
    }

    @Override
    public boolean onCommand(String commandName, String[] args) {
        if(commandName.equalsIgnoreCase("print") || commandName.equalsIgnoreCase("print") || commandName.equalsIgnoreCase("=")) {
            for(String s : args) {
                System.out.print(s + " ");
            }
            System.out.print("\n");
            return true;
        }
        if(commandName.equalsIgnoreCase("drop")) {
            if(args.length == 0) {
                System.out.println("Cannot drop an empty variable");
                return true;
            }
            String varName = args[0].toLowerCase();
            if(CommandInterpreter.vars.containsKey(varName)) CommandInterpreter.vars.remove(varName);
            System.out.println("Done");
            return true;
        }
        if(commandName.equalsIgnoreCase("core")) {
            if(args.length == 0) {
                System.out.println("PiBase Core v" + PiBaseMain.currentVersion);
                return true;
            }
            boolean known = false;
            for(String s : args) {
                s = s.toLowerCase();
                switch(s) {
                    case "-v": {
                        known = true;
                        System.out.println(PiBaseMain.currentVersion);
                        break;
                    }
                    case "-plc": {
                        known = true;
                        System.out.println(PiBaseMain.plugins.size() + "");
                        break;
                    }
                    case "-pf": {
                        known = true;
                        System.out.println(frontend.port + "");
                        break;
                    }
                    case "-pb": {
                        known = true;
                        System.out.println(backend.port + "");
                        break;
                    }
                }
            }
            if(!known)
                System.out.println("Unknown arguments.");
            return true;
        }
        return false;
    }
}
