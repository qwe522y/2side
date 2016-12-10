package com.sotas;

import com.alee.laf.button.WebButton;
import com.alee.laf.checkbox.WebCheckBox;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.text.WebTextArea;

import javax.swing.*;

public class RightSidePanel extends SidePanel {
    public RightSidePanel(ComponentMap componentMap) {
        super(componentMap);
        createRow("Технологическая операция", specialColor);
        createCustomRow();
        createRowLabel("    Документы ТС");
        createRow("Одобрение типа ТС");
        createRow("Технические документы");
        createCustomRow2();
        createCustomRow3();
        createRowLabel("    Страховой полис");
        createRow("Серия и номер", Type.TextField);
        createRow("Дата выдачи", Type.DateField);
        createRow("Срок действия", Type.DateField);
        createCustomRow4();
        createRowLabel("    Документ, подтверждающий право собственности");
        createRow("Серия и номер");
        createRow("Стоимость ТС", Type.TextField);
        createCustomRow5();
        createRowLabel("    Прочие представленные документы");
        createRow("Свидетельство на агрегат");
        createRow("Прочие документы");
        createRow("Квитанция об оплате");
        createRowLabel("    Утилизационные сбор");
        createRow("Статус", Type.ComboBox);
        createRow("Значение", Type.TextField);
        createCustomRow6();
    }

    private void createCustomRow() {
        xCursor = 0;
        createLabel("Ограниченный срок", labelLen);
        WebCheckBox checkBox = new WebCheckBox();
        checkBox.setBounds(labelLen, yCursor, 20, rowHeight);
        add(checkBox);
        yCursor += yCursorStep;
    }

    private void createCustomRow2() {
        xCursor = 0;
        createLabel("ПТС", labelLen);
        createSpecialField(160);

        xCursor +=10;
        createLabel("Действия ПТС", 190);
        createSpecialField(100);

        yCursor += yCursorStep;
    }

    private void createCustomRow3() {
        xCursor = 0;
        createLabel("Свид. о регистрации", labelLen);
        createSpecialField(300);

        xCursor +=10;
        WebCheckBox checkBox = new WebCheckBox("Свид. утрачено");
        checkBox.setBounds(xCursor, yCursor, 150, rowHeight);
        add(checkBox);

        yCursor += yCursorStep;
    }

    private void createCustomRow4() {
        xCursor = 0;
        createLabel("Страховщик", labelLen);
        createSpecialField(370);

        xCursor +=10;
        JButton but = new WebButton("Полис");
        but.setBounds(xCursor, yCursor, 80, rowHeight);
        add(but);
        yCursor += yCursorStep;
    }

    private void createCustomRow5() {
        xCursor = 0;
        createLabel("Форма собственности", labelLen);
        createSpecialField(370);

        xCursor +=10;
        JButton but = new WebButton("Договор");
        but.setBounds(xCursor, yCursor, 80, rowHeight);
        add(but);
        yCursor += yCursorStep;
    }

    private void createCustomRow6() {
        xCursor = 0;
        createLabel("<html>Вносимые изменения<br>в конструкцию ТС", labelLen);

        WebTextArea textArea = new WebTextArea ();
        textArea.setLineWrap ( true );
        textArea.setWrapStyleWord ( true );
        WebScrollPane areaScroll = new WebScrollPane ( textArea );
        areaScroll.setBounds(xCursor, yCursor, 460, rowHeight*4);
        add(areaScroll);
        yCursor += yCursorStep;
    }
}
