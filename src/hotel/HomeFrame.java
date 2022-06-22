package hotel;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeFrame extends JFrame implements ActionListener {
    JButton receptionistLoginButton;
    JButton showReportButton;
    JButton receptionistSignupButton;
    HomeFrame() {
        Container c = this.getContentPane();
        c.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700, 500);
        this.setVisible(true);
        c.setBackground(Color.BLACK);


        Border border = BorderFactory.createLineBorder(Color.RED, 3);

        JLabel label = new JLabel("Welcome To Hotel X");
        label.setBorder(border);
        label.setForeground(Color.WHITE);
        label.setBounds(245, 15, 220, 30);
        label.setFont(new Font("MV Boli", Font.ITALIC, 20));

        receptionistLoginButton = new JButton();
        receptionistLoginButton.addActionListener(this);
        receptionistLoginButton.setBounds(250, 120, 200, 30);
        receptionistLoginButton.setText("Receptionist Login");
        receptionistLoginButton.setFocusable(false);

        receptionistSignupButton = new JButton();
        receptionistSignupButton.addActionListener(this);
        receptionistSignupButton.setBounds(250, 185, 200, 30);
        receptionistSignupButton.setText("Receptionist Sign-up");
        receptionistSignupButton.setFocusable(false);

        showReportButton = new JButton();
        showReportButton.addActionListener(this);
        showReportButton.setBounds(250, 250, 200, 30);
        showReportButton.setText("Show Report");
        showReportButton.setFocusable(false);

        c.add(showReportButton);
        c.add(receptionistLoginButton);
        c.add(receptionistSignupButton);
        c.add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == receptionistLoginButton) {
            this.setVisible(false);
            new LoginFrame();
        } else if (e.getSource() == showReportButton) {
            new ManagerFrame();
        }
        else if (e.getSource()==receptionistSignupButton){
            this.setVisible(false);
            new SignupReceptionistFrame();
        }
    }
}
