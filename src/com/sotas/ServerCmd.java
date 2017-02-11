package com.sotas;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.HttpURLConnection;

public class ServerCmd {
    //private String serverUrl = "127.0.0.1:8000";
    //private String serverUrl = "192.168.11.114";
    private String serverUrl = "izba.sotas05.ru";
    private static final Logger log = Logger.getLogger(ServerCmd.class);
    private Protocol protocol = new Protocol();
    public Prms sendRegisterRequest(String login, String password, Prms prms) {
        log.info("sendRegisterRequest login=" + login + ", prms=" + prms);
        HttpURLConnection conn = null;
        try {
            conn = HttpURLConnectionFactory.getHttpConnection("http://" + serverUrl + "/register");
            conn.setRequestMethod("POST");
            conn.addRequestProperty("login", login);
            conn.addRequestProperty("password", password);
            conn.setDoOutput(true);
            String request = protocol.genRegisterRequest(prms);
            conn.getOutputStream().write(request.getBytes("UTF-8"));
            int httpStatus = conn.getResponseCode();
            if (httpStatus == 200) { // http-status 200 - ok
                Prms res = protocol.parseRegisterRequest(new String(Utils.read(conn.getInputStream()), "UTF-8"));
                log.info("response: " + res);
                return res;
            } else {
                throw new RuntimeException("fail http-status: " + httpStatus);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) conn.disconnect();
        }
    }

    public boolean sendAuthRequest(String login, String password) {
        log.info("sendAuthRequest login=" + login);
        HttpURLConnection conn = null;
        try {
            conn = HttpURLConnectionFactory.getHttpConnection("http://" + serverUrl + "/auth");
            conn.setRequestMethod("POST");
            conn.addRequestProperty("login", login);
            conn.addRequestProperty("password", password);
            int httpStatus = conn.getResponseCode();
            log.info("response: " + httpStatus);
            if (httpStatus == 200) { // http-status 200 - ok
                return true;
            } else if (httpStatus == 403) {
                return false;
            } else {
                throw new RuntimeException("Ошибка на сервере");
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось связаться с сервером");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) conn.disconnect();
        }
    }
}
