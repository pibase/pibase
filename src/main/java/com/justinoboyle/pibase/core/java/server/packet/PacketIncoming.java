package com.justinoboyle.pibase.core.java.server.packet;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import com.justinoboyle.pibase.core.java.util.UtilHTTP;

public class PacketIncoming {

    private InetAddress from;
    private String localMethodName;
    private Map<String, String> data;
    private String httpMethod;

    public PacketIncoming(InetAddress from, String httpMethod, String localMethodName, Map<String, String> data) {
        super();
        this.from = from;
        this.localMethodName = localMethodName;
        this.data = data;
    }

    public PacketIncoming(InetAddress from, String httpMethod, String localMethodData) {
        super();
        this.from = from;
        this.localMethodName = localMethodData;
        this.httpMethod = httpMethod;
        data = new HashMap<String, String>();
        localMethodName = localMethodData;
        if (!this.localMethodName.contains("?"))
            return;
        String[] split = localMethodData.split("?");
        localMethodName = split[0];
        for (String s : split[1].split("&"))
            data.put(UtilHTTP.decode(s.split("=")[0]), UtilHTTP.decode(s.split("=")[1]));
    }

    public InetAddress getFrom() {
        return from;
    }

    public void setFrom(InetAddress from) {
        this.from = from;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public String getLocalMethodName() {
        return localMethodName;
    }

    public void setLocalMethodName(String localMethodName) {
        this.localMethodName = localMethodName;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

}
