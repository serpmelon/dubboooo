package com.togo.register;

/**
 * @Author taiyn
 * @Description the service address
 * @Date 9:36 下午 2020/4/11
 **/
public class ServiceAddress {

    private String host;
    private int port;

    public ServiceAddress(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
