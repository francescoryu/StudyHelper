import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Anwendung extends JFrame {
    public JTextArea area;
    public JLabel todoListLabel;
    public JLabel timerLabel;
    public JLabel timerPassed;
    public JButton clear;
    public JButton startTimer;
    public JTextField addTaskField;
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

        timerLabel.setBounds(450, 10, 200, 40);
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

        clear.setBounds(5, 380, 100, 40);
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

        timerPassed.setBounds(450, 160, 130, 30);
        timerPassed.setFont(new Font("Geneva", Font.BOLD, 20));
        timerPassed.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(timerPassed);

        this.readFile();

        area.setText(fileText);

        startTimer.setBounds(450, 200, 115, 45);
        startTimer.setFont(new Font("Geneva", Font.BOLD, 15));
        this.add(startTimer);
        startTimer.addActionListener(e -> {
            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            final Runnable runnable = new Runnable() {
                int countdownStarter = 2400;
                String currentTime;

                public void run() {
                    timerPassed.setText("Secs: " + countdownStarter);
                    countdownStarter--;

                    if (countdownStarter < 0) {
                        timerPassed.setText("Done");
                        scheduler.shutdown();
                    }
                }
            };
            scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
        });
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