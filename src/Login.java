import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class Login extends JFrame {

    JButton login;

    JTextField name;
    JPasswordField pwd;

    JLabel loginLabel;
    JLabel nameText;
    JLabel pwdText;

    final String userName = "Dianyra";
    final String passWord = "180621";

    public Login(Anwendung anwendung) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        anwendung.setVisible(true);
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(Color.decode("#b5b8ff"));

        name = new JTextField();
        pwd = new JPasswordField();

        loginLabel = new JLabel("Anmeldung");
        loginLabel.setBounds(10, 10, 200, 40);
        loginLabel.setFont(new Font("Geneva", Font.BOLD, 30));

        nameText = new JLabel("Name:");
        nameText.setBounds(30, 80, 200, 40);
        nameText.setFont(new Font("Geneva", Font.BOLD, 20));

        pwdText = new JLabel("Passwort:");
        pwdText.setBounds(30, 120, 200, 40);
        pwdText.setFont(new Font("Geneva", Font.BOLD, 20));

        name = new JTextField();
        name.setBounds(130, 80, 300, 40);
        name.setFont(new Font("Geneva", Font.BOLD, 20));

        pwd = new JPasswordField();
        pwd.setBounds(130, 120, 300, 40);
        pwd.setFont(new Font("Geneva", Font.BOLD, 20));

        login = new JButton("Anmelden");
        login.setBounds(290, 200, 140, 40);
        login.setFont(new Font("Geneva", Font.BOLD, 15));

        login.addActionListener(e -> {
            if (name.getText().equals(userName) && pwd.getText().equals(passWord)) {
                this.setVisible(false);
                anwendung.setVisible(true);
            } else {
                showMessageDialog(null, "Name und/oder Passwort falsch!", "Ungültig", ERROR_MESSAGE);
            }
        });

        this.add(login);
        this.add(name);
        this.add(pwd);
        this.add(loginLabel);
        this.add(nameText);
        this.add(pwdText);

        setTitle("Anmeldung");

        setSize(500, 300);
        setVisible(true);
        setLocation(350, 200);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
