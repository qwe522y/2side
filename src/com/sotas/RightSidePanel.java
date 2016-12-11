package com.sotas;

import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.text.WebTextArea;

import javax.swing.*;
import java.awt.*;

public class RightSidePanel extends SidePanel {
    public RightSidePanel(ComponentMap componentMap) {
        super(componentMap);
        createRow("<html>Технологическая операция</html>", genSpecialField()).setBackground(specialColor);
        createRow("", genCheckBox("Ограниченный срок"));
        addElement(genLabel("    Документы ТС"), 2); rowBr();
        createRow("<html>Одобрение типа ТС<html>", genSpecialField());
        createRow("<html>Технические документы<html>", genSpecialField());
        createCustomRow2();
        createCustomRow3();
        addElement(genLabel("    Страховой полис"), 2); rowBr();
        createRow("Серия и номер");
        createRow("Дата выдачи", genDateField());
        createRow("Срок действия", genDateField());
        createCustomRow4();
        addElement(genLabel("    Документ, подтверждающий право собственности"), labelLen+fieldLen); rowBr();
        createRow("Серия и номер", genSpecialField());
        createRow("Стоимость ТС");
        createCustomRow5();
        addElement(genLabel("    Прочие представленные документы"), 2); rowBr();
        createRow("Свидетельство на агрегат", genSpecialField());
        createRow("Прочие документы", genSpecialField());
        createRow("Квитанция об оплате", genSpecialField());
        addElement(genLabel("    Утилизационные сбор"), 2); rowBr();
        createRow("Статус", genComboBox(new String[]{}));
        createRow("Значение");
        createCustomRow6();
    }

    private void createCustomRow2() {
        addElement(genLabel("ПТС"), 1);
        ComplexField complex = new ComplexField();
        complex.add(genSpecialField(), 250);

        complex.add(genLabel("Действия ПТС:"), 110).setHorizontalAlignment(SwingConstants.RIGHT);
        complex.add(genSpecialField(), 100);

        addElement(complex, 1);
        rowBr();
    }

    private void createCustomRow3() {
        addElement(genLabel("Свид. о регистрации"), labelLen);
        ComplexField complex = new ComplexField();
        complex.add(genSpecialField(), 300);
        complex.add(genCheckBox("Свид. утрачено"), 160).setHorizontalAlignment(SwingConstants.RIGHT);
        addElement(complex, 1);
        rowBr();
    }

    private void createCustomRow4() {
        addElement(genLabel("Страховщик"), labelLen);
        ComplexField complex = new ComplexField();
        complex.add(genSpecialField(), 370);
        complex.add(genButton("Полис"), 90);
        addElement(complex, 1);
        rowBr();
    }

    private void createCustomRow5() {
        addElement(genLabel("Форма собственности"), labelLen);
        ComplexField complex = new ComplexField();
        complex.add(genSpecialField(), 370);
        complex.add(genButton("Договор"), 90);
        addElement(complex, 1);
        rowBr();
    }

    private void createCustomRow6() {
        JLabel lable = addElement(genLabel("<html>Вносимые изменения в конструкцию ТС</html>"), labelLen);
        lable.setMinimumSize(new Dimension(labelLen, 2*minRowHeight));
        lable.setPreferredSize(new Dimension(labelLen, 2*rowHeight));

        WebTextArea textArea = new WebTextArea ();
        textArea.setLineWrap ( true );
        textArea.setWrapStyleWord ( true );
        WebScrollPane areaScroll = new WebScrollPane ( textArea );
        areaScroll.setPreferredSize(new Dimension(fieldLen, 4*rowHeight));
        areaScroll.setMinimumSize(new Dimension(fieldLen, 4*minRowHeight));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = xCursor;
        c.gridy = yCursor;
        c.anchor = GridBagConstraints.LINE_END;
        c.fill = GridBagConstraints.VERTICAL;
        add(areaScroll, c);
        rowBr();
    }
}
