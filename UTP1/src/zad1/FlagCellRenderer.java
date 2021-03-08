package zad1;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class FlagCellRenderer implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);

        if(value instanceof Image){
            ImageIcon imageIcon = new ImageIcon((Image)value);
            label.setIcon(imageIcon);
        }

        return label;
    }
}
