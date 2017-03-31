package com.sotas;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.radiobutton.WebRadioButton;
import com.alee.utils.swing.UnselectableButtonGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VladelecDialog extends AbstractDialog {
    private final NaturalPersonForm naturalPersonForm;
    private final AddressForm addressForm;

    public VladelecDialog(ComponentMap cm) {
        super(cm);
        addressForm = new AddressForm(cm);
        naturalPersonForm = new NaturalPersonForm(cm);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(330, 200);
        setResizable(false);
        setTitle("Выберете:");

        final WebRadioButton radioButtonA = new WebRadioButton ( "Физическое лицо" );
        final WebRadioButton radioButtonB = new WebRadioButton ( "Юридическое лицо" );
        final WebRadioButton radioButtonC = new WebRadioButton ( "Аккредитованный при МИД РФ" );

        radioButtonA.setSelected ( true );
        radioButtonC.setEnabled(false);

        WebLabel label = new WebLabel("Владелец");
        label.setBounds(10, 10, 200, 30);
        radioButtonA.setBounds(10, 40, 300, 30);
        radioButtonB.setBounds(10, 70, 300, 30);
        radioButtonC.setBounds(10, 100, 300, 30);

        add(label);
        final ButtonGroup group = new ButtonGroup();
        group.add(radioButtonA);
        group.add(radioButtonB);
        group.add(radioButtonC);
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
                String type = null;
                if(radioButtonA.isSelected()) type = radioButtonA.getText();
                if(radioButtonB.isSelected()) type = radioButtonB.getText();
                if(radioButtonC.isSelected()) type = radioButtonC.getText();
                if(type != null) {
                    VladelecDialog.this.dispose();
                    showNaturalPersonDialog(type);
                }
            }
        });
    }

    private void showNaturalPersonDialog(String actionCommand) {
        FormDialog dialog = new FormDialog(field, naturalPersonForm , "Сведения о физических лицах", new Dimension(700, 700));
        naturalPersonForm.getComponentMap().add(StrConst.Владелец.tip, new JTextField(actionCommand));
        dialog.setVisible(true);
    }

    class NaturalPersonForm extends SidePanel {
        public NaturalPersonForm(ComponentMap componentMap) {
            super(componentMap);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<50; i++) sb.append("..........");
            labelSuffix = sb.toString();
            fieldLen = 300;
            createRow(StrConst.Владелец.famil, genTextField()).setBackground(specialColor);
            createRow("Транслитерация фамилии", genTextField());
            createRow(StrConst.Владелец.name, genTextField()).setBackground(specialColor);
            createRow("Транслитерация имени");
            createRow(StrConst.Владелец.otchestvo);
            createRow(StrConst.Владелец.bornDate, genDateField()).setBackground(specialColor);
            createRow("Пол", genComboBox(new String[]{"", "Мужской", "Женский"})).setBackground(specialColor);
            createRow("Регион места рождения", genListField(new ListDialog(Resource.getInstance().bornPlaceRegion, null, "Регион места рождения", new Dimension(600, 800)))).setBg(specialColor);
            createRow("Место рождения").setBackground(specialColor);
            createRow(StrConst.Владелец.inn);
            createRow("Кем выдан ИНН");
            createRow("Индивидуальный предприниматель", genComboBox(new String[]{"", "Нет", "Да"})).setBackground(specialColor);
            createRow("ОГРНИП");
            createRow("Кем выдан ОГРНИП");
            createRow("Гражданство", genListField(new ListDialog(Resource.getInstance().nationality, null, "Гражданство", new Dimension(400, 600)))).setBg(specialColor);
            createRow(StrConst.Владелец.tipDUL, genListField(new ListDialog(Resource.getInstance().passportType, null, StrConst.Владелец.tipDUL, new Dimension(400, 600)))).setBg(specialColor);
            createRow(StrConst.Владелец.seriaNomerDUL).setBackground(specialColor);
            createRow(StrConst.Владелец.dataVidachiDUL, genDateField()).setBackground(specialColor);
            createRow("Код подразделения");
            createRow(StrConst.Владелец.kemVidanDUL).setBackground(specialColor);
            createRow("Страна документа, удостоверяющего личность", genListField(new ListDialog(Resource.getInstance().passportCountry, null, "Страна документа, удостоверяющего личность", new Dimension(400, 600)))).setBg(specialColor);
            createRow("Адрес регистрации", new SpecialField(new FormDialog(null, addressForm, "Адрес", new Dimension(750, 520))));
            createRow("Место работы");
            createRow("Должность");
            createRow("Домашний телефон");
            createRow(StrConst.Владелец.mobilePhone);
            createRow(StrConst.Владелец.mail);
        }

        @Override
        public String genShortText() {
            return (cm.getFieldText(StrConst.Владелец.famil) + " " + cm.getFieldText(StrConst.Владелец.name) + " " + cm.getFieldText(StrConst.Владелец.otchestvo)).toUpperCase();
        }
    }

    class AddressForm extends SidePanel{
        public AddressForm(ComponentMap componentMap) {
            super(componentMap);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<50; i++) sb.append("..........");
            labelSuffix = sb.toString();
            fieldLen = 300;
            prefix = StrConst.Владелец.adresReg.prefix;
            createRow(StrConst.Владелец.adresReg.strana, genListField(new ListDialog(Resource.getInstance().nationality, null, StrConst.Владелец.adresReg.strana, new Dimension(400, 600))));
            createRow(StrConst.Владелец.adresReg.subectRf, genListField(new ListDialog(Resource.getInstance().russianRegion, null, StrConst.Владелец.adresReg.subectRf, new Dimension(400, 600))));
            createRow(StrConst.Владелец.adresReg.rayon, genSpecialField());
            createRow(StrConst.Владелец.adresReg.naseleniyPunktPriRegVRf, genSpecialField());
            createRow(StrConst.Владелец.adresReg.naseleniyPunktPriRegNeVRF, genTextField());
            createRow(StrConst.Владелец.adresReg.ulicaPriRegVRf, genSpecialField());
            createRow(StrConst.Владелец.adresReg.ulicaPriRegNeVRf, genTextField());
            createRow("Тип жилья", genComboBox(new String[]{""}));
            createRow(StrConst.Владелец.adresReg.dom, genTextField());
            createRow(StrConst.Владелец.adresReg.korpus, genTextField());
            createRow("Тип строения", genComboBox(new String[]{""}));
            createRow(StrConst.Владелец.adresReg.stroyenie, genTextField());
            createRow(StrConst.Владелец.adresReg.kvartira, genTextField());
            createRow(StrConst.Владелец.adresReg.mailIndex, genTextField());
            createRow("ОКТМО", genTextField());
        }
    }
}