package com.sotas;

import org.apache.log4j.Logger;

import java.net.HttpURLConnection;

public class ServerCmd {
    private static final Logger log = Logger.getLogger(ServerCmd.class);
    private Protocol protocol = new Protocol();
    public Prms sendRegisterRequest(String login, String password, int docnum, Prms prms) {
        log.info("sendMonitorMsg prms=" + prms);
        HttpURLConnection conn = null;
        try {
            conn = HttpURLConnectionFactory.getHttpConnection("http://localhost:8000/register");
            conn.setRequestMethod("POST");
            conn.addRequestProperty("login", login);
            conn.addRequestProperty("password", password);
            conn.addRequestProperty("docnum", ""+docnum);
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
}
