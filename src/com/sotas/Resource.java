package com.sotas;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

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
    public String[][] formaSobstvennosti;
    public String[][] colorGroup;
    public String[][] ptsType;
    public String[][] svidRegType;
    public String[][] typeTS;
    public String[][] engineType;
    public String[][] documentOsnovanieType;

    public Map<String, String> technologicalOperationsPdfViewMap;

    public Resource() {
        try {
            bornPlaceRegion = getSingleColumnList("bornPlaceRegion.txt");
            nationality = getSingleColumnList("nationality.txt"); //гражданство
            passportType = getSingleColumnList("passportType.txt"); //документ удостоверяющий чичность
            passportCountry = getSingleColumnList("passportCountry.txt"); //страна документа удостоверяющего личность
            russianRegion = getSingleColumnList("russianRegion.txt"); //субъекты РФ
            formaSobstvennosti = getSingleColumnList("formaSobstvennosti.txt"); //форма собственности
            colorGroup = getSingleColumnList("colorGroup.txt"); //цветовая группа
            ptsType = getSingleColumnList("ptsType.txt"); // тип ПТС
            svidRegType = getSingleColumnList("svidRegType.txt"); // тип свидетельства о регистрации
            typeTS = getSingleColumnList("typeTS.txt"); // тип ТС
            engineType = getSingleColumnList("engineType.txt"); // тип двигателя
            documentOsnovanieType = getSingleColumnList("documentOsnovanieType.txt"); // документ основание -> тип

            loadTechnologicalOperations();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTechnologicalOperations() throws IOException, URISyntaxException {
        technologicalOperationsPdfViewMap = new HashMap<>();
        technologicalOperations = getSingleColumnList("technologicalOperations.txt"); //технологические операции
        for(int i=0; i<technologicalOperations.length; i++) {
            String s = technologicalOperations[i][0];
            if(s.contains("---")) {
                String[] keyVal = s.split("---");
                technologicalOperations[i][0] = keyVal[0];
                technologicalOperationsPdfViewMap.put(keyVal[0], keyVal[1]);
            }
        }
    }

    private String[][] getSingleColumnList(String txtPath) throws IOException, URISyntaxException {
        String[] arr = readList(txtPath);
        String[][] res = new String[arr.length][1];
        for(int i=0; i<arr.length; i++) {
            res[i][0] = arr[i];
        }
        return res;
    }

    private String[] readList(String filePath) throws IOException, URISyntaxException {
        InputStream is = getClass().getResourceAsStream("/dict/" + filePath);
        byte[] data = Utils.read(is);
        String s = new String(data, "CP1251");
        return s.split("\\n");
    }
}
