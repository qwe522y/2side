package com.sotas;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.util.LinkedHashMap;
import java.util.Map;

public class ComponentMap {
    private final Map<String, JComponent> map = new LinkedHashMap<>();
    public String getFieldText(String name) {
        JComponent c = map.get(name);
        if(c instanceof JTextComponent)
            return ((JTextComponent)c).getText();
        if(c instanceof JSpinner)
            return ((JSpinner)c).getValue().toString();
        if(c instanceof JComboBox) {
            return ((JComboBox)c).getSelectedItem().toString();
        }
        else throw new RuntimeException(c.getClass() + " is wrong class");
    }

    public void add(String name, JComponent component){
        map.put(name, component);
    }

    public Map<String, JComponent> getMap() {
        return map;
    }
}
