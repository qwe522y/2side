package com.sotas;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Resource {
    private static Resource resource;
    synchronized
    public static Resource getInstance() {
        if(resource == null) resource = new Resource();
        return resource;
    }

    public String[][] bornPlaceRegion;
    public String[][] nationality;
    public String[][] passportType;
    public String[][] passportCountry;
    public String[][] russianRegion;
    public Resource() {
        try {
            //bornPlaceRegion
            String[] arr = readList("res/bornPlaceRegion.txt");
            bornPlaceRegion = new String[arr.length][1];
            for(int i=0; i<arr.length; i++) {
                bornPlaceRegion[i][0] = arr[i];
            }
            //nationality - гражданство
            arr = readList("res/nationality.txt");
            nationality = new String[arr.length][1];
            for(int i=0; i<arr.length; i++) {
                nationality[i][0] = arr[i];
            }
            //passportType - документ удостоверяющий чичность
            arr = readList("res/passportType.txt");
            passportType = new String[arr.length][1];
            for(int i=0; i<arr.length; i++) {
                passportType[i][0] = arr[i];
            }
            //passportCountry - страна документа удостоверяющего личность
            arr = readList("res/passportCountry.txt");
            passportCountry = new String[arr.length][1];
            for(int i=0; i<arr.length; i++) {
                passportCountry[i][0] = arr[i];
            }
            //russianRegion - субъекты РФ
            arr = readList("res/russianRegion.txt");
            russianRegion = new String[arr.length][1];
            for(int i=0; i<arr.length; i++) {
                russianRegion[i][0] = arr[i];
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    private String[] readList(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Charset charset = Charset.forName("windows-1251");
        List<String> list = Files.readAllLines(path, charset);
        return list.toArray(new String[0]);
    }
}
