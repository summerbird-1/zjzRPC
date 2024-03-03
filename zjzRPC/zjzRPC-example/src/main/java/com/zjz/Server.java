package com.zjz;

public class Server {

    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(CalcService.class, new CalcServiceImpl());
        rpcServer.start();
    }
}
