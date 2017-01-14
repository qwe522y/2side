package com.sotas;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Protocol {
    public String genRegisterRequest(Prms prms) {
        try {
            return new ObjectMapper().writeValueAsString(prms);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Prms parseRegisterRequest(String json) {
        return new Prms();
    }
}
