package zad1;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList<E> implements List<E> {

    private List<E> list;

    public XList() {
        list = new ArrayList<>();
    }

    public XList(E... a) {
        list = new ArrayList<>(Arrays.asList(a));
    }

    public XList(Collection<? extends E> a) {
        list = new ArrayList<>(a);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(E t) {
        return list.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return list.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return list.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        return list.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
    }

    @Override
    public E remove(int index) {
        return list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

    public static <T> XList<T> of(T... a) {
        return new XList<>(a);
    }

    public static <T> XList<T> of(Collection<? extends T> a) {
        return new XList<>(a);
    }

    public static XList<String> charsOf(String str) {
        String[] strArr = str.split("");
        return new XList<>(strArr);
    }

    public static <T> XList<String> tokensOf(String str) {
        String[] strArr = str.split(" ");
        return new XList<>(strArr);
    }

    public static XList<String> tokensOf(String str, String delimiter) {
        String[] strArr = str.split(delimiter);
        return new XList<>(strArr);
    }

    public XList<E> union(Collection<? extends E> list2) {
        XList<E> copy = new XList<>(list);
        copy.addAll(list2);
        return copy;
    }

    public XList<E> union(E... a) {
        XList<E> copy = new XList<>(list);
        copy.addAll(Arrays.asList(a));
        return copy;
    }

    public XList<E> diff(Collection<? extends E> a) {
        XList<E> copy = new XList<>(list);
        return copy.stream().filter(e -> !a.contains(e)).collect(Collectors.toCollection(XList::new));
    }

    public XList<E> unique() {

        XList<E> arr = new XList<>();
        for (E t : list) {
            if (!arr.contains(t)) {
                arr.add(t);
            }
        }

        return arr;
    }

    public <T extends String> XList<E> combine() {
        XList<List<T>> xList = new XList<>((Collection<? extends List<T>>) list);

        XList<List<T>> product = new XList<>();

        if (xList.size() > 1) {

            product = firstCartesianProduct(xList.get(0), xList.get(1));

            for (int i = 2; i < xList.size(); i++) {
                product = restOfCartesianProducts(product, xList.get(i));
            }

        }


        return (XList<E>) product;
    }

    private <T> XList<List<T>> firstCartesianProduct(List<T> a, List<T> b) {
        XList<List<T>> lists = new XList<>();

        for (T t1 : b) {
            for (T t2 : a) {
                lists.add(new XList<>(Arrays.asList(t1, t2)));
            }
        }

        return lists;
    }

    private <T extends String> XList<List<T>> restOfCartesianProducts(List<List<T>> a, List<T> b) {
        XList<List<T>> lists = new XList<>();

        for (T t1 : b) {
            for (List<T> t2 : a) {

                XList<T> list = new XList<>(t2);
                list.add(t1);
                list.sort(Comparator.reverseOrder());
                lists.add(list);

            }
        }

        return lists;
    }


    public <T extends XList, R> XList<R> collect(Function<T, R> function) {
        XList<List<String>> basicList = new XList<>((Collection<? extends List<String>>) list);
        XList<R> returnList = new XList<>();

        for(List<String> e : basicList){
            returnList.add(function.apply((T)e));
        }

        return returnList;
    }


    public String join(String string) {
        XList<E> listXList = new XList<>(list);
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < listXList.size(); i++) {
            stringBuilder.append(listXList.get(i));
            if(i < listXList.size() - 1) stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }

    public String join() {
        XList<E> listXList = new XList<>(list);
        StringBuilder stringBuilder = new StringBuilder();
        for (E t : listXList) {
            stringBuilder.append(t);
        }
        return stringBuilder.toString();
    }

    public void forEachWithIndex(BiConsumer<E, Integer> biConsumer) {
        for (int i = 0; i < list.size(); i++) {
            biConsumer.accept(list.get(i), i);
        }
    }

    @Override
    public String toString() {
        return list.toString();
    }

}
