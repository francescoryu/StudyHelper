import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Anwendung extends JFrame {
    public JTextArea area;
    public JLabel anwendungLabel;
    public JButton add;
    public JButton clear;
    public JTextField addTaskField;
    public JScrollPane scrollPane;
    int cntr = 1;

    public Anwendung() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {


        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(Color.decode("#b5b8ff"));

        anwendungLabel = new JLabel("BucketList");
        anwendungLabel.setBounds(10, 10, 200, 40);
        anwendungLabel.setFont(new Font("Serif", Font.BOLD, 30));
        this.add(anwendungLabel);

        addTaskField = new JTextField();
        addTaskField.setBounds(5, 55, 250, 30);
        addTaskField.setFont(new Font("Serif", Font.BOLD, 15));
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

        scrollPane = new JScrollPane(area);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(275, 55, 360, 280);
        scrollPane.setFont(new Font("Serif", Font.BOLD, 15));

        area = new JTextArea();
        area.setBounds(275, 55, 360, 280);
        area.setFont(new Font("Serif", Font.BOLD, 15));
        area.setEditable(false);
        area.setLineWrap(true);

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




        scrollPane = new JScrollPane(area);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(275, 55, 360, 280);
        scrollPane.setFont(new Font("Serif", Font.BOLD, 15));
        this.add(scrollPane);

        add = new JButton("Hinzufügen");
        add.setBounds(5, 295, 100, 40);
        add.setFont(new Font("Serif", Font.BOLD, 12));


        this.add(add);

        clear = new JButton("Löschen");
        clear.setBounds(110, 295, 100, 40);
        clear.setFont(new Font("Serif", Font.BOLD, 12));
        clear.addActionListener(e ->

        {
            area.setText(null);
            cntr = 1;
        });
        this.add(clear);

        setTitle("TODO");

        setSize(700, 400);

        setVisible(true);

        setResizable(false);

        setLocation(350, 250);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}
