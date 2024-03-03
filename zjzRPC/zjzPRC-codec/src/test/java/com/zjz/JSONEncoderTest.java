package com.zjz;

import junit.framework.TestCase;
import org.junit.Test;

public class JSONEncoderTest extends TestCase {
     @Test
    public void testEncode() {
         Encoder encoder = new JSONEncoder();
         TestBean testBean = new TestBean("zjz",18);
         byte[] bytes = encoder.encode(testBean);
         assertNotNull(bytes);
     }
}