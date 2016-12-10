package com.sotas;

import com.alee.laf.button.WebButton;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.text.WebTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SpecialField extends WebTextField {
    public SpecialField() {
        WebButton popupButton = WebButton.createIconWebButton(new ImageIcon(getClass().getResource ( "/img/form_button16.png" )));
        popupButton.setFocusable ( false );
        //popupButton.setShadeWidth ( 0 );
        popupButton.setMoveIconOnPress ( false );
        popupButton.setRolloverDecoratedOnly ( true );
        setTrailingComponent(popupButton);
        popupButton.setCursor ( Cursor.getDefaultCursor () );
        popupButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WebDialog d = new TestDialog();
                d.setModal(true);
                d.setLocation(300, 300);
                d.setVisible(true);
            }
        });
    }
}
