package com.sotas;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.radiobutton.WebRadioButton;
import com.alee.laf.rootpane.WebDialog;
import com.alee.utils.swing.UnselectableButtonGroup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestDialog extends WebDialog {
    public TestDialog() {
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
                TestDialog.this.dispose();
            }
        });
    }
}
