package com.sotas;

import java.awt.*;

public class SvidRegDialog extends FormDialog {
    public SvidRegDialog(ComponentMap svidRegMap) {
        super(null, new PTSForm(svidRegMap), "Свид. о регистрации", new Dimension(750, 250));
    }
}
