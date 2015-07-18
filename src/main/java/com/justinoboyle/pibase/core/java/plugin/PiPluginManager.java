package com.justinoboyle.pibase.core.java.plugin;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.codehaus.classworlds.Launcher;

import com.justinoboyle.pibase.core.java.util.JarCopyUtil;

public class PiPluginManager {

    

    // Thanks to user1079877 on
    // http://stackoverflow.com/questions/11012819/how-can-i-get-a-resource-folder-from-inside-my-jar-file

    public static void extract(PiPlugin plugin) throws IOException {
        final String path = "frontend";
        final File jarFile = new File(plugin.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());

        if (jarFile.isFile()) {
            final JarFile jar = new JarFile(jarFile);
            final Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                final String name = entries.nextElement().getName();
                if (name.startsWith(path + "/")) {
                    try {
                        JarCopyUtil.exportResource(plugin.getClass(), plugin.getClass().getName(), "/" + name);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            jar.close();
        } else {
            final URL url = Launcher.class.getResource("/" + path);
            if (url != null) {
                try {
                    final File apps = new File(url.toURI());
                    for (File app : apps.listFiles()) {
                        System.out.println(app);
                    }
                } catch (URISyntaxException ex) {
                }
            }
        }
    }
}
