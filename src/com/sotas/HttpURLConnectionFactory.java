package com.sotas;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionFactory {
    public static final int READ_TIMEOUT = 60*1000;
    public static final int CONNECTION_TIMEOUT = 60*1000;

    public static HttpURLConnection getHttpConnection(String host) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(host).openConnection();
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
