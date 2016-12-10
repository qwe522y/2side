package com.sotas;

import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.text.WebTextArea;

public class RightSidePanel extends SidePanel {
    public RightSidePanel(ComponentMap componentMap) {
        super(componentMap);
        createRow("Технологическая операция", genSpecialField()).setBackground(specialColor);
        createCustomRow();
        addElement(genLabel("    Документы ТС"), labelLen+fieldLen); rowBr();
        createRow("Одобрение типа ТС", genSpecialField());
        createRow("Технические документы", genSpecialField());
        createCustomRow2();
        createCustomRow3();
        addElement(genLabel("    Страховой полис"), labelLen+fieldLen); rowBr();
        createRow("Серия и номер");
        createRow("Дата выдачи", genDateField());
        createRow("Срок действия", genDateField());
        createCustomRow4();
        addElement(genLabel("    Документ, подтверждающий право собственности"), labelLen+fieldLen); rowBr();
        createRow("Серия и номер", genSpecialField());
        createRow("Стоимость ТС");
        createCustomRow5();
        addElement(genLabel("    Прочие представленные документы"), labelLen+fieldLen); rowBr();
        createRow("Свидетельство на агрегат", genSpecialField());
        createRow("Прочие документы", genSpecialField());
        createRow("Квитанция об оплате", genSpecialField());
        addElement(genLabel("    Утилизационные сбор"), labelLen+fieldLen); rowBr();
        createRow("Статус", genComboBox(new String[]{}));
        createRow("Значение");
        createCustomRow6();
    }

    private void createCustomRow() {
        addElement(genLabel("Ограниченный срок"), labelLen);
        addElement(genCheckBox(""), 20);
        rowBr();
    }

    private void createCustomRow2() {
        addElement(genLabel("ПТС"), labelLen);
        addElement(genSpecialField(), 250);

        xCursor +=10;
        addElement(genLabel("Действия ПТС "), 100);
        addElement(genSpecialField(), 100);

        rowBr();
    }

    private void createCustomRow3() {
        addElement(genLabel("Свид. о регистрации"), labelLen);
        addElement(genSpecialField(), 300);

        xCursor +=10;
        addElement(genCheckBox("Свид. утрачено"), 150);

        rowBr();
    }

    private void createCustomRow4() {
        addElement(genLabel("Страховщик"), labelLen);
        addElement(genSpecialField(), 370);

        xCursor +=10;
        addElement(genButton("Полис"), 80);
        rowBr();
    }

    private void createCustomRow5() {
        addElement(genLabel("Форма собственности"), labelLen);
        addElement(genSpecialField(), 370);

        xCursor +=10;
        addElement(genButton("Договор"), 80);
        rowBr();
    }

    private void createCustomRow6() {
        addElement(genLabel("<html>Вносимые изменения<br>в конструкцию ТС"), labelLen);

        WebTextArea textArea = new WebTextArea ();
        textArea.setLineWrap ( true );
        textArea.setWrapStyleWord ( true );
        WebScrollPane areaScroll = new WebScrollPane ( textArea );
        areaScroll.setBounds(xCursor, yCursor, 460, rowHeight*4);
        add(areaScroll);
        yCursor += yCursorStep;
    }
}
