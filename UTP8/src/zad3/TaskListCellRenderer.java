package zad3;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Future;

public class TaskListCellRenderer extends JLabel implements ListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        @SuppressWarnings("unchecked")
        Future<Integer> task = (Future<Integer>) value;

        setText(task.getClass().getSimpleName() + " " + index);
        setHorizontalAlignment(JLabel.CENTER);
        setOpaque(true);

        if(isSelected){
            setFont(new Font("SansSerif", Font.BOLD, 16));
            setBackground(new Color(0x697EE8));
        }
        else {
            setFont(new Font("SansSerif", Font.PLAIN, 14));
            setBackground(Color.WHITE);
        }

        return this;
    }
}
