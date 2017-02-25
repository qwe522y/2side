package com.sotas;

import com.alee.laf.label.WebLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

public class LeftSidePanel extends SidePanel {
    public LeftSidePanel(final ComponentMap cm, ComponentMap vladelecCm, ComponentMap predstavitelCm) {
        super(cm);
        createRow("Владелец", genSpecialField(new VladelecDialog(vladelecCm))).setBg(specialColor);
        createRow("Представитель", genSpecialField(new PredstavitelDialog(predstavitelCm)));
        addElement(genLabel("<html><b>&nbsp;&nbsp;&nbsp;&nbsp;Государственные регистрационные знаки ТС:</b></html>"), 2); rowBr();
        ListField tipRegZnakField = genListField(new TipRegZnakListDialog(cm));
        createCustomRow1(tipRegZnakField);
        createRow("Тип", tipRegZnakField).setBackground(specialColor);
        addElement(genLabel("<html><b>&nbsp;&nbsp;&nbsp;&nbsp;Сведения о транспортном средстве:</b></html>"), 2); rowBr();
        createCustomRow2(); // VIN
        createCustomRow21();
        createRow("Модификация");
        createRow("Изготовитель", genSpecialField()).setBackground(specialColor);
        createRow("Тип ТС", genListField(new ListDialog(Resource.getInstance().typeTS, null, "Типы ТС", new Dimension(600, 600)))).setBackground(specialColor);
        createRow(StrConst.kategoriya, genComboBox(new String[]{"", " A"," A1"," B"," B1"," C"," C1"," D"," D1"," Прицеп"})).setBackground(specialColor);
        createRow("Спецназначение", genSpecialField());
        createCustomRow3(); //Перевозка крупно-габаритного груза
        createRow(StrConst.god_vipuska, genNumericField());
        createRow("Модель двигателя");
        createRow("Номер двигателя");
        createRow("Номер кузова", genSpecialField(new SpecialFieldActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                field.setText(cm.getFieldText(StrConst.VIN_id+"1"));
            }
        }));
        createRow("Номер шасси", genSpecialField(new SpecialFieldActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                field.setText(cm.getFieldText(StrConst.VIN_id+"1"));
            }
        }));
        createCustomRow31();
        createCustomRow4(); //Мощн. двигателя Л/С
        createCustomRow5(); //Разрешается максимальная масса
        createCustomRow6(); //Тип двигателя
        createCustomRow7(); //Тип трансмиссии
        createRow("<html>Расположение руля</html>", genComboBox(new String[]{"Левостороннее", "Правостороннее"}));
    }

    private void createCustomRow1(final ListField tipRegZnakField) {
        addElement(new WebLabel("Номер"), 1);
        ComplexField complex = new ComplexField();
        final JTextField nomerField = genTextField();
        nomerField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                final String[] rows = TipRegZnakList.getList(nomerField.getText());
                tipRegZnakField.setItems(rows);
            }
        });
        getComponentMap().add(StrConst.nomer, nomerField);
        complex.add(nomerField, 200);
        complex.add(genLabel("<html>Действие<br>со знаком</html>"), 70).setHorizontalAlignment(SwingConstants.RIGHT);
        complex.add(genSpecialField());
        addElement(complex, 1);
        rowBr();
    }

    private void createCustomRow2() {
        addElement(genLabel(StrConst.VIN_id), 1);
        ComplexField complex = new ComplexField();
        final JTextField vin1Field = genTextField();
        vin1Field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int len = vin1Field.getText().length();
                if(len < 17) vin1Field.setForeground(Color.BLUE);
                else if(len > 17) vin1Field.setForeground(Color.RED);
                else vin1Field.setForeground(Color.BLACK);
            }
        });
        getComponentMap().add(StrConst.VIN_id + "1", complex.add(vin1Field, 260));
        JLabel label = genLabel("/");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        complex.add(label, 10);
        getComponentMap().add(StrConst.VIN_id + "2", complex.add(genTextField(), 190));
        addElement(complex, 1);
        rowBr();
    }

    private void createCustomRow21() {
        addElement(genLabel(StrConst.marka), 1);
        ComplexField complex = new ComplexField();
        getComponentMap().add(StrConst.marka, complex.add(genTextField(), 190));

        JLabel label = genLabel(StrConst.model + ":");
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        complex.add(label, 80);
        getComponentMap().add(StrConst.model, complex.add(genTextField(), 190));
        addElement(complex, 1);
        rowBr();
    }

    private void createCustomRow3() {
        addElement(genLabel(""), 1);
        ComplexField complex = new ComplexField();
        JCheckBox component = genCheckBox("<html>Перевозка крупно<br>-габаритного груза</html>");
        complex.add(component, 200);
        getComponentMap().add(StrConst.перевозка_крупногабаритного_груза, component);
        JCheckBox component2 = genCheckBox("<html>Оборудование<br>системы ГЛОНАСС</html>");
        getComponentMap().add(StrConst.оборудование_системы_ГЛОНАСС, component2);
        complex.add(component2, 200);
        addElement(complex, 1);
        rowBr();
    }

    private void createCustomRow31() {
        addElement(genLabel(StrConst.color), 1);
        ComplexField complex = new ComplexField();
        getComponentMap().add(StrConst.color, complex.add(genTextField(), 180));

        JLabel label = genLabel("Цвет. группа" + ":");
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        complex.add(label, 100);
        getComponentMap().add("Цветовая группа", complex.add(genListField(new ListDialog(Resource.getInstance().colorGroup, null, "Цветовые группы", new Dimension(600, 600))), 180).setBg(specialColor));
        addElement(complex, 1);
        rowBr();
    }

    private void createCustomRow4() {
        final JTextField fhp = genNumericField();
        final JTextField fkwt = genNumericField();

        fhp.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(fhp.getText().length() > 0)
                    fkwt.setText(new BigDecimal(fhp.getText()).setScale(4, BigDecimal.ROUND_DOWN).divide(new BigDecimal("1.36"), BigDecimal.ROUND_DOWN).setScale(2, BigDecimal.ROUND_DOWN).toString());
                else fkwt.setText("");
            }
        });

        fkwt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(fkwt.getText().length() > 0)
                    fhp.setText(new BigDecimal(fkwt.getText()).setScale(4, BigDecimal.ROUND_DOWN).multiply(new BigDecimal("1.36")).setScale(2, BigDecimal.ROUND_DOWN).toString());
                else fhp.setText("");
            }
        });



        addElement(genLabel(StrConst.moshnost_dvigatelya + " Л/С:"), 1);
        ComplexField complex = new ComplexField();
        getComponentMap().add(StrConst.moshnost_dvigatelya, complex.add(fhp, 160));

        complex.add(genLabel(" кВт:"), 45).setHorizontalAlignment(SwingConstants.RIGHT);
        getComponentMap().add(StrConst.moshnost_dvigatelya_kvt, complex.add(fkwt, 100));

        complex.add(genLabel(" Объем:"), 55).setHorizontalAlignment(SwingConstants.RIGHT);;
        JTextField v = genNumericField();
        complex.add(v, 100);
        getComponentMap().add(StrConst.Vdvigatelya, v);

        addElement(complex, 1);
        rowBr();
    }

    private void createCustomRow5() {
        xCursor = 0;
        addElement(genLabel("<html>Разрешается макс. масса(КГ)</html>"), 1);
        ComplexField complex = new ComplexField();
        JTextField m = genNumericField();
        complex.add(m, 160);
        getComponentMap().add(StrConst.max_massa, m);

        complex.add(genLabel(" Масса без нагрузки(КГ):"), 200).setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField m2 = genNumericField();
        complex.add(m2, 100);
        getComponentMap().add(StrConst.massa_bez_nagruzki, m2);

        addElement(complex, 1);
        rowBr();
    }

    private void createCustomRow6() {
        addElement(genLabel("Тип двигателя"), 1);
        ComplexField complex = new ComplexField();
        complex.add(genListField(new ListDialog(Resource.getInstance().engineType, null, "Типы двигателя", new Dimension(600, 600))), 260);

        complex.add(genLabel("Эколог. класс:"), 100).setHorizontalAlignment(SwingConstants.RIGHT);
        complex.add(genNumericField(), 100);

        addElement(complex, 1);
        rowBr();
    }

    private void createCustomRow7() {
        addElement(genLabel("Тип трансмиссии"), 1);
        ComplexField complex = new ComplexField();
        complex.add(genComboBox(new String[]{"Механическая", "Автоматическая"}), 160);

        complex.add(genLabel("Тип привода:"), 130).setHorizontalAlignment(SwingConstants.RIGHT);
        complex.add(genComboBox(new String[]{"Переднеприводный", "Заднеприводный", "Полноприводный", "Иные"}), 170);

        addElement(complex, 1);
        rowBr();
    }
}
