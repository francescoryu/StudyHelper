import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class Anwendung extends JFrame {

    public JLabel blackLine;

    public JTextArea area;

    public JLabel todoListLabel;
    public JLabel timerLabel;
    public JLabel timerPassed;

    public JButton clear;
    public JButton startTimer;
    public JTextField addTaskField;
    public JTextField inputTime;

    public JScrollPane scrollPane;

    public int cntr = 1;
    public static String fileText;
    public JComboBox<String> comboBox;
    public String[] whatToDo = {"Lernen", "Hausaufgaben", "Anderes"};

    public Anwendung() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, InterruptedException {

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(Color.decode("#b5b8ff"));


        addTaskField = new JTextField();
        inputTime = new JTextField();
        area = new JTextArea();
        todoListLabel = new JLabel("ToDo-List");
        timerLabel = new JLabel("Timer");
        comboBox = new JComboBox<>(whatToDo);
        clear = new JButton("Löschen");
        startTimer = new JButton("Start");
        scrollPane = new JScrollPane(area);
        timerPassed = new JLabel();


        comboBox.setBounds(450, 55, 140, 30);
        comboBox.setFont(new Font("Geneva", Font.BOLD, 15));
        this.add(comboBox);

        timerLabel.setBounds(470, 10, 200, 40);
        timerLabel.setFont(new Font("Geneva", Font.BOLD, 30));
        this.add(timerLabel);

        todoListLabel.setBounds(110, 10, 200, 40);
        todoListLabel.setFont(new Font("Geneva", Font.BOLD, 30));
        this.add(todoListLabel);

        addTaskField.setBounds(5, 55, 360, 30);
        addTaskField.setFont(new Font("Geneva", Font.BOLD, 15));
        addTaskField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String text = addTaskField.getText();
                    area.append(cntr + ". " + text + "\n");
                    addTaskField.setText("");
                    cntr++;
                    File file = new File("todo.txt");

                    PrintWriter printWriter = null;
                    try {
                        printWriter = new PrintWriter(file);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    printWriter.println(area.getText());
                    printWriter.close();
                }
            }
        });

        this.add(addTaskField);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(5, 90, 360, 280);
        scrollPane.setFont(new Font("Geneva", Font.BOLD, 15));

        area.setBounds(5, 90, 360, 280);
        area.setFont(new Font("Geneva", Font.BOLD, 15));
        area.setEditable(false);
        area.setLineWrap(true);
        this.add(scrollPane);

        clear.setBounds(5, 380, 140, 30);
        clear.setFont(new Font("Geneva", Font.BOLD, 15));
        clear.addActionListener(e ->
        {
            area.setText(null);
            cntr = 1;
            File file = new File("todo.txt");

            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(file);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            printWriter.println(area.getText());
            printWriter.close();


        });

        this.add(clear);
        this.readFile();
        area.setText(fileText);

        inputTime.setBounds(450, 100, 140, 30);
        inputTime.setFont(new Font("Geneva", Font.BOLD, 20));
        inputTime.setText("40");

        this.add(inputTime);

        timerPassed.setBounds(450, 140, 140, 30);
        timerPassed.setFont(new Font("Geneva", Font.BOLD, 15));
        timerPassed.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        timerPassed.setText("0 Min 0 Secs");
        this.add(timerPassed);

        startTimer.setBounds(450, 180, 140, 30);
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
        setSize(1000, 650);
        setVisible(true);
        setResizable(false);
        setLocation(350, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public void readFile() throws IOException {
        FileReader fileReader = new FileReader("todo.txt");
        BufferedReader br = new BufferedReader(fileReader);
        String str = br.readLine();
        while (str != null) {
            fileText = fileText + str + "\n";
            str = br.readLine();
        }
        System.out.println(fileText);
        fileReader.close();
    }
}
