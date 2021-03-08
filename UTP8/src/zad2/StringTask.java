package zad2;

public class StringTask implements Runnable {

    final private String text;
    final private int number;

    private Thread thread;

    private String result;
    private volatile State state;

    public StringTask(String text, int number) {
        this.number = number;
        this.text = text;
        this.thread = new Thread(this);
        this.result = "";
        this.state = State.CREATED;
    }

    public boolean isDone() {
        return state == State.READY || state == State.ABORTED;
    }

    public void start() {
        thread.start();
    }

    public void abort() {
        thread.interrupt();
    }

    public State getState() {
        return state;
    }

    public String getResult() {
        return result;
    }

    @Override
    public void run() {
        state = State.RUNNING;

        for (int i = 0; i < number; i++) {

            if(thread.isInterrupted()){
                state = State.ABORTED;
                return;
            }

           result += text;
        }

        state = State.READY;
    }

    enum State {

        RUNNING, ABORTED, READY, CREATED

    }
}
