package com.sotas;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.util.LinkedHashMap;
import java.util.Map;

public class ComponentMap {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ComponentMap.class);
    private final Map<String, JComponent> map = new LinkedHashMap<>();
    public String getFieldText(String name) {
        name = removeTags(name);
        try {
            JComponent c = map.get(name);
            if(c == null) {
                log.warn("no item for name " + name);
                return "";
            }
            if (c instanceof JTextComponent)
                return ((JTextComponent) c).getText();
            if (c instanceof JSpinner)
                return "" + ((JSpinner) c).getValue();
            if (c instanceof JComboBox) {
                return "" + ((JComboBox) c).getSelectedItem();
            }
            if (c instanceof JCheckBox) {
                return "" + ((JCheckBox) c).isSelected();
            }
            throw new RuntimeException(c.getClass() + " is wrong class");
        } catch (RuntimeException e) {
            log.error("error for name " + name);
            throw e;
        }
    }

    public void add(String name, JComponent component){
        map.put(removeTags(name), component);
    }

    public Map<String, JComponent> getMap() {
        return map;
    }

    private String removeTags(String str) {
        str = str.replace("<html>", "");
        str = str.replace("</html>", "");
        str = str.replace("<br>", " ");
        return str;
    }
}
