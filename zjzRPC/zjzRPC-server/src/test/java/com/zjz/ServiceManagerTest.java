package com.zjz;

import com.zjz.utils.ReflectUtils;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertNotNull;

public class ServiceManagerTest{
   ServiceManager sm;
   @Before
   public void init() {
       sm = new ServiceManager();
   }
    @Test
    public void testRegister() {
        TestInterface bean = new TestClass();
        sm.register(TestInterface.class, bean);
    }

    @Test
    public void testLookup() {
        TestInterface bean = new TestClass();
        sm.register(TestInterface.class, bean);
        Method[] publicMethods = ReflectUtils.getPublicMethods(TestInterface.class);
        ServiceDescriptor sdp = ServiceDescriptor.from(TestInterface.class, publicMethods[0]);
        Request request = new Request();
        request.setServiceDescriptor(sdp);
        ServiceInstance lookup = sm.lookup(request);
        assertNotNull(lookup);
    }
}