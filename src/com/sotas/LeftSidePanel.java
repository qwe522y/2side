package com.sotas;

import com.alee.laf.label.WebLabel;

import javax.swing.*;

public class LeftSidePanel extends SidePanel {
    public LeftSidePanel(ComponentMap componentMap) {
        super(componentMap);
        createRow(StrConst.vladelec, genSpecialField(new OwnerDialog())).setBg(specialColor);
        createRow("Представитель", genSpecialField());
        addElement(genLabel("    Государственные регистрационные знаки ТС"), 2); rowBr();
        createCustomRow1();
        createRow("Тип", genSpecialField()).setBackground(specialColor);
        addElement(genLabel("    Сведения о транспортном средстве"), 2); rowBr();
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
        createRow("<html>Расположение руля</html>", genComboBox(new String[]{""}));
    }

    private void createCustomRow1() {
        addElement(new WebLabel("Номер"), 1);
        ComplexField complex = new ComplexField();
        complex.add(genSpecialField(), 200);
        complex.add(genLabel("<html>Действие<br>со знаком</html>"), 70).setHorizontalAlignment(SwingConstants.RIGHT);
        complex.add(genSpecialField());
        addElement(complex, 1);
        rowBr();
    }

    private void createCustomRow2() {
        addElement(genLabel(StrConst.VIN_id), 1);
        ComplexField complex = new ComplexField();
        getComponentMap().add(StrConst.VIN_id + "1", complex.add(genSpecialField(), 300));
        JLabel label = genLabel("/");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        complex.add(label, 10);
        getComponentMap().add(StrConst.VIN_id + "2", complex.add(genSpecialField(), 150));
        addElement(complex, 1);
        rowBr();
    }

    private void createCustomRow3() {
        addElement(genLabel(""), 1);
        ComplexField complex = new ComplexField();
        complex.add(genCheckBox("<html>Перевозка крупно<br>-габаритного груза</html>"), 200);
        complex.add(genCheckBox("<html>Оборудование<br>системы ГЛОНАСС</html>"), 200);
        addElement(complex, 1);
        rowBr();
    }

    private void createCustomRow4() {
        addElement(genLabel(StrConst.moshnost_dvigatelya + " Л/С:"), 1);
        ComplexField complex = new ComplexField();
        getComponentMap().add(StrConst.moshnost_dvigatelya, complex.add(genTextField(), 160));

        complex.add(genLabel(" кВт:"), 45).setHorizontalAlignment(SwingConstants.RIGHT);
        complex.add(genTextField(), 100);

        complex.add(genLabel(" Объем:"), 55).setHorizontalAlignment(SwingConstants.RIGHT);;
        complex.add(genSpinner(), 100);

        addElement(complex, 1);
        rowBr();
    }

    private void createCustomRow5() {
        xCursor = 0;
        addElement(genLabel("<html>Разрешается макс. масса(КГ)</html>"), 1);

        ComplexField complex = new ComplexField();
        complex.add(genSpinner(), 160);

        complex.add(genLabel(" Масса без нагрузки(КГ):"), 200).setHorizontalAlignment(SwingConstants.RIGHT);;;
        complex.add(genSpinner(), 100);

        addElement(complex, 1);
        rowBr();
    }

    private void createCustomRow6() {
        addElement(genLabel("Тип двигателя"), 1);
        ComplexField complex = new ComplexField();
        complex.add(genSpecialField(), 160);

        complex.add(genLabel("Экологический класс:"), 200).setHorizontalAlignment(SwingConstants.RIGHT);
        complex.add(genSpinner(), 100);

        addElement(complex, 1);
        rowBr();
    }

    private void createCustomRow7() {
        addElement(genLabel("Тип трансмиссии"), 1);
        ComplexField complex = new ComplexField();
        complex.add(genComboBox(new String[]{""}), 160);

        complex.add(genLabel("Тип привода:"), 200).setHorizontalAlignment(SwingConstants.RIGHT);
        complex.add(genComboBox(new String[]{}), 100);

        addElement(complex, 1);
        rowBr();
    }
}
