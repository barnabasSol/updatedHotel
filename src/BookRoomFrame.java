package hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookRoomFrame extends JFrame implements ActionListener {
    Receptionist r = new Receptionist();
    public static String cD;
    JButton seekRoomButton;
    JButton logoutButton;
    JButton checkOutCustomerButton;
    JTextField firstNameText;
    JTextField lastNameText;
    JTextField PhoneNumberText;
    JTextField checkOutDateText;
    Report rp = new Report();

    BookRoomFrame() {
        Container c = this.getContentPane();
        c.setLayout(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700, 500);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.BLACK);

        JLabel label = new JLabel("Book Customer");
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
        lastNameText.setBounds(192, 145, 205, 30);
        c.add(lastNameText);

        JLabel enterPhoneNumLabel = new JLabel("enter phone number:");
        enterPhoneNumLabel.setBounds(45, 210, 220, 30);
        enterPhoneNumLabel.setForeground(Color.WHITE);
        enterPhoneNumLabel.setFont(new Font("Thoma", Font.PLAIN, 20));
        c.add(enterPhoneNumLabel);

        PhoneNumberText = new JTextField();
        PhoneNumberText.setBounds(230, 210, 220, 30);
        c.add(PhoneNumberText);

        JLabel enterCheckOutDateLabel = new JLabel("enter checkout date (yyyy-mm-dd):");
        enterCheckOutDateLabel.setBounds(45, 275, 350, 30);
        enterCheckOutDateLabel.setForeground(Color.WHITE);
        enterCheckOutDateLabel.setFont(new Font("Thoma", Font.PLAIN, 20));
        c.add(enterCheckOutDateLabel);

        checkOutDateText = new JTextField();
        checkOutDateText.setBounds(356, 275, 90, 30);
        c.add(checkOutDateText);


        logoutButton = new JButton();
        logoutButton.setText("Logout");
        logoutButton.setBounds(550, 100, 100, 30);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(Color.RED);
        logoutButton.setFocusable(false);
        logoutButton.addActionListener(this);
        c.add(logoutButton);

        seekRoomButton = new JButton();
        seekRoomButton.setText("Seek Rooms");
        seekRoomButton.setBounds(250, 360, 135, 30);
        seekRoomButton.setForeground(Color.BLACK);
        seekRoomButton.setBackground(Color.GREEN);
        seekRoomButton.setFocusable(false);
        seekRoomButton.addActionListener(this);
        c.add(seekRoomButton);


        checkOutCustomerButton = new JButton();
        checkOutCustomerButton.setText("Check Out");
        checkOutCustomerButton.setBounds(550, 50, 100, 30);
        checkOutCustomerButton.setForeground(Color.BLACK);
        checkOutCustomerButton.setBackground(Color.blue);
        checkOutCustomerButton.setFocusable(false);
        checkOutCustomerButton.addActionListener(this);
        c.add(checkOutCustomerButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == seekRoomButton) {
            Customer x = new Customer();
            String fname = firstNameText.getText();
            String lname = lastNameText.getText();
            String pnum = PhoneNumberText.getText();
            cD = checkOutDateText.getText();

            if (fname.isBlank() || lname.isBlank() || pnum.isBlank() || cD.isBlank()) {
                JOptionPane.showMessageDialog(null, "there is an empty field");
            } else if (!r.isNumeric(pnum) || !r.isLetters(fname) || !r.isLetters(lname) || !rp.isProperDate(cD)) {
                JOptionPane.showMessageDialog(null, "invalid format");
            } else if (x.redundantPhone(pnum)) {
                JOptionPane.showMessageDialog(null, "number already exists");
            } else {
                Customer c = new Customer();
                c.setCustomer_id(c.idGenerator());
                c.setFirst_name(fname);
                c.setLast_name(lname);
                c.setPhone_number(pnum);
                Customer.currentCustomer = c;
                c.addCustomer(Customer.currentCustomer.getCustomer_id(), Customer.currentCustomer.getFirst_name(),
                        Customer.currentCustomer.getLast_name(), Customer.currentCustomer.getPhone_number());

                new SeekRoomFrame();
                this.setVisible(false);
            }

        } else if (e.getSource() == checkOutCustomerButton) {
            new CheckOutFrame();
        } else if (e.getSource() == logoutButton) {
            this.setVisible(false);
            new HomeFrame();
        }
    }
}

