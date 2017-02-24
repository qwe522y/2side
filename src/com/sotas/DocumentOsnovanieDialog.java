package com.sotas;

import java.awt.*;

public class DocumentOsnovanieDialog extends FormDialog {
    public DocumentOsnovanieDialog(ComponentMap documentOsnovanieCm) {
        super(null, new DocumentOsnovanieForm(documentOsnovanieCm), "Документ-основание", new Dimension(750, 250));
    }
}

class DocumentOsnovanieForm extends SidePanel {

    public DocumentOsnovanieForm(ComponentMap componentMap) {
        super(componentMap);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<50; i++) sb.append("..........");
        labelSuffix = sb.toString();
        fieldLen = 300;
        createRow(StrConst.ДокументОснование.серия_и_номер, genTextField());
        createRow(StrConst.ДокументОснование.тип, genListField(new ListDialog(Resource.getInstance().documentOsnovanieType, null, "Типы", new Dimension(500, 300))));
        createRow(StrConst.ДокументОснование.дата_выдачи, genDateField());
        createRow(StrConst.ДокументОснование.кем_выдан, genTextField());
    }

    @Override
    public String genShortText() {
        return getComponentMap().getFieldText(StrConst.PTS.серия_и_номер);
    }
}
