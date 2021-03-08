package zad3;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Timer implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int time = 0;

        while(time != 60){

            try{
                TimeUnit.SECONDS.sleep(1);
                time++;
            }catch (InterruptedException e){
                return time;
            }

        }

        return time;
    }
}
