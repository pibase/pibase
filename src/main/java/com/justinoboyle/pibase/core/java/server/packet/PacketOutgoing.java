package com.justinoboyle.pibase.core.java.server.packet;

import com.justinoboyle.pibase.core.java.util.StatusCode;

public class PacketOutgoing {
    public static final String PASS_STRING = "Could not pass to any plugins.";
    public static final PacketOutgoing PASS = new PacketOutgoing(StatusCode.NOT_FOUND, PASS_STRING, false);
    public static final PacketOutgoing NOT_FOUND = new PacketOutgoing(StatusCode.NOT_FOUND, "404 Not Found.", false);

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (isFile ? 1231 : 1237);
        result = prime * result + ((responseString == null) ? 0 : responseString.hashCode());
        result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PacketOutgoing other = (PacketOutgoing) obj;
        if (isFile != other.isFile)
            return false;
        if (responseString == null) {
            if (other.responseString != null)
                return false;
        } else if (!responseString.equals(other.responseString))
            return false;
        if (statusCode != other.statusCode)
            return false;
        return true;
    }

}
