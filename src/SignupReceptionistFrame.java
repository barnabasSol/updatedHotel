package hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupReceptionistFrame extends JFrame implements ActionListener {
    Receptionist r = new Receptionist();
    JTextField firstNameText;
    JTextField lastNameText;
    JTextField phoneNumberText;
    JPasswordField passwordText;
    JButton doneButton;
    JButton cancelButton;

    SignupReceptionistFrame() {

        Container c = this.getContentPane();
        c.setLayout(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700, 500);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.BLACK);

        JLabel label = new JLabel("Receptionist Sign-Up");
        label.setBounds(245, 15, 220, 30);
        label.setForeground(Color.GREEN);
        label.setFont(new Font("MV Boli", Font.ITALIC, 20));
        c.add(label);

        JLabel enterFirstNameLabel = new JLabel("enter first name:");
        enterFirstNameLabel.setBounds(45, 80, 220, 30);
        enterFirstNameLabel.setForeground(Color.WHITE);
        enterFirstNameLabel.setFont(new Font("Thoma", Font.PLAIN, 20));
        c.add(enterFirstNameLabel);

        firstNameText = new JTextField();
        firstNameText.setBounds(192, 80, 200, 30);
        c.add(firstNameText);

        JLabel enterLastNameLabel = new JLabel("enter last name:");
        enterLastNameLabel.setBounds(45, 145, 220, 30);
        enterLastNameLabel.setForeground(Color.WHITE);
        enterLastNameLabel.setFont(new Font("Thoma", Font.PLAIN, 20));
        c.add(enterLastNameLabel);

        lastNameText = new JTextField();
        lastNameText.setBounds(192, 145, 203, 30);
        c.add(lastNameText);

        JLabel enterPhoneNumLabel = new JLabel("enter phone number:");
        enterPhoneNumLabel.setBounds(45, 210, 220, 30);
        enterPhoneNumLabel.setForeground(Color.WHITE);
        enterPhoneNumLabel.setFont(new Font("Thoma", Font.PLAIN, 20));
        c.add(enterPhoneNumLabel);

        phoneNumberText = new JTextField();
        phoneNumberText.setBounds(230, 210, 168, 30);
        c.add(phoneNumberText);

        JLabel enterPasswordLabel = new JLabel("enter your password:");
        enterPasswordLabel.setBounds(45, 270, 220, 30);
        enterPasswordLabel.setForeground(Color.WHITE);
        enterPasswordLabel.setFont(new Font("Thoma", Font.PLAIN, 20));
        c.add(enterPasswordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(235, 270, 166, 30);
        c.add(passwordText);

        doneButton = new JButton();
        doneButton.setText("done");
        doneButton.setBounds(210, 360, 100, 30);
        doneButton.setForeground(Color.BLACK);
        doneButton.setBackground(Color.GREEN);
        doneButton.setFocusable(false);
        doneButton.addActionListener(this);
        c.add(doneButton);

        cancelButton = new JButton();
        cancelButton.setText("cancel");
        cancelButton.setBounds(350, 360, 100, 30);
        cancelButton.setForeground(Color.BLACK);
        cancelButton.setBackground(Color.RED);
        cancelButton.setFocusable(false);
        cancelButton.addActionListener(this);
        c.add(cancelButton);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == doneButton) {
            r.loadPhoneList();
            if (firstNameText.getText().isBlank() || lastNameText.getText().isBlank() ||
                    phoneNumberText.getText().isBlank() || passwordText.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "there is an empty field");
            } else if (!r.isLetters(firstNameText.getText()) || !r.isLetters(lastNameText.getText())
                    || !r.isNumeric(phoneNumberText.getText())) {
                JOptionPane.showMessageDialog(null, "incorrect format");
            }
            else if (r.redundantPhone(phoneNumberText.getText())) {
                JOptionPane.showMessageDialog(null, "invalid phone number");
            }
            else {
                r.signupReceptionist(firstNameText.getText(), lastNameText.getText(), phoneNumberText.getText(),
                        passwordText.getText());
                r.loadRecLoginTable();
                String newID = r.getReceptionistLoggerList().get(r.getReceptionistLoggerList().size()-1).getRecLoginID();
                JOptionPane.showMessageDialog(null, "successfully signed up: "+ newID);
                this.setVisible(false);
                new HomeFrame();
            }
        }
    else if (e.getSource()==cancelButton){
        this.setVisible(false);
        new HomeFrame();
        }
    }
}
