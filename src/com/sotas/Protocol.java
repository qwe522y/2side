package com.sotas;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Protocol {
    public String genRegisterRequest(Prms prms) {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(prms);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Prms parseRegisterRequest(String json) {
        try {
            return new ObjectMapper().readValue(json, Prms.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
