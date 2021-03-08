package zad1;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

class JTableModel implements TableModel {

    private List<List<String>> resultList;

    public JTableModel(TravelData travelData){
        resultList = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return resultList.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return resultList.get(rowIndex).get(columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    private List<TableModelListener> listeners;

    @Override
    public void addTableModelListener(TableModelListener tableModelListener) {
        listeners.add(tableModelListener);
    }

    @Override
    public void removeTableModelListener(TableModelListener tableModelListener) {
        listeners.remove(tableModelListener);
    }

    private void inform(){

        TableModelEvent tme = new TableModelEvent(this, 0, getRowCount());
        listeners.forEach((l) -> l.tableChanged(tme)
        );

    }

    public void setTableList(List<List<String>> list){
        resultList = list;
        inform();
    }
}
