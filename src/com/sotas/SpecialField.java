package com.sotas;

import com.alee.laf.button.WebButton;
import com.alee.laf.text.WebTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpecialField extends WebTextField {
    public JDialog dialog;
    public WebButton but;

    public SpecialField(final AbstractDialog  dialog) {
        this.dialog = dialog;
        setEditable(false);
        but = WebButton.createIconWebButton(new ImageIcon(getClass().getResource ( "/img/form_button16.png" )));
        but.setFocusable ( false );
        //popupButton.setShadeWidth ( 0 );
        but.setMoveIconOnPress ( false );
        but.setRolloverDecoratedOnly ( true );
        setTrailingComponent(but);
        but.setCursor ( Cursor.getDefaultCursor () );
        dialog.field = this;
        but.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressBut();
            }
        });
    }

    public void pressBut() {
        dialog.setVisible(true);
    }

    @Deprecated
    public SpecialField() {
        but = WebButton.createIconWebButton(new ImageIcon(getClass().getResource ( "/img/form_button16.png" )));
        but.setFocusable ( false );
        //popupButton.setShadeWidth ( 0 );
        but.setMoveIconOnPress ( false );
        but.setRolloverDecoratedOnly ( true );
        setTrailingComponent(but);
        but.setCursor ( Cursor.getDefaultCursor () );
    }

    public SpecialField setBg(Color color) {
        setBackground(color);
        return this;
    }
}
