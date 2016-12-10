package com.sotas;

import java.text.SimpleDateFormat;

public class Utils {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    public static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public static String cutFio(String fio) {
        fio = fio.trim();
        String[] fioAr = fio.split(" ");
        String res = fioAr[0].trim();
        if(fioAr.length == 3) {
            res += " " + fioAr[1].trim().substring(0, 1) + ". " + fioAr[2].trim().substring(0, 1)+ ".";
        }
        return res;
    }
}
