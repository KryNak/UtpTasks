package zad1;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountryTable {

    private String[] headersTab;
    private List<Object[]> elementList;

    public CountryTable(String countriesFileName) {

        headersTab = new String[4];
        elementList = new ArrayList<>();
        Object[] elements = null;

        try {
            Scanner input = new Scanner(Paths.get(countriesFileName));
            input.useDelimiter("\t|\n");

            int counter = 0;
            boolean passHeaders = true;
            while (input.hasNext()) {

                if (passHeaders) {
                    headersTab[counter] = input.next().trim();
                    if (counter == 3) {
                        headersTab[0] = "Flag";
                        passHeaders = false;
                    }
                } else {
                    if (counter % 4 == 0) elements = new Object[4];
                    elements[counter % 4] = input.next().trim();
                    if (counter % 4 == 3) {
                        elements[counter % 4] = Integer.parseInt(elements[counter % 4].toString()) / 1000;
                        elementList.add(elements);
                    } else if (counter % 4 == 0) {
                        elements[counter % 4] = new ImageIcon("data/flags/" + elements[counter % 4].toString() + ".png").getImage().getScaledInstance(125, 50, Image.SCALE_FAST);
                    }
                }

                counter++;
            }

            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public JTable create() {
        JTable table = new JTable(new MyTableModel(headersTab, elementList));

        DefaultTableCellRenderer cellRenderer = createDefaultTableCellRenderer();
        for (int i = 1; i <= 2; i++) {
            table.getColumn(headersTab[i]).setCellRenderer(cellRenderer);
        }
        table.setDefaultRenderer(Integer.class, new NumberCellRenderer());
        table.setDefaultRenderer(Image.class, new FlagCellRenderer());

        table.setRowMargin(20);
        table.setRowHeight(100);
        table.setEnabled(false);
        table.setBackground(new Color(243, 243, 243));

        return table;
    }

    public DefaultTableCellRenderer createDefaultTableCellRenderer() {

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);

        return cellRenderer;
    }

}
