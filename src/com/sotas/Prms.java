package com.sotas;

import java.util.HashMap;
import java.util.Map;

public class Prms extends HashMap<String, Map<String, String>> {
    public Prms(Map<String, String>... maps) {
        for(Map<String, String> map : maps) {
            String name = map.remove("mapName");
            put(name, map);
        }
    }

    public Prms() {
    }

    public String vladelec(String key) {
        return getVal(StrConst.vladelec.prefix, key);
    }

    public String main(String key) {
        return getVal("main", key);
    }

    private String getVal(String group, String key) {
        Map<String, String> m = get(group);
        if(m == null) return "";
        String val = m.get(key);
        if(val == null || val.length() == 0) return "";
        return val;
    }
}
