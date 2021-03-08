package zad1;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Letters {

    private final String sequence;
    private List<Thread> threads;

    public Letters(String sequence) {
        this.sequence = sequence;

        this.threads = Stream.of(sequence.split(""))
                .map(letter -> new Thread(() -> {
                    while (true) {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                            System.out.print(letter);
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                }, "Thread " + letter))
                .collect(Collectors.toList());
    }

    public List<Thread> getThreads() {
        return threads;
    }
}
