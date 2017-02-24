package com.sotas;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class TipRegZnakListDialog extends ListDialog {
    private TableModel defaultTableModel;
    Map<Integer, Pattern> patternMap = new HashMap<>();
    Map<Integer, String[]> listMap = new HashMap<>();
    private JTextField nomerField = new JTextField();
    public TipRegZnakListDialog(JTextField nomerField) {
        super(Resource.getInstance().tipRegZnak, null, "Выбор регистрационного знака", new Dimension(500, 500));
        this.nomerField = nomerField;
        defaultTableModel = table.getModel();
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

    @Override
    public void setVisible(boolean flag) {
        if(flag) {
            TableModel newModel = defaultTableModel;
            for(Map.Entry<Integer, Pattern> e : patternMap.entrySet()) {
                final String[] rows = listMap.get(e.getKey());
                if(e.getValue().matcher(nomerField.getText().toUpperCase()).matches()) {
                    newModel = new AbstractTableModel() {
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
                    };
                    break;
                }
            }
            table.setModel(newModel);
        }
        super.setVisible(flag);
    }
}