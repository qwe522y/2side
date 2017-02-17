package com.sotas;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Resource {
    private static Resource resource;
    public static Image icon = new ImageIcon(Resource.class.getResource("/car.png")).getImage();
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
    public String[][] technologicalOperations;
    public String[][] tipRegZnak;
    public String[][] formaSobstvennosti;
    public String[][] colorGroup;
    public String[][] ptsType;
    public String[][] svidRegType;
    public String[][] typeTS;

    public Resource() {
        try {
            bornPlaceRegion = getSingleColumnList("bornPlaceRegion.txt");
            nationality = getSingleColumnList("nationality.txt"); //гражданство
            passportType = getSingleColumnList("passportType.txt"); //документ удостоверяющий чичность
            passportCountry = getSingleColumnList("passportCountry.txt"); //страна документа удостоверяющего личность
            russianRegion = getSingleColumnList("russianRegion.txt"); //субъекты РФ
            technologicalOperations = getSingleColumnList("technologicalOperations.txt"); //технологические операции
            tipRegZnak = getSingleColumnList("tipRegZnak.txt"); //тип регистрационного знака
            formaSobstvennosti = getSingleColumnList("formaSobstvennosti.txt"); //форма собственности
            colorGroup = getSingleColumnList("colorGroup.txt"); //цветовая группа
            ptsType = getSingleColumnList("ptsType.txt"); // тип ПТС
            svidRegType = getSingleColumnList("svidRegType.txt"); // тип свидетельства о регистрации
            typeTS = getSingleColumnList("typeTS.txt"); // тип ТС
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String[][] getSingleColumnList(String txtPath) throws IOException {
        String[] arr = readList(txtPath);
        String[][] res = new String[arr.length][1];
        for(int i=0; i<arr.length; i++) {
            res[i][0] = arr[i];
        }
        return res;
    }

    private String[] readList(String filePath) throws IOException {
        Path path = Paths.get(getClass().getResource("/dict/" + filePath).getFile());
        Charset charset = Charset.forName("windows-1251");
        List<String> list = Files.readAllLines(path, charset);
        return list.toArray(new String[0]);
    }
}
