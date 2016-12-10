package com.sotas;

import com.alee.laf.checkbox.WebCheckBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.text.WebTextField;

import javax.swing.*;

public class LeftSidePanel extends SidePanel {
    public LeftSidePanel(ComponentMap componentMap) {
        super(componentMap);
        createRow(StrConst.vladelec, specialColor);
        createRow("Представитель");
        createRowLabel("    Государственные регистрационные знаки ТС");
        createCustomRow1();
        createRow("Тип", specialColor);
        createRowLabel("    Сведения о транспортном средстве");
        createCustomRow2();
        createRow(StrConst.marka);
        createRow(StrConst.model);
        createRow("Модификация", Type.TextField);
        createRow("Изготовитель", specialColor);
        createRow("Тип ТС", specialColor);

        createRow(StrConst.kategoriya, Type.ComboBox, specialColor);
        for(String i : new String[]{" A"," A1"," B"," B1"," C"," C1"," D"," D1"," Прицеп"}) ((JComboBox)componentMap.getMap().get(StrConst.kategoriya)).addItem(i);

        createRow("Спецназначение");
        createCustomRow3();
        createRow(StrConst.god_vipuska, Type.Spinner); ((JSpinner)componentMap.getMap().get(StrConst.god_vipuska)).setValue(2010);
        createRow("Модель двигателя",  Type.TextField);
        createRow("Номер двигателя",  Type.TextField);
        createRow("Номер кузова");
        createRow("Номер шасси");
        createRow("Цвет");
        createRow("Цветовая группа", specialColor);
        createCustomRow4(); //Мощн. двигателя Л/С
        createCustomRow5(); //Разрешается максимальная масса
        createCustomRow6(); //Тип двигателя
        createCustomRow7(); //Тип трансмиссии
        createRow("Расположение руля", Type.ComboBox);
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

        WebLabel l = new WebLabel(StrConst.VIN_id);
        l.setBounds(0, yCursor, labelLen, rowHeight);
        add(l);

        WebTextField f = new SpecialField();
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
        yCursor += yCursorStep;
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
        xCursor = 0;
        createLabel(StrConst.moshnost_dvigatelya + " Л/С:", labelLen);
        getComponentMap().add(StrConst.moshnost_dvigatelya, createTextField(160));

        xCursor +=10;
        createLabel("кВт:", 30);
        createTextField(100);

        xCursor +=10;
        createLabel("Объем:", 50);
        createSpinner(100);

        yCursor += yCursorStep;
    }

    private void createCustomRow5() {
        xCursor = 0;
        createLabel("<html>Разрешается<br>максимальная масса(КГ)</html>", labelLen);
        createSpinner(160);

        xCursor +=10;
        createLabel("Масса без нагрузки(КГ)", 190);
        createSpinner(100);

        yCursor += yCursorStep;
    }

    private void createCustomRow6() {
        xCursor = 0;
        createLabel("Тип двигателя", labelLen);
        createSpinner(160);

        xCursor +=10;
        createLabel("Экологический класс", 190);
        createSpinner(100);

        yCursor += yCursorStep;
    }

    private void createCustomRow7() {
        xCursor = 0;
        createLabel("Тип трансмиссии", labelLen);
        createComboBox(160);

        xCursor +=10;
        createLabel("Тип привода", 190);
        createComboBox(100);

        yCursor += yCursorStep;
    }
}
