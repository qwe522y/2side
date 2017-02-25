package com.sotas;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class TipRegZnakListDialog extends ListDialog {
    private ComponentMap cm;
    public TipRegZnakListDialog(ComponentMap cm) {
        super(normalizeList4ListDialog(TipRegZnakList.getList(null)), null, "Выбор регистрационного знака", new Dimension(500, 500));
        this.cm = cm;
    }

    private static String[][] normalizeList4ListDialog(String[] rows) {
        String[][] rows2 = new String[rows.length][1];
        for(int i=0; i<rows.length; i++) rows2[i][0] = rows[i];
        return rows2;
    }

    @Override
    public void setVisible(boolean flag) {
        if(flag) {
            final String[] rows = TipRegZnakList.getList(((JTextField)cm.getMap().get(StrConst.nomer)).getText());
            table.setModel(new AbstractTableModel() {
                @Override
                public int getRowCount() {
                    return rows.length;
                }
                @Override
                public int getColumnCount() {
                    return 1;
                }
                @Override
                public Object getValueAt(int rowIndex, int columnIndex) {
                    return rows[rowIndex];
                }
            });
        }
        super.setVisible(flag);
    }
}

class TipRegZnakList {
    static Map<Integer, Pattern> patternMap = new HashMap<>();
    static Map<Integer, String[]> listMap = new HashMap<>();
    static {
        patternMap.put(1, Pattern.compile("^[А-Я]{1}\\d\\d\\d[А-Я]{1}[А-Я]{1}\\d\\d$"));
        patternMap.put(2, Pattern.compile("^[А-Я]{1}[А-Я]{1}\\d\\d\\d[А-Я]{1}\\d\\d$"));
        patternMap.put(3, Pattern.compile("^[А-Я]{1}[А-Я]{1}\\d\\d\\d\\d\\d\\d$"));
        patternMap.put(4, Pattern.compile("^[А-Я]{1}\\d\\d\\d\\d[А-Я]{1}[А-Я]{1}$"));

        listMap.put(1, new String[] {
                "Автомобиль легковой.грузовой и автобусы (белый)",
                "Мотоциклы, мотороллеры, мопеды и мотонарты (желтый)",
                "Автомобили легковые, грузовые и автобусы(тип 1 исполнение 2, белый)",
                "Прочие государственные регистрационные знаки",
                "(201)Украинские легковые, грузовые автомобили и прицепы",
                "(202)Украинские легковые автомобили 1995-2004г",
                "(203)Украинские Транзитные номера"
        });
        listMap.put(2, new String[]{
                "Автомобили прочих иностранных представительств(желтый, старый гост)",
                "Автомобиль легковой.грузовой и автобусы (тип 1 исполнение 2, белый)",
                "Транзит (тип 15, пластик)",
                "Прочие государственные регистрационные знаки",
                "(201)Украинские легковые, грузовые автомобили и прицепы",
                "(202)Украинские легковые автомобили 1995-2004г",
                "(203)Украинские Транзитные номера"
        });
        listMap.put(3, new String[]{
                "Автомобильные прицепы и полуприцепы (белый)",
                "Прицепы в/ч (черный)",
                "Транзит (белый)",
                "Транзитные знаки мотоциклов (временное участие в движении)",
                "Транзит (выезд за пределы РФ)(белый, буква \"Т\")",
                "Автомобили легковые, грузовые и автобусы (тип 1 исполнение 2, белый)",
                "Коммерческий пассажирский автотранспорт (тип 1Б, желтый)",
                "Прочие государственные регистрационные знаки",
                "(201)Украинские легковые, грузовые автомобили и прицепы",
                "(202)Украинские легковые автомобили 1995-2004г",
                "(203)Украинские Транзитные номера"
        });
        listMap.put(3, new String[]{
                "Легковой индивидуальный (белый)",
                "Автомобили легковые, грузовые и автобусы (тип 1 исполнение 2, белый)",
                "Прочие государственные регистрационные знаки",
                "(201)Украинские легковые, грузовые автомобили и прицепы",
                "(202)Украинские легковые автомобили 1995-2004г",
                "(203)Украинские Транзитные номера"
        });
    }

    public static String[] getList(String nomer) {
        if(nomer == null) nomer = "default";
        for(Map.Entry<Integer, Pattern> e : TipRegZnakList.patternMap.entrySet()) {
            if (e.getValue().matcher(nomer.toUpperCase()).matches()) {
                return TipRegZnakList.listMap.get(e.getKey());
            }
        }
        return new String[] {
            "Автомобиль легковой.грузовой и автобусы (белый)",
            "Автомобильные прицепы и полуприцепы (белый)",
            "Мотоциклы, мотороллеры, мопеды и мотонарты (белый)",
            "Автотранспорт в/ч (черный)",
            "Прицеп в/ч (черный)",
            "Трацторы в/ч (черный)",
            "Мототранспорт в/ч (черный)",
            "Автомобили глав дип. представительств(красный)",
            "Автомобили дип. представительств(красный)",
            "Автомобили органов печати, МИД России, ин.представ.(старый гост)",
            "Автомобили прочих ин. представительств(желтый, старый гост)",
            "Автомобильные прицепы и полуприцепы(желтый, старый гост)",
            "Мотоциклы, мотороллеры, мопеды и мотонарты(желтый)",
            "Транзит (белый)",
            "Транзитный знаки мотоциклов(временное участие в движении)",
            "Транзит (выезд за пределы РФ) (белый, буква \"Т\")",
            "Легковой предприятий (белый)",
            "Легковой индивидуальный (белый)",
            "Грузовые автомобили (автобусы) (белый)",
            "Прицеп, полуприцеп (старый гост)",
            "Мототранспорт (старый гост)",
        };
    }
}