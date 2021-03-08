package zad3;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class TaskFrame extends JFrame {

    public TaskFrame() throws InterruptedException {

        setFrameProperties();

        TaskListModel<Future<Integer>> model = new TaskListModel<>();
        model.setElementList(getFutureList());
        JList<Future<Integer>> list = new JList<>(model);
        list.setCellRenderer(new TaskListCellRenderer());
        JScrollPane scrollPane = new JScrollPane(list);

        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout());

        JButton resultButton = new JButton("Podaj wynik");
        resultButton.addActionListener(e -> {
            if(list.getSelectedIndex() == -1) return;
            Future<Integer> currentTask = model.getElementAt(list.getSelectedIndex());
            if(currentTask.isDone()){
                String result = "";
                try {
                    result = currentTask.get(1, TimeUnit.SECONDS).toString();
                } catch (InterruptedException | ExecutionException | TimeoutException | CancellationException ex) {
                    result = "Brak wyniku. Zadanie zostalo przerwane.";
                }
                JOptionPane.showMessageDialog(this, result);
            }
            else {
                JOptionPane.showMessageDialog(this, "Zadanie jest jeszcze w trakcie obliczen.");
            }
        });

        JButton cancellationButton = new JButton("Anuluj");
        cancellationButton.addActionListener(e -> {
            if(list.getSelectedIndex() == -1) return;
            Future<Integer> currentTask = model.getElementAt(list.getSelectedIndex());
            currentTask.cancel(true);
            if(currentTask.isCancelled()){
                JOptionPane.showMessageDialog(this, "Zadanie: " +
                        currentTask.getClass().getSimpleName() + " " + list.getSelectedIndex() + " zostalo przerwane.");
            }
        });

        JButton checkStateButton = new JButton("Sprawdz stan");
        checkStateButton.addActionListener(e -> {
            if(list.getSelectedIndex() == -1) return;
            Future<Integer> currentTask = model.getElementAt(list.getSelectedIndex());
            String state = "";
            if(currentTask.isDone() && currentTask.isCancelled()){
                state = "Zadanie przerwane.";
            }
            else if(currentTask.isDone() && !currentTask.isCancelled()){
                state = "Zadanie ukonczone.";
            }
            else state = "Zadanie w trakcie wykonywania.";

            JOptionPane.showMessageDialog(this, state);
        });

        buttonPanel.add(resultButton, Component.LEFT_ALIGNMENT);
        buttonPanel.add(checkStateButton, Component.CENTER_ALIGNMENT);
        buttonPanel.add(cancellationButton, Component.RIGHT_ALIGNMENT);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

    }

    private void setFrameProperties() {
        setTitle("Task Manager");
        setVisible(true);
        setSize(400, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private List<Future<Integer>> getFutureList() throws InterruptedException {

        List<Callable<Integer>> list = Arrays.asList(
                new Timer(),
                new Timer(),
                new Timer()
        );


        ExecutorService service = Executors.newCachedThreadPool();

        List<Future<Integer>> futures = list.stream()
                .map(service::submit)
                .collect(Collectors.toList());

        service.shutdown();

        return futures;
    }


}
