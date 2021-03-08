package zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

class GoodReader extends Thread {

    private GoodCollector goodCollector;

    GoodReader(GoodCollector goodCollector)  {
        this.goodCollector = goodCollector;
    }

    public void run() {
        int counter = 0;

        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get("../Towary.txt"));

            String line;
            while(true){

                line = reader.readLine();

                Optional<Good> good;
                if(line != null){
                    String[] goodIdAndWeight = line.split(" ");
                    good = Optional.of(new Good(goodIdAndWeight[0], goodIdAndWeight[1]));
                    ++counter;
                }
                else {
                    good = Optional.empty();
                }

                goodCollector.addGood(good);

                if(!good.isPresent()) return;

                if(counter % 200 == 0){
                    System.out.println("utworzono " + counter + " obiektów");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
