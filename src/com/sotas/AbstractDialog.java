package com.sotas;

import com.alee.laf.rootpane.WebDialog;

public class AbstractDialog extends WebDialog {
    public SpecialField field;
    public ComponentMap cm;

    public AbstractDialog(ComponentMap cm) {
        this.cm = cm;
        setIconImage(Resource.icon);
    }
}
