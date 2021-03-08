package zad1;

import java.util.Optional;

class GoodCollector {

    private boolean newTxt = false;

    private Optional<Good> optionalGood;

    synchronized void addGood(Optional<Good> optionalGood) {
        while (newTxt) {
            try {
                wait();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        newTxt = true;

        this.optionalGood = optionalGood;

        notifyAll();
    }

    synchronized Optional<Good> getGood() {
        while (!newTxt) {
            try {
                wait();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        newTxt = false;

        notifyAll();
        return optionalGood;
    }

}

