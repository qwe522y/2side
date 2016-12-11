package com.sotas;

import com.alee.laf.button.WebButton;
import com.alee.laf.text.WebTextField;

import javax.swing.*;
import java.awt.*;

public class SpecialField extends WebTextField {
    public WebButton but;
    public SpecialField() {
        but = WebButton.createIconWebButton(new ImageIcon(getClass().getResource ( "/img/form_button16.png" )));
        but.setFocusable ( false );
        //popupButton.setShadeWidth ( 0 );
        but.setMoveIconOnPress ( false );
        but.setRolloverDecoratedOnly ( true );
        setTrailingComponent(but);
        but.setCursor ( Cursor.getDefaultCursor () );
    }
}
