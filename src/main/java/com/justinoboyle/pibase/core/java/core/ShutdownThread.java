/**
 * by Justin O'Boyle
 * www.justinoboyle.com
 * on Jul 18, 2015
 * @formatter-on
 */
package com.justinoboyle.pibase.core.java.core;

import com.justinoboyle.pibase.core.java.plugin.PiPlugin;

public class ShutdownThread extends Thread {
    public static final String AUTHOR = "www.justinoboyle.com";

    public void run() {
        System.out.println("Shutting down.");
        for (PiPlugin p : PiBaseMain.plugins)
            p.onDisable();
        System.exit(0);
    }
}