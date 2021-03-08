package zad2;

@FunctionalInterface
public interface NoExcRunnable extends Runnable {

    void checkedRun() throws InterruptedException;

    @Override
    default void run(){
        try{
            checkedRun();
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
