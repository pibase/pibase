package com.justinoboyle.pibase.core.java.server.packet;

import java.util.HashMap;
import java.util.Map;

import com.justinoboyle.pibase.core.java.util.UtilHTTP;

public class PacketIncoming {
    
    private String from;
    private String methodName;
    private Map<String, String> data;
    
    public PacketIncoming(String from, String methodName, Map<String, String> data) {
        super();
        this.from = from;
        this.methodName = methodName;
        this.data = data;
    }
    
    public PacketIncoming(String from, String methodData) {
        super();
        this.from = from;
        this.methodName = methodData;
        data = new HashMap<String, String>();
        if(!this.methodName.contains("?"))
            return;
        String[] split = methodData.split("?");
        methodName = split[0];
        for(String s : split[1].split("&"))
            data.put(UtilHTTP.decode(s.split("=")[0]), UtilHTTP.decode(s.split("=")[1]));
    }
    
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    public Map<String, String> getData() {
        return data;
    }
    public void setData(Map<String, String> data) {
        this.data = data;
    }
    
}
