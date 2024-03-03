package com.zjz;

import junit.framework.TestCase;
import org.junit.Test;

public class JSONDecoderTest extends TestCase {
      @Test
    public void testDecode() {
        Decoder decoder = new JSONDecoder();
        Encoder encoder = new JSONEncoder();
          TestBean testBean = new TestBean("zjz", 18);
          byte[] bytes = encoder.encode(testBean);
          TestBean testBean1 = decoder.decode(bytes, TestBean.class);
          assertEquals(testBean.getName(), testBean1.getName());
          assertEquals(testBean.getAge(), testBean1.getAge());
      }
}