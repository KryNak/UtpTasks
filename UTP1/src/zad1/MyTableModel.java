package zad1;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class MyTableModel implements TableModel {

    private String[] headers;
    private List<Object[]> elementList;

    public MyTableModel(String[] headers, List<Object[]> elementList) {
        this.headers = headers;
        this.elementList = elementList;
    }

    @Override
    public int getRowCount() {
        return elementList.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return headers[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        if (columnIndex == 3) {
            return Integer.class;
        }
        else if(columnIndex == 0){
            return Image.class;
        }

        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return elementList.get(rowIndex)[columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
