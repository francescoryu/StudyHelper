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

    public Login(Menu menu) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        menu.setVisible(true);
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(Color.decode("#b5b8ff"));

        name = new JTextField();
        pwd = new JPasswordField();

        loginLabel = new JLabel("Login");
        loginLabel.setBounds(10, 10, 200, 40);
        loginLabel.setFont(new Font("Geneva", Font.BOLD, 30));

        nameText = new JLabel("Name:");
        nameText.setBounds(30, 80, 200, 40);
        nameText.setFont(new Font("Geneva", Font.BOLD, 20));

        pwdText = new JLabel("Password:");
        pwdText.setBounds(30, 120, 200, 40);
        pwdText.setFont(new Font("Geneva", Font.BOLD, 20));

        name = new JTextField();
        name.setBounds(130, 80, 300, 40);
        name.setFont(new Font("Geneva", Font.BOLD, 20));

        pwd = new JPasswordField();
        pwd.setBounds(130, 120, 300, 40);
        pwd.setFont(new Font("Geneva", Font.BOLD, 20));

        login = new JButton("Login");
        login.setBounds(290, 200, 140, 40);
        login.setFont(new Font("Geneva", Font.BOLD, 15));

        login.addActionListener(e -> {
            if (name.getText().equals(userName) && pwd.getText().equals(passWord)) {
                this.setVisible(false);
                menu.setVisible(true);
            } else {
                showMessageDialog(null, "Name and/or Password wrong!", "Invalid", ERROR_MESSAGE);
            }
        });

        this.add(login);
        this.add(name);
        this.add(pwd);
        this.add(loginLabel);
        this.add(nameText);
        this.add(pwdText);

        setTitle("Login");

        setSize(500, 300);
        setVisible(true);
        setLocation(350, 200);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
