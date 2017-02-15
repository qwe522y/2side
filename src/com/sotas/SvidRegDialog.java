package com.sotas;

import java.awt.*;

public class SvidRegDialog extends FormDialog {
    public SvidRegDialog(ComponentMap svidRegMap) {
        super(null, new SvidRegForm(svidRegMap), "Свид. о регистрации", new Dimension(750, 250));
    }
}

class SvidRegForm extends SidePanel {

    public SvidRegForm(ComponentMap componentMap) {
        super(componentMap);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50; i++) sb.append("..........");
        labelSuffix = sb.toString();
        fieldLen = 300;
        createRow(StrConst.PTS.серия_и_номер, genTextField());
        createRow(StrConst.PTS.тип, genListField(new ListDialog(Resource.getInstance().svidRegType, null, "Типы cвид. о регистрации", new Dimension(500, 300))));
        createRow(StrConst.PTS.дата_выдачи, genDateField());
        createRow(StrConst.PTS.кем_выдан, genTextField());
        createRow(StrConst.PTS.особые_отметки, genTextField());
    }

    @Override
    public String genShortText() {
        return getComponentMap().getFieldText(StrConst.PTS.серия_и_номер);
    }
}