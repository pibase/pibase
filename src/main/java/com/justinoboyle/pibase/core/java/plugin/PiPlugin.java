package com.justinoboyle.pibase.core.java.plugin;

import net.xeoh.plugins.base.Plugin;
import net.xeoh.plugins.base.annotations.PluginImplementation;

@PluginImplementation
public abstract interface PiPlugin extends Plugin {

    public abstract void onEnable();

    public abstract void onDisable();

    public boolean onCommand(String commandName, String[] args);
}
