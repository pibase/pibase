package examples.java.webserver;

import com.justinoboyle.pibase.core.webserver.java.WebServer;

public class ExampleWebServer {

    public void setupServer() {
        int port = 341;
        WebServer srv = new WebServer(port, new ExampleListener());
        srv.enable(); // start the server.
    }
    
}
