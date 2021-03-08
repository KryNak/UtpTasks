package zad3;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;

public class TaskListModel<E> implements ListModel<E> {

    private List<E> elementList;

    public TaskListModel(){
        elementList = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return elementList.size();
    }

    @Override
    public E getElementAt(int index) {
        return elementList.get(index);
    }

    private List<ListDataListener> listeners;

    @Override
    public void addListDataListener(ListDataListener l) {
        listeners.add(l);
        inform();
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listeners.remove(l);
        inform();
    }

    private void inform(){
        ListDataEvent listDataEvent = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, getSize());
        listeners.forEach(e -> e.contentsChanged(listDataEvent));
    }

    public void setElementList(List<E> elementList) {
        this.elementList.addAll(elementList);
        inform();
    }

    public void add(E element){
        elementList.add(element);
        inform();
    }

    public void remove(int index){
        if(elementList.size() > index){
            elementList.remove(index);
            inform();
        }
    }
}
