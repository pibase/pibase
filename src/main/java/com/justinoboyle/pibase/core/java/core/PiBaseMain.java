package com.justinoboyle.pibase.core.java.core;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

import com.justinoboyle.pibase.core.commands.CommandInterpreter;
import com.justinoboyle.pibase.core.java.plugin.PiPlugin;
import com.justinoboyle.pibase.core.java.plugin.PiPluginManager;

import net.xeoh.plugins.base.PluginManager;
import net.xeoh.plugins.base.impl.PluginManagerFactory;
import net.xeoh.plugins.base.util.PluginManagerUtil;

public class PiBaseMain {

    private static boolean shouldStayAlive = true;
    
    public static String currentVersion = "0.0.1a";

    public static Collection<PiPlugin> plugins = null;

    public static void main(String[] args) {
        System.out.println("Attempting to start...");
        File fi = new File("./plugins/");
        fi.mkdirs();
        File f2 = new File("./temp/");
        if (f2.exists())
            f2.delete();
        PluginManager pm = PluginManagerFactory.createPluginManager();
        pm.addPluginsFrom(fi.toURI());

        plugins = new PluginManagerUtil(pm).getPlugins(PiPlugin.class);
        plugins.add(new CorePlugin());
        Runtime.getRuntime().addShutdownHook(new ShutdownThread());
        for (PiPlugin p : plugins) {
            p.onEnable();
            System.out.println("Loaded plugin");
            try {
                PiPluginManager.extract(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Starting up...");
        System.out.println("Starting command interpreter...");
        Scanner sc = new Scanner(System.in);
        CommandInterpreter in = new CommandInterpreter();
        while (shouldStayAlive) {
            shouldStayAlive = !in.interpret(sc);
        }
        System.out.println("Shutting down.");
        for (PiPlugin p : plugins)
            p.onDisable();

    }

}
