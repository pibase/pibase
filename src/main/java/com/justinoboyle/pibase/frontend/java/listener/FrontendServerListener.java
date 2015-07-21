package com.justinoboyle.pibase.frontend.java.listener;

import java.io.File;

import com.justinoboyle.pibase.core.java.plugin.PiPlugin;
import com.justinoboyle.pibase.core.java.plugin.PiPluginManager;
import com.justinoboyle.pibase.core.java.server.listener.ServerRequestListener;
import com.justinoboyle.pibase.core.java.server.packet.PacketIncoming;
import com.justinoboyle.pibase.core.java.server.packet.PacketOutgoing;
import com.justinoboyle.pibase.core.java.util.StatusCode;

public class FrontendServerListener extends ServerRequestListener {

    @Override
    public PacketOutgoing listen(PacketIncoming incoming) {
        if (incoming.getLocalMethodName().equals("/favicon.ico"))
            return new PacketOutgoing(StatusCode.NOT_IMPLEMENTED, "Not implemented.", false);
        String plugin = incoming.getLocalMethodName().split("/")[1].toLowerCase();
        try {
            PiPlugin p = PiPluginManager.getPlugin(plugin);
            if (p == null)
                throw new NullPointerException();
            String path = "temp/" + p.getClass().getName() + "/frontend/" + incoming.getLocalMethodName().substring(plugin.length() + 2);
            File f = new File(path);
            if (f.isDirectory()) {
                f = new File(path + "/" + "index.html");
                if (f.exists()) {
                    return new PacketOutgoing(StatusCode.OK, f.getAbsolutePath(), true);
                }
            }
            if (f.exists()) {
                return new PacketOutgoing(StatusCode.OK, f.getAbsolutePath(), true);
            }
        } catch (Exception ex) {
            // That's OK, it didn't pass to a plugin.
        }
        return PacketOutgoing.NOT_FOUND;
    }

}
