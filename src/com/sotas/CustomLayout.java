package com.sotas;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CustomLayout implements LayoutManager {
    private Map<String, Component> componentMap = new HashMap<String, Component>();
    @Override
    public void addLayoutComponent(String name, Component comp) {
        componentMap.put(name, comp);
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        componentMap.remove(comp);
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return parent.getSize();
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return parent.getSize();
    }

    @Override
    public void layoutContainer(Container parent) {

    }
}
