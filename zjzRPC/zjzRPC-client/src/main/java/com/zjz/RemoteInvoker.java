package com.zjz;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector transportSelector;
    RemoteInvoker(Class clazz,Encoder encoder,Decoder decoder,TransportSelector transportSelector){
        this.clazz = clazz;
        this.decoder = decoder;
        this.encoder = encoder;
        this.transportSelector = transportSelector;

    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setServiceDescriptor(ServiceDescriptor.from(clazz,method));
        request.setParameters(args);

        Response response = invokeRmote(request);
        if(response == null || response.getCode() != 0)
            throw new IllegalStateException("fail to invoke remote" + response);

        return response.getData();
    }

    private Response invokeRmote(Request request) {
        TransportClient client = null;
        Response response = null;
        try{
             client = transportSelector.select();
            byte[] outBytes = encoder.encode(request);
            InputStream receive = client.write(new ByteArrayInputStream(outBytes));
            byte[] inBytes = IOUtils.readFully(receive, receive.available());
            response = decoder.decode(inBytes, Response.class);
        } catch (IOException e) {
            log.warn(e.getMessage(),e);
            response = new Response();
            response.setCode(1);
            response.setMessage("RpcClient got error: " +e.getClass()
            + ":" + e.getMessage());
        } finally {
              if(client != null)
                  transportSelector.release(client);
        }
     return response;
    }
}
