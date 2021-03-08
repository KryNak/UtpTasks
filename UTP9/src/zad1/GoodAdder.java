package zad1;

import java.util.Optional;

class GoodAdder extends Thread {

    private GoodCollector goodCollector;

    GoodAdder(GoodCollector goodCollector) {
        this.goodCollector = goodCollector;
    }

    public void run() {
        int counter = 0;
        double sum = 0;

        Optional<Good> optionalGood = goodCollector.getGood();
        if(optionalGood.isPresent()){
            ++counter;
            sum += optionalGood.get().getWeight();
        }

        while (optionalGood.isPresent()) {

            if (counter % 100 == 0) {
                System.out.println("policzono wage " + counter +" towarów");
            }

            optionalGood = goodCollector.getGood();
            if(optionalGood.isPresent()){
                ++counter;
                sum += optionalGood.get().getWeight();
            }
        }

        System.out.println(sum);

    }

}

