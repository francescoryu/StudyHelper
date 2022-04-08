import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Anwendung extends JFrame {
    public JTextArea area;
    public JLabel anwendungLabel;
    public JButton add;
    public Anwendung() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Hinzufuegen hinzufuegen = new Hinzufuegen();
        hinzufuegen.setVisible(false);
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(Color.decode("#b5b8ff"));

        anwendungLabel = new JLabel("Anwendung");
        anwendungLabel.setBounds(10, 10, 200, 40);
        anwendungLabel.setFont(new Font("Serif", Font.BOLD, 30));
        this.add(anwendungLabel);

        area = new JTextArea();
        area.setBounds(10, 55, 400, 280);
        area.setFont(new Font("Serif", Font.BOLD, 15));
        area.setEditable(false);
        this.add(area);

        add = new JButton("Hinzufügen");
        add.setBounds(450, 295, 100, 40);
        add.setFont(new Font("Serif", Font.BOLD, 12));
        this.add(add);

        add.addActionListener(e -> {
            hinzufuegen.setVisible(true);
        });

        setSize(600, 400);
        setVisible(true);
        setResizable(false);
        setLocation(350, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}
