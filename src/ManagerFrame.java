package hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ManagerFrame extends JFrame implements ActionListener {
    JButton updateCustomerButton;
    JButton updateReceptionistButton;
    JButton customerDetailButton;

    Receptionist x = new Receptionist();
    JPasswordField psw = new JPasswordField();
    JButton openButton;
    JTextField mgrIdText;
    Manager mg = new Manager();
    Container c;


    ManagerFrame() {
         c = this.getContentPane();
        c.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700, 450);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.BLACK);

        JLabel title = new JLabel("* Manager Login *");
        title.setForeground(Color.YELLOW);
        title.setBounds(220, 15, 265, 30);
        title.setFont(new Font("MV Boli", Font.ITALIC, 30));

        JLabel mgrIDLabel = new JLabel("Enter Manager ID: ");
        mgrIDLabel.setForeground(Color.YELLOW);
        mgrIDLabel.setFont(new Font("MV Boli", Font.ITALIC, 17));
        mgrIDLabel.setBounds(150, 110, 230, 30);

        mgrIdText = new JTextField();
        mgrIdText.setBounds(300, 110, 100, 30);

        JLabel passwordLabel = new JLabel("Enter Password: ");
        passwordLabel.setForeground(Color.YELLOW);
        passwordLabel.setFont(new Font("MV Boli", Font.ITALIC, 17));
        passwordLabel.setBounds(169, 160, 230, 30);

        psw.setBounds(300, 160, 100, 30);
        psw.setFont(new Font("MV Boli", Font.ITALIC, 30));


        openButton = new JButton();
        openButton.setText("Open");
        openButton.setBounds(295, 220, 80, 30);
        openButton.setForeground(Color.BLACK);
        openButton.setBackground(Color.green);
        openButton.setFocusable(false);
        openButton.addActionListener(this);

        c.add(title);
        c.add(mgrIDLabel);
        c.add(passwordLabel);
        c.add(mgrIdText);
        c.add(psw);
        c.add(openButton);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {
            if (mgrIdText.getText().isBlank() || psw.getText().isBlank()){
                JOptionPane.showMessageDialog(null, "blank field");
            }
            else if (mg.validateID(mgrIdText.getText()) && mg.validatePsw(psw.getText())){
                mgrIdText.setText("");
                psw.setText("");
               new ReportTableFrame();
            }
            else
                JOptionPane.showMessageDialog(null, "manager doesn't exist");
        }
    }
}
