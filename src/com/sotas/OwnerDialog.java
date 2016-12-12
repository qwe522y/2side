package com.sotas;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.radiobutton.WebRadioButton;
import com.alee.utils.swing.UnselectableButtonGroup;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OwnerDialog extends AbstractDialog {
    public OwnerDialog() {
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(330, 200);
        setResizable(false);
        setTitle("Выберете:");
        final WebRadioButton radioButtonA = new WebRadioButton ( "Физическое лицо" );
        radioButtonA.setSelected ( true );
        final WebRadioButton radioButtonB = new WebRadioButton ( "Юридическое лицо" );
        radioButtonA.setSelected ( true );
        final WebRadioButton radioButtonC = new WebRadioButton ( "Аккредитованный при МИД РФ" );
        radioButtonA.setSelected ( true );
        WebLabel label = new WebLabel("Владелец");
        label.setBounds(10, 10, 200, 30);
        radioButtonA.setBounds(10, 40, 300, 30);
        radioButtonB.setBounds(10, 70, 300, 30);
        radioButtonC.setBounds(10, 100, 300, 30);

        add(label);
        add(radioButtonA);
        add(radioButtonB);
        add(radioButtonC);

        // Grouping buttons with custom button group that allows deselection
        UnselectableButtonGroup.group ( radioButtonA, radioButtonB, radioButtonC);

        WebButton but = new WebButton("OK");
        but.setBounds(240, 140, 80, 30);
        add(but);

        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OwnerDialog.this.dispose();
                showNaturalPersonDialog();
            }
        });
    }

    private void showNaturalPersonDialog() {
        FormDialog dialog = new FormDialog(new NaturalPersonForm(new ComponentMap()), "Сведения о физических лицах", new Dimension(700, 900));
        dialog.setVisible(true);
    }
}

class NaturalPersonForm extends SidePanel {
    public NaturalPersonForm(ComponentMap componentMap) {
        super(componentMap);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<50; i++) sb.append("..........");
        labelSuffix = sb.toString();
        fieldLen = 300;
        createRow("Фамилия", genTextField()).setBackground(specialColor);
        createRow("Транслитерация фамилии", genTextField());
        createRow("Имя", genTextField()).setBackground(specialColor);
        createRow("Транслитерация имени");
        createRow("Отчество");
        createRow("Дата рождения", genDateField()).setBackground(specialColor);
        createRow("Пол", genComboBox(new String[]{"Мужской", "Женский"})).setBackground(specialColor);
        createRow("Регион места рождения", genSpecialField(new ListDialog(Resource.getInstance().bornPlaceRegion, null, "Регион места рождения", new Dimension(600, 800)))).setBg(specialColor);
        createRow("Место рождения").setBackground(specialColor);
        createRow("ИНН");
        createRow("Кем выдан ИНН");
        createRow("Индивидуальный предприниматель", genComboBox(new String[]{"Нет", "Да"})).setBackground(specialColor);
        createRow("ОГРНИП");
        createRow("Кем выдан ОГРНИП");
        createRow("Гражданство", genSpecialField(new ListDialog(Resource.getInstance().nationality, null, "Гражданство", new Dimension(400, 600)))).setBg(specialColor);
        createRow("Тип документа удостоверяющий личность", genSpecialField(new ListDialog(Resource.getInstance().passportType, null, "Тип документа удостоверяющий личность", new Dimension(400, 600)))).setBg(specialColor);
        createRow("Серия и номер документа, удостоверяющего личность").setBackground(specialColor);
        createRow("Дата выдачи документа, удостоверяющего личность", genDateField()).setBackground(specialColor);
        createRow("Код подразделения");
        createRow("Кем выдан документ, удостоверяющий личность").setBackground(specialColor);
        createRow("Страна документа, удостоверяющего личность", genSpecialField(new ListDialog(Resource.getInstance().passportCountry, null, "Страна документа, удостоверяющего личность", new Dimension(400, 600)))).setBg(specialColor);
        createRow("Адрес регистрации", genSpecialField()).but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormDialog dialog = new FormDialog(new AddressForm(new ComponentMap()), "Адрес", new Dimension(750, 480));
                dialog.setVisible(true);
            }
        });
        createRow("Место работы");
        createRow("Должность");
        createRow("Домашний телефон");
        createRow("Мобильный телефон");
        createRow("Электронная почта");
    }
}

class AddressForm extends SidePanel{
    public AddressForm(ComponentMap componentMap) {
        super(componentMap);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<50; i++) sb.append("..........");
        labelSuffix = sb.toString();
        fieldLen = 300;
        createRow("Страна", genSpecialField(new ListDialog(Resource.getInstance().nationality, null, "Страна", new Dimension(400, 600))));
        createRow("Субъект Российской Федерации", genSpecialField(new ListDialog(Resource.getInstance().russianRegion, null, "Субъекты Российской Федерации", new Dimension(400, 600))));
        createRow("Район", genSpecialField());
        createRow("Населенный пункт(при регистрации в России)", genSpecialField());
        createRow("Населенный пункт(при регистрации за пределами России)", genTextField());
        createRow("Улица(при регистрации в России)", genSpecialField());
        createRow("Улица(при регистрации за пределами России)", genTextField());
        createRow("Тип жилья", genComboBox(new String[]{""}));
        createRow("Дом", genTextField());
        createRow("Корпус", genTextField());
        createRow("Тип строения", genComboBox(new String[]{""}));
        createRow("Строения", genTextField());
        createRow("Квартира", genTextField());
        createRow("Почтовый индекс", genTextField());
        createRow("ОКТМО", genTextField());
    }
}