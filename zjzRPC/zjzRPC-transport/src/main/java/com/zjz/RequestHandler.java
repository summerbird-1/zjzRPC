package com.zjz;

import java.io.InputStream;
import java.io.OutputStream;

public interface RequestHandler {
    void onRequest(InputStream receive, OutputStream toRespond);
}
