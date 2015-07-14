package com.justinoboyle.pibase.core.java.core;

import java.io.File;

public class PiConfiguration {
    
    private File fileLocation;
    
    public PiConfiguration(File location) {
        fileLocation = location;
        if(fileLocation.getParentFile() != null)
            fileLocation.getParentFile().mkdirs();
        
    }

    public String getConfig(String node) {
        return ""; //temporary
    }
    
    public void setConfig(String node, String value) {
        
    }
    
}
