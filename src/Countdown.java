import javax.swing.*;
import java.awt.*;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class Countdown extends JFrame {
    public JTextField inputTime;
    public JButton startTimer;
    public JLabel timerLabel;
    public JLabel timerPassed;
    public JComboBox<String> comboBox;

    public String[] whatToDo = {"Study", "Homework", "Something else"};

    public Countdown() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(Color.decode("#b5b8ff"));

        inputTime = new JTextField();
        timerLabel = new JLabel("Timer");
        comboBox = new JComboBox<>(whatToDo);
        startTimer = new JButton("Start");
        timerPassed = new JLabel();

        comboBox.setBounds(40, 55, 140, 30);
        comboBox.setFont(new Font("Geneva", Font.BOLD, 15));
        this.add(comboBox);

        timerLabel.setBounds(40, 10, 200, 40);
        timerLabel.setFont(new Font("Geneva", Font.BOLD, 30));
        this.add(timerLabel);

        inputTime.setBounds(40, 100, 140, 30);
        inputTime.setFont(new Font("Geneva", Font.BOLD, 20));
        inputTime.setText("40");

        this.add(inputTime);

        timerPassed.setBounds(40, 140, 140, 30);
        timerPassed.setFont(new Font("Geneva", Font.BOLD, 15));
        timerPassed.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        timerPassed.setText("0 Min 0 Secs");
        this.add(timerPassed);

        startTimer.setBounds(40, 180, 140, 30);
        startTimer.setFont(new Font("Geneva", Font.BOLD, 15));
        this.add(startTimer);
        startTimer.addActionListener(e -> new Thread(() -> {
            for (int minutes = 0; minutes < Integer.parseInt(inputTime.getText()); minutes++) {
                inputTime.setEditable(false);
                startTimer.setEnabled(false);
                comboBox.setEnabled(false);
                for (int seconds = 0; seconds < 60; seconds++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    timerPassed.setText(minutes + " Min " + seconds + " Secs");
                    System.out.println(minutes + ":" + seconds);

                }

            }
            comboBox.setEnabled(true);
            inputTime.setEditable(true);
            startTimer.setEnabled(true);
            JOptionPane.showMessageDialog(this, "Time's up!! Do a break :)",
                    "TIMER", INFORMATION_MESSAGE);
            timerPassed.setText("0 Min 0 Secs");
        }).start());

        setTitle("Program");
        setSize(240, 300);
        setVisible(true);
        setResizable(false);
        setLocation(350, 250);
    }
}
