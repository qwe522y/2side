package com.sotas;

import com.alee.laf.optionpane.WebOptionPane;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
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

    public static byte[] read(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        return buffer.toByteArray();
    }

    public static void showException(Exception ex) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ex.printStackTrace(new PrintStream(baos));
        WebOptionPane.showMessageDialog (null, "Возникла внутренняя ошибка\nmessage: " + ex.getMessage() + "\n" + new String(baos.toByteArray()) , "Ошибка", WebOptionPane.ERROR_MESSAGE );
    }
}
