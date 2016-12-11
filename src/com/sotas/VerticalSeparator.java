package com.sotas;

import com.alee.laf.panel.WebPanel;
import com.alee.laf.separator.WebSeparator;

import java.awt.*;

public class VerticalSeparator extends WebPanel {
    private int width = 20;
    public VerticalSeparator() {
        //setBackground(Color.BLUE);
        setLayout(new BorderLayout());
        WebSeparator sep = new WebSeparator(WebSeparator.VERTICAL);
        add(sep, BorderLayout.CENTER);
    }
}
