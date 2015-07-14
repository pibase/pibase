package com.justinoboyle.pibase.core.java.server.listener;

import com.justinoboyle.pibase.core.java.server.packet.PacketIncoming;
import com.justinoboyle.pibase.core.java.server.packet.PacketOutgoing;

public abstract class ServerRequestListener {
    
    public abstract PacketOutgoing listen(PacketIncoming incoming);
    
}
