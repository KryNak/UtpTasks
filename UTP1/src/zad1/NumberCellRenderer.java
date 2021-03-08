package zad1;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class NumberCellRenderer implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);

        if (value instanceof Integer) {
            int number = (Integer) value;
            label.setText(number + "");
            if (number > 20_000) {
                label.setForeground(Color.RED);
            }
        }

        return label;
    }

}
