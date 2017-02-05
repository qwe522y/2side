package com.sotas;

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
    public String[][] technologicalOperations;
    public String[][] tipRegZnak;
    public String[][] formaSobstvennosti;
    public String[][] colorGroup;

    public Resource() {
        try {
            bornPlaceRegion = getSingleColumnList("res/bornPlaceRegion.txt");
            nationality = getSingleColumnList("res/nationality.txt"); //гражданство
            passportType = getSingleColumnList("res/passportType.txt"); //документ удостоверяющий чичность
            passportCountry = getSingleColumnList("res/passportCountry.txt"); //страна документа удостоверяющего личность
            russianRegion = getSingleColumnList("res/russianRegion.txt"); //субъекты РФ
            technologicalOperations = getSingleColumnList("res/technologicalOperations.txt"); //технологические операции
            tipRegZnak = getSingleColumnList("res/tipRegZnak.txt"); //тип регистрационного знака
            formaSobstvennosti = getSingleColumnList("res/formaSobstvennosti.txt"); //форма собственности
            colorGroup = getSingleColumnList("res/colorGroup.txt"); //цветовая группа
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
        Path path = Paths.get(filePath);
        Charset charset = Charset.forName("windows-1251");
        List<String> list = Files.readAllLines(path, charset);
        return list.toArray(new String[0]);
    }
}
