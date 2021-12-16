package zad2;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MyFrame extends JFrame {

    public MyFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultListModel<Future<Integer>> listModel = new DefaultListModel<>();

        JList<String> leftPanel = new JList(listModel);
        leftPanel.setMinimumSize(new Dimension(400, 400));
        JPanel rightPanel = new JPanel();

        JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        pane.setResizeWeight(0.5);

        JButton startNewButton = new JButton("Start new");
        JButton stateButton = new JButton("State");
        JButton cancelButton = new JButton("Cancel");
        JButton resultButton = new JButton("Result");

        rightPanel.setLayout(new GridLayout(4, 1));
        rightPanel.setPreferredSize(new Dimension(300, 400));
        rightPanel.setMaximumSize(new Dimension(300, 400));

        rightPanel.add(startNewButton);
        rightPanel.add(stateButton);
        rightPanel.add(cancelButton);
        rightPanel.add(resultButton);

        startNewButton.addActionListener(
                e -> {
                    listModel.addElement(new SquareCalculator().calculate(10));
                }
        );

        stateButton.addActionListener(
                e -> {
                    int selected = leftPanel.getSelectedIndex();
                    if(selected != -1) {
                        try {
                            if (listModel.get(selected).isDone()) {
                                System.out.println("Task is done");
                            } else if (listModel.get(selected).isCancelled()) {
                                System.out.println("Task is cancelled");
                            } else {
                                System.out.println("Task is still processing");
                            }
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                }
        );

        cancelButton.addActionListener(
                e -> {
                    int selected = leftPanel.getSelectedIndex();
                    if(selected != -1) {
                        listModel.get(selected).cancel(true);
                        System.out.println("Task cancelled");
                        listModel.removeElementAt(selected);
                    }
                }
        );

        resultButton.addActionListener(
                e -> {
                    int selected = leftPanel.getSelectedIndex();
                    try {
                        if(selected != -1) {
                            System.out.println(listModel.get(selected).get());
                        }
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    } catch (ExecutionException executionException) {
                        executionException.printStackTrace();
                    }
                }
        );

        this.getContentPane().add(pane);
        this.setSize(800, 400);
        this.setVisible(true);
    }

}

//https://www.baeldung.com/java-future
//https://stackoverflow.com/questions/284899/how-do-you-add-an-actionlistener-onto-a-jbutton-in-java
//https://stackoverflow.com/questions/7309550/jlist-selected-item-to-string/7309578
//https://stackoverflow.com/questions/8176965/how-to-add-element-to-existing-jlist
//https://www.javatpoint.com/java-jlist