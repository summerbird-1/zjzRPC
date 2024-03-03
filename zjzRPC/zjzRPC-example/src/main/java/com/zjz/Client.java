package com.zjz;

public class Client {
    public static void main(String[] args) {
        RpcClient rpcClient = new RpcClient();
        CalcService service = rpcClient.getProxy(CalcService.class);
        int add = service.add(1, 2);
        int sub = service.sub(1, 2);
        System.out.println("add:" + add + ",sub:" + sub);
    }
}
