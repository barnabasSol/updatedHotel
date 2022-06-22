package hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {
    JButton homeButton = new JButton();
    JButton loginButton = new JButton();
    JTextField usernameText = new JTextField();
    JPasswordField passwordText = new JPasswordField();


    LoginFrame() {
        Container c = this.getContentPane();
        this.setSize(700, 500);
        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.BLACK);
        c.setLayout(null);

        JLabel userNameLabel = new JLabel("login ID:");
        JLabel passwordLabel = new JLabel("Password: ");

        loginButton.setBounds(285, 250, 120, 30);
        loginButton.setText("Login");
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        homeButton.setBounds(285, 310, 120, 30);
        homeButton.setText("Home");
        homeButton.setFocusable(false);
        homeButton.addActionListener(this);

        userNameLabel.setForeground(Color.WHITE);
        userNameLabel.setFont(new Font("MV Boli", Font.ITALIC, 20));
        userNameLabel.setBounds(165, 120, 200, 30);

        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("MV Boli", Font.ITALIC, 20));
        passwordLabel.setBounds(150, 190, 200, 30);



        usernameText.setFont(new Font("Thoma", Font.PLAIN, 20));
        usernameText.setBounds(250, 120, 200, 30);

        passwordText.setFont(new Font("Thoma", Font.PLAIN, 20));
        passwordText.setBounds(250, 190, 200, 30);

        c.add(usernameText);
        c.add(userNameLabel);
        c.add(passwordText);
        c.add(passwordLabel);
        c.add(loginButton);
        c.add(homeButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            Receptionist x = new Receptionist();
            x.loadRecLoginTable();
            String userId = usernameText.getText();
            String password = passwordText.getText();
            if (userId.isBlank() && password.isBlank()){
                JOptionPane.showMessageDialog(null, "field empty");
            }
            else if (userId.isBlank()) {
                JOptionPane.showMessageDialog(null, "empty username");
            }
            else if (password.isBlank()) {
                JOptionPane.showMessageDialog(null, "empty password");
            }
            else if (!x.checkID(userId) && !x.checkPassword(password)) {
                JOptionPane.showMessageDialog(null, "receptionist doesn't exist");
            } else if (x.checkID(userId) || x.checkPassword(password)) {
                boolean z = false;
                for (int i = 0; i < x.getReceptionistLoggerList().size(); i++) {
                    if (userId.equals(x.getReceptionistLoggerList().get(i).getRecLoginID()) &&
                            password.equals(x.getReceptionistLoggerList().get(i).getPassword())) {
                        z = true;
                        Receptionist.setCurrentRecepUser(userId);
                        this.setVisible(false);
                        new BookRoomFrame();
                        break;
                    }
                }
                if (!z) {
                    JOptionPane.showMessageDialog(null, "incorrect username or password");
                }
            }
        } else if (e.getSource() == homeButton) {
            this.setVisible(false);
            new HomeFrame();
        }
    }
}
