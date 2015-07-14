package com.justinoboyle.pibase.core.java.server.listener;

import com.justinoboyle.pibase.core.java.plugin.Listener;
import com.justinoboyle.pibase.core.java.server.packet.PacketIncoming;
import com.justinoboyle.pibase.core.java.server.packet.PacketOutgoing;

public abstract class ServerRequestListener implements Listener {
    
    public abstract PacketOutgoing listen(PacketIncoming incoming);
    
}
