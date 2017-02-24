package com.sotas;

import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.text.WebTextArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RightSidePanel extends SidePanel {
    public RightSidePanel(ComponentMap cm, ComponentMap ptsCm, ComponentMap svidRegCm, ComponentMap kvitanciyaMap, ComponentMap documentOsnovanieCm) {
        super(cm);
        createRow("<html>" + StrConst.технологическая_операция + "</html>", genListField(new ListDialog(Resource.getInstance().technologicalOperations, null, "Технологические операции", new Dimension(600, 600)))).setBackground(specialColor);
        createRow("Ограниченный срок", genCheckBox(""));
        addElement(genLabel("<html><b>&nbsp;&nbsp;&nbsp;&nbsp;Документы ТС:</b></html>"), 2); rowBr();
        createRow("<html>Одобрение типа ТС<html>", genSpecialField());
        createRow("<html>Технические документы<html>", genSpecialField());
        createCustomRow2(ptsCm);
        createCustomRow3(svidRegCm);

        addElement(genLabel("<html><b>&nbsp;&nbsp;&nbsp;&nbsp;Страховой полис:</b></html>"), 2); rowBr();
        prefix = StrConst.Страховой_полис._name;
        createCustomRow301();
        createCustomRow31();
        createCustomRow4();
        prefix = null;

        addElement(genLabel("<html><b>&nbsp;&nbsp;&nbsp;&nbsp;Документ, подтверждающий право собственности:</b></html>"), 2); rowBr();
        createRow("Серия и номер", genSpecialField(new DocumentOsnovanieDialog(documentOsnovanieCm)));
        createRow("Стоимость ТС");
        createCustomRow5();
        addElement(genLabel("<html><b>&nbsp;&nbsp;&nbsp;&nbsp;Прочие представленные документы</html>"), 2); rowBr();
        createRow("Свидетельство на агрегат", genSpecialField());
        createRow("Прочие документы", genSpecialField());
        createRow("Квитанция об оплате", genSpecialField(new KvitanciyaDialog(kvitanciyaMap)));
        addElement(genLabel("<html><b>&nbsp;&nbsp;&nbsp;&nbsp;Утилизационные сбор:</b></html>"), 2); rowBr();
        createRow("Статус", genComboBox(new String[]{"Отсутствуют сведения", "Уплачен", "Приняты обязательства", "Не уплачивается"}));
        createRow("Значение");
        createCustomRow6();
    }

    private void createCustomRow2(ComponentMap ptsCm) {
        addElement(genLabel("ПТС"), 1);
        ComplexField complex = new ComplexField();
        SpecialField field = genSpecialField(new PTSDialog(ptsCm));
        complex.add(field, 250);

        complex.add(genLabel("Действия ПТС:"), 110).setHorizontalAlignment(SwingConstants.RIGHT);
        complex.add(genSpecialField(), 100);

        addElement(complex, 1);
        rowBr();
    }

    private void createCustomRow3(ComponentMap svidRegCm) {
        addElement(genLabel("Cвид. о регистрации"), 1);
        ComplexField complex = new ComplexField();
        SpecialField field = genSpecialField(new SvidRegDialog(svidRegCm));

        complex.add(field, 300);
        JCheckBox checkBox = genCheckBox(StrConst.свид_утрачено);
        getComponentMap().add(StrConst.свид_утрачено, checkBox);
        complex.add(checkBox, 160).setHorizontalAlignment(SwingConstants.RIGHT);
        addElement(complex, 1);
        rowBr();
    }

    private void createCustomRow301() {
        final JTextField f = genTextField();
        f.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int len = f.getText().length();
                if(len < 13) f.setForeground(Color.BLUE);
                else if(len > 13) f.setForeground(Color.RED);
                else f.setForeground(Color.BLACK);
            }
        });
        createRow("Серия и номер", f);
    }

    private void createCustomRow31() {
        addElement(genLabel("Дата выдачи"), 1);
        ComplexField complex = new ComplexField();
        getComponentMap().add(StrConst.Страховой_полис._name + "_" + StrConst.Страховой_полис.дата_выдачи, complex.add(genDateField(), 170));

        JLabel label = genLabel("Срок действия" + ":");
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        complex.add(label, 120);
        getComponentMap().add(StrConst.Страховой_полис._name + "_" + StrConst.Страховой_полис.срок_действия, complex.add(genDateField(), 170));
        addElement(complex, 1);
        rowBr();
    }

    private void createCustomRow4() {
        addElement(genLabel("Страховщик"), 1);
        ComplexField complex = new ComplexField();
        SpecialField f = genSpecialField();
        complex.add(f, 370);
        JButton but = genButton("Полис");
        but.setFocusable(false);
        complex.add(but, 90);
        addElement(complex, 1);
        getComponentMap().add(StrConst.Страховой_полис._name + "_" + StrConst.Страховой_полис.страховщик, f);
        rowBr();
    }

    private void createCustomRow5() {
        addElement(genLabel("Форма собственности"), 1);
        ComplexField complex = new ComplexField();
        complex.add(genListField(new ListDialog(Resource.getInstance().formaSobstvennosti, null, "Формы собственности", new Dimension(600, 600))), 370);
        JButton but = genButton("Договор");
        but.setFocusable(false);
        complex.add(but, 90);
        addElement(complex, 1);
        rowBr();
    }

    private void createCustomRow6() {
        JLabel lable = addElement(genLabel("<html>Вносимые изменения в конструкцию ТС</html>"), 1);
        lable.setMinimumSize(new Dimension(0, 2*minRowHeight));
        lable.setPreferredSize(new Dimension(0, 2*rowHeight));

        WebTextArea textArea = new WebTextArea ();
        textArea.setLineWrap ( true );
        textArea.setWrapStyleWord ( true );
        WebScrollPane areaScroll = new WebScrollPane ( textArea );
        areaScroll.setPreferredSize(new Dimension(fieldLen, 3*rowHeight));
        areaScroll.setMinimumSize(new Dimension(fieldLen, 3*minRowHeight));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = xCursor;
        c.gridy = yCursor;
        c.anchor = GridBagConstraints.LINE_END;
        c.fill = GridBagConstraints.VERTICAL;
        add(areaScroll, c);
        rowBr();
    }
}
