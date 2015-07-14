package com.justinoboyle.pibase.core.java.server;

import java.io.PrintStream;

public abstract class PiBaseServer {
    
    public abstract PrintStream getOutputStream();
    
    public void start() { }; // TODO
    
    public void stop() { };
    

}
