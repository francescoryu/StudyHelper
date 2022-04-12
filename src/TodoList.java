import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TodoList extends JFrame {

    public JTextArea area;

    public JLabel todoListLabel;

    public JButton clear;

    public JTextField addTaskField;

    public JScrollPane scrollPane;

    public int cntr = 1;
    public static String fileText;

    public TodoList() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(Color.decode("#b5b8ff"));


        addTaskField = new JTextField();
        area = new JTextArea();
        todoListLabel = new JLabel("ToDo-List");
        clear = new JButton("Delete");
        scrollPane = new JScrollPane(area);

        todoListLabel.setBounds(120, 10, 200, 40);
        todoListLabel.setFont(new Font("Geneva", Font.BOLD, 30));
        this.add(todoListLabel);

        addTaskField.setBounds(10, 55, 360, 30);
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
        scrollPane.setBounds(10, 90, 360, 280);
        scrollPane.setFont(new Font("Geneva", Font.BOLD, 15));

        area.setBounds(10, 90, 360, 280);
        area.setFont(new Font("Geneva", Font.BOLD, 15));
        area.setEditable(false);
        area.setLineWrap(true);
        this.add(scrollPane);

        clear.setBounds(120, 380, 140, 30);
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

        setTitle("ToDO-List");
        setSize(395, 500);
        setVisible(true);
        setResizable(false);
        setLocation(350, 250);
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
