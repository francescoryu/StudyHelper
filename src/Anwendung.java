import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Anwendung extends JFrame {
    public JTextArea area;
    public JLabel todoListLabel;
    public JButton clear;
    public JTextField addTaskField;
    public JScrollPane scrollPane;
    public int cntr = 1;
    public JCheckBox checkBox;
    public static String fileText;


    public Anwendung() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(Color.decode("#b5b8ff"));


        addTaskField = new JTextField();
        area = new JTextArea();
        todoListLabel = new JLabel("ToDo-List");
        checkBox = new JCheckBox("MOIN");
        clear = new JButton("Löschen");
        scrollPane = new JScrollPane(area);


        todoListLabel.setBounds(110, 10, 200, 40);
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

        this.readFile();

        area.setText(fileText);

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