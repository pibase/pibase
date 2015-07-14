package com.justinoboyle.pibase.core.java.server.packet;

import com.justinoboyle.pibase.core.java.util.StatusCode;

public class PacketOutgoing {

    private StatusCode statusCode;
    private String responseString;
    private boolean isFile;

    public PacketOutgoing(StatusCode statusCode, String responseString, boolean isFile) {
        super();
        this.statusCode = statusCode;
        this.responseString = responseString;
        this.isFile = isFile;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean isFile) {
        this.isFile = isFile;
    }

}
