/**
 * @author Nakielski Krystian S20258
 */

package zad1;


public class Main {

    public static void main(String[] args) {
        GoodCollector t = new GoodCollector();
        Thread t1 = new GoodReader(t);
        Thread t2 = new GoodAdder(t);
        t1.start();
        t2.start();
    }
}
