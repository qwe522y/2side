package com.sotas;

import java.awt.*;

public class KvitanciyaDialog extends FormDialog {

    public KvitanciyaDialog(ComponentMap kvitanciyaMap) {
        super(null, new KvitanciyaForm(kvitanciyaMap), "Квитанция", new Dimension(700, 300));
    }
}

class KvitanciyaForm extends SidePanel {
    public KvitanciyaForm(ComponentMap componentMap) {
        super(componentMap);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<50; i++) sb.append("..........");
        labelSuffix = sb.toString();
        fieldLen = 300;
        createRow(StrConst.Квитанция_об_оплате.сумма_платежа, genNumericField());
        createRow(StrConst.Квитанция_об_оплате.дата_платежа, genDateField());
        createRow(StrConst.Квитанция_об_оплате.номер_платежа, genTextField());
    }
}
