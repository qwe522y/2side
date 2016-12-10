package com.sotas;

import com.alee.laf.panel.WebPanel;
import com.alee.laf.separator.WebSeparator;

public class VerticalSeparator extends WebPanel {
    private int width = 20;
    public VerticalSeparator(int len) {
        //setBackground(Color.BLUE);
        setSize(width, len);
        setLayout(null);
        WebSeparator sep = new WebSeparator(WebSeparator.VERTICAL);
        sep.setBounds(11, 0, 3, len);
        add(sep);
    }
}
