package com.sotas;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.radiobutton.WebRadioButton;
import com.alee.utils.swing.UnselectableButtonGroup;

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
        radioButtonA.setSelected ( true );
        final WebRadioButton radioButtonB = new WebRadioButton ( "Юридическое лицо" );
        radioButtonB.setEnabled(false);
        final WebRadioButton radioButtonC = new WebRadioButton ( "Аккредитованный при МИД РФ" );
        radioButtonC.setEnabled(false);
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
                VladelecDialog.this.dispose();
                showNaturalPersonDialog();
            }
        });
    }

    private void showNaturalPersonDialog() {
        FormDialog dialog = new FormDialog(field, naturalPersonForm , "Сведения о физических лицах", new Dimension(700, 700));
        dialog.setVisible(true);
    }

    class NaturalPersonForm extends SidePanel {
        public NaturalPersonForm(ComponentMap componentMap) {
            super(componentMap);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<50; i++) sb.append("..........");
            labelSuffix = sb.toString();
            fieldLen = 300;
            createRow(StrConst.vladelec.famil, genTextField()).setBackground(specialColor);
            createRow("Транслитерация фамилии", genTextField());
            createRow(StrConst.vladelec.name, genTextField()).setBackground(specialColor);
            createRow("Транслитерация имени");
            createRow(StrConst.vladelec.otchestvo);
            createRow(StrConst.vladelec.bornDate, genDateField()).setBackground(specialColor);
            createRow("Пол", genComboBox(new String[]{"", "Мужской", "Женский"})).setBackground(specialColor);
            createRow("Регион места рождения", genListField(new ListDialog(Resource.getInstance().bornPlaceRegion, null, "Регион места рождения", new Dimension(600, 800)))).setBg(specialColor);
            createRow("Место рождения").setBackground(specialColor);
            createRow(StrConst.vladelec.inn);
            createRow("Кем выдан ИНН");
            createRow("Индивидуальный предприниматель", genComboBox(new String[]{"", "Нет", "Да"})).setBackground(specialColor);
            createRow("ОГРНИП");
            createRow("Кем выдан ОГРНИП");
            createRow("Гражданство", genListField(new ListDialog(Resource.getInstance().nationality, null, "Гражданство", new Dimension(400, 600)))).setBg(specialColor);
            createRow(StrConst.vladelec.tipDUL, genListField(new ListDialog(Resource.getInstance().passportType, null, StrConst.vladelec.tipDUL, new Dimension(400, 600)))).setBg(specialColor);
            createRow(StrConst.vladelec.seriaNomerDUL).setBackground(specialColor);
            createRow(StrConst.vladelec.dataVidachiDUL, genDateField()).setBackground(specialColor);
            createRow("Код подразделения");
            createRow(StrConst.vladelec.kemVidanDUL).setBackground(specialColor);
            createRow("Страна документа, удостоверяющего личность", genListField(new ListDialog(Resource.getInstance().passportCountry, null, "Страна документа, удостоверяющего личность", new Dimension(400, 600)))).setBg(specialColor);
            createRow("Адрес регистрации", new SpecialField(new FormDialog(null, addressForm, "Адрес", new Dimension(750, 520))));
            createRow("Место работы");
            createRow("Должность");
            createRow("Домашний телефон");
            createRow(StrConst.vladelec.mobilePhone);
            createRow(StrConst.vladelec.mail);
        }

        @Override
        public String genShortText() {
            return (cm.getFieldText(StrConst.vladelec.famil) + " " + cm.getFieldText(StrConst.vladelec.name) + " " + cm.getFieldText(StrConst.vladelec.otchestvo)).toUpperCase();
        }
    }

    class AddressForm extends SidePanel{
        public AddressForm(ComponentMap componentMap) {
            super(componentMap);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<50; i++) sb.append("..........");
            labelSuffix = sb.toString();
            fieldLen = 300;
            prefix = StrConst.vladelec.adresReg.prefix;
            createRow(StrConst.vladelec.adresReg.strana, genListField(new ListDialog(Resource.getInstance().nationality, null, StrConst.vladelec.adresReg.strana, new Dimension(400, 600))));
            createRow(StrConst.vladelec.adresReg.subectRf, genListField(new ListDialog(Resource.getInstance().russianRegion, null, StrConst.vladelec.adresReg.subectRf, new Dimension(400, 600))));
            createRow(StrConst.vladelec.adresReg.rayon, genSpecialField());
            createRow(StrConst.vladelec.adresReg.naseleniyPunktPriRegVRf, genSpecialField());
            createRow(StrConst.vladelec.adresReg.naseleniyPunktPriRegNeVRF, genTextField());
            createRow(StrConst.vladelec.adresReg.ulicaPriRegVRf, genSpecialField());
            createRow(StrConst.vladelec.adresReg.ulicaPriRegNeVRf, genTextField());
            createRow("Тип жилья", genComboBox(new String[]{""}));
            createRow(StrConst.vladelec.adresReg.dom, genTextField());
            createRow(StrConst.vladelec.adresReg.korpus, genTextField());
            createRow("Тип строения", genComboBox(new String[]{""}));
            createRow(StrConst.vladelec.adresReg.stroyenie, genTextField());
            createRow(StrConst.vladelec.adresReg.kvartira, genTextField());
            createRow(StrConst.vladelec.adresReg.mailIndex, genTextField());
            createRow("ОКТМО", genTextField());
        }
    }
}