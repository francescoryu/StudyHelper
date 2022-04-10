import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Anwendung extends JFrame {
    public JTextArea area;
    public JLabel todoListLabel;
    public JButton clear;
    public JTextField addTaskField;
    public JScrollPane scrollPane;
    public int cntr = 1;
    public JCheckBox checkBox;


    public Anwendung() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
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


        try {
            File myObj = new File("todo.txt");
            FileWriter fw = new FileWriter(myObj.getAbsoluteFile(), true);
            area.write(fw);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        clear.setBounds(5, 380, 100, 40);
        clear.setFont(new Font("Geneva", Font.BOLD, 15));
        clear.addActionListener(e ->
        {
            area.setText(null);
            cntr = 1;
        });
        this.add(clear);

        setTitle("Program");
        setSize(1000, 650);
        setVisible(true);
        setResizable(false);
        setLocation(350, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}
