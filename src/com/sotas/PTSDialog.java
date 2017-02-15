package com.sotas;

import java.awt.*;

public class PTSDialog extends FormDialog {
    public PTSDialog(ComponentMap ptsMap) {
        super(null, new PTSForm(ptsMap), "ПТС", new Dimension(750, 250));
    }

}

class PTSForm extends SidePanel {

    public PTSForm(ComponentMap componentMap) {
        super(componentMap);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<50; i++) sb.append("..........");
        labelSuffix = sb.toString();
        fieldLen = 300;
        createRow(StrConst.PTS.серия_и_номер, genTextField());
        createRow(StrConst.PTS.тип, genListField(new ListDialog(Resource.getInstance().ptsType, null, "Типы ПТС", new Dimension(500, 300))));
        createRow(StrConst.PTS.дата_выдачи, genDateField());
        createRow(StrConst.PTS.кем_выдан, genTextField());
        createRow(StrConst.PTS.особые_отметки, genTextField());
    }

    @Override
    public String genShortText() {
        return getComponentMap().getFieldText(StrConst.PTS.серия_и_номер);
    }
}
