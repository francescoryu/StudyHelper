import javax.swing.*;
import java.awt.*;

public class Hinzufuegen extends JFrame {
    public JLabel hinzufuegenLabel;
    public JTextArea area;
    public JButton hinzufuegen;

    public String text;

    public Hinzufuegen() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(Color.decode("#b5b8ff"));

        hinzufuegenLabel = new JLabel("Was musst du machen?");
        hinzufuegenLabel.setBounds(10, 10, 200, 40);
        hinzufuegenLabel.setFont(new Font("Serif", Font.BOLD, 17));
        this.add(hinzufuegenLabel);

        hinzufuegen = new JButton("Hinzufügen");
        hinzufuegen.setBounds(135, 160, 100, 40);
        hinzufuegen.setFont(new Font("Serif", Font.BOLD, 12));
        hinzufuegen.addActionListener(e -> {
            text = area.getText();
            area.selectAll();
            area.setText("");
        });
        this.add(hinzufuegen);

        area = new JTextArea();
        area.setBounds(10, 55, 350, 100);
        area.setFont(new Font("Serif", Font.BOLD, 15));
        area.setLineWrap(true);
        area.setEditable(true);
        this.add(area);


        setSize(400, 250);
        setVisible(true);
        setResizable(false);
        setLocation(450, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
