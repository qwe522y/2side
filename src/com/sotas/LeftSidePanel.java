package com.sotas;

import com.alee.laf.checkbox.WebCheckBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.text.WebTextField;

import javax.swing.*;

public class LeftSidePanel extends SidePanel {
    public LeftSidePanel(ComponentMap componentMap) {
        super(componentMap);
        createRow(StrConst.vladelec, genSpecialField()).setBackground(specialColor);
        createRow("Представитель", genSpecialField());
        addElement(genLabel("    Государственные регистрационные знаки ТС"), labelLen+fieldLen); rowBr();
        createCustomRow1();
        createRow("Тип", genSpecialField()).setBackground(specialColor);
        addElement(genLabel("    Сведения о транспортном средстве"), labelLen+fieldLen); rowBr();
        createCustomRow2();
        createRow(StrConst.marka, genSpecialField());
        createRow(StrConst.model, genSpecialField());
        createRow("Модификация");
        createRow("Изготовитель", genSpecialField()).setBackground(specialColor);
        createRow("Тип ТС", genSpecialField()).setBackground(specialColor);
        createRow(StrConst.kategoriya, genComboBox(new String[]{" A"," A1"," B"," B1"," C"," C1"," D"," D1"," Прицеп"})).setBackground(specialColor);
        createRow("Спецназначение", genSpecialField());
        createCustomRow3();
        createRow(StrConst.god_vipuska, genSpinner()); ((JSpinner)componentMap.getMap().get(StrConst.god_vipuska)).setValue(2010);
        createRow("Модель двигателя");
        createRow("Номер двигателя");
        createRow("Номер кузова", genSpecialField());
        createRow("Номер шасси", genSpecialField());
        createRow("Цвет", genSpecialField());
        createRow("Цветовая группа", genSpecialField()).setBackground(specialColor);
        createCustomRow4(); //Мощн. двигателя Л/С
        createCustomRow5(); //Разрешается максимальная масса
        createCustomRow6(); //Тип двигателя
        createCustomRow7(); //Тип трансмиссии
        createRow("Расположение руля", genComboBox(new String[]{""}));
    }

    private void createCustomRow1() {
        int f1len = 200;
        int f2len = 110;
        int l2len = 150;

        WebLabel l = new WebLabel("Номер");
        l.setBounds(0, yCursor, labelLen, rowHeight);
        add(l);

        WebTextField f = new SpecialField();
        f.setBounds(labelLen, yCursor, f1len, rowHeight);
        add(f);

        l = new WebLabel("Действие со знаком");
        l.setBounds(labelLen + f1len + 10, yCursor, l2len, rowHeight);
        add(l);

        f = new SpecialField();
        f.setBounds(labelLen + f1len + l2len, yCursor, f2len, rowHeight);
        add(f);
        yCursor += yCursorStep;
    }

    private void createCustomRow2() {
        int f1len = 330;
        int f2len = 115;
        int l2len = 10;

        JLabel l = genLabel(StrConst.VIN_id);
        l.setBounds(0, yCursor, labelLen, rowHeight);
        add(l);

        JTextField f = genSpecialField();
        f.setBounds(labelLen, yCursor, f1len, rowHeight);
        add(f);
        getComponentMap().add(StrConst.VIN_id + "1", f);

        l = new WebLabel("/");
        l.setBounds(labelLen + f1len + 5, yCursor, l2len, rowHeight);
        add(l);

        f = new SpecialField();
        f.setBounds(labelLen + f1len + l2len + 5, yCursor, f2len, rowHeight);
        add(f);
        getComponentMap().add(StrConst.VIN_id + "2", f);
        rowBr();
    }

    private void createCustomRow3() {
        int chLen = 200;
        WebCheckBox cb = new WebCheckBox("<html>Перевозка крупно<br>-габаритного груза</html>");
        cb.setBounds(labelLen, yCursor, chLen, rowHeight);
        add(cb);

        cb = new WebCheckBox("<html>Оборудование<br>системы ГЛОНАСС</html>");
        cb.setBounds(labelLen + chLen , yCursor, chLen , rowHeight);
        add(cb);
        yCursor += yCursorStep;
    }

    private void createCustomRow4() {
        addElement(genLabel(StrConst.moshnost_dvigatelya + " Л/С:"), labelLen);
        getComponentMap().add(StrConst.moshnost_dvigatelya, addElement(genTextField(), 160));

        xCursor +=10;
        addElement(genLabel("кВт:"), 30);
        addElement(genTextField(), 100);

        xCursor +=10;
        addElement(genLabel("Объем:"), 50);
        addElement(genSpinner(), 100);

        rowBr();
    }

    private void createCustomRow5() {
        xCursor = 0;
        addElement(genLabel("<html>Разрешается<br>максимальная масса(КГ)</html>"), labelLen);
        addElement(genSpinner(), 160);

        xCursor +=10;
        addElement(genLabel("Масса без нагрузки(КГ)"), 190);
        addElement(genSpinner(), 100);

        rowBr();
    }

    private void createCustomRow6() {
        xCursor = 0;
        addElement(genLabel("Тип двигателя"), labelLen);
        addElement(genSpinner(), 160);

        xCursor +=10;
        addElement(genLabel("Экологический класс"), 190);
        addElement(genSpinner(), 100);

        rowBr();
    }

    private void createCustomRow7() {
        xCursor = 0;
        addElement(genLabel("Тип трансмиссии"), labelLen);
        addElement(genComboBox(new String[]{""}), 160);

        xCursor +=10;
        addElement(genLabel("Тип привода"), 190);
        addElement(genComboBox(new String[]{}), 100);

        rowBr();
    }
}
