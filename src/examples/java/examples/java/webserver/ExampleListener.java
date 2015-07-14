package examples.java.webserver;

import com.justinoboyle.pibase.core.java.server.listener.ServerRequestListener;
import com.justinoboyle.pibase.core.java.server.packet.PacketIncoming;
import com.justinoboyle.pibase.core.java.server.packet.PacketOutgoing;
import com.justinoboyle.pibase.core.java.util.StatusCode;

public class ExampleListener extends ServerRequestListener {

    @Override
    public PacketOutgoing listen(PacketIncoming incoming) {
        PacketOutgoing outgoing = new PacketOutgoing(StatusCode.OK, "The request: " + incoming.getLocalMethodName(), false);
        return outgoing;
    }

}
