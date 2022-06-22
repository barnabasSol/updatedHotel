package hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

public class SeekRoomFrame extends JFrame implements ActionListener {
    Room r = new Room();
    int nor = 0;
    int c = 0;
    boolean confirmed = false;
    int limit = 1;
    JButton cancelButton;
    JButton doneButton;
    JButton confirmButton;
    JButton[] b = new JButton[r.getRoomList().size()];
    Customer cus = new Customer();
    CbR cbr = new CbR();
    Report rp = new Report();

    public SeekRoomFrame() {
        r.loadRoomTable();
        Container c = this.getContentPane();
        c.setLayout(null);
        this.setResizable(false);
        c.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(580, 450);
        int yPos = 5;
        int xPos = 60;
        int newLine = 4;
        for (int i = 0; i < r.getRoomList().size(); i++) {
            b[i] = new JButton();
            b[i].setText(String.valueOf(i + 1));
            b[i].setBounds(xPos, yPos, 50, 30);
            b[i].setFocusable(false);
            b[i].addActionListener(this);
            if (r.getRoomList().get(i).getRoomStatus() == 1) {
                b[i].setBackground(Color.RED);
            } else if (r.getRoomList().get(i).getRoomStatus() == 0 &&
                    r.getRoomList().get(i).getRoomType().equals("dlx")) {
                b[i].setBackground(Color.YELLOW);
            } else
                b[i].setBackground(Color.GREEN);
            c.add(b[i]);
            if (i == newLine) {
                xPos = 0;
                yPos += 50;
                newLine += 5;
            }
            xPos += 60;
        }
        this.setVisible(true);
        cancelButton = new JButton("cancel");
        confirmButton = new JButton("confirm");
        doneButton = new JButton("done");

        confirmButton.setFocusable(false);
        confirmButton.setBounds(410, 30, 100, 30);
        confirmButton.addActionListener(this);

        cancelButton.setFocusable(false);
        cancelButton.setBounds(410, 80, 100, 30);
        cancelButton.addActionListener(this);

        doneButton.setFocusable(false);
        doneButton.setBounds(410, 130, 100, 30);
        doneButton.addActionListener(this);

        c.add(cancelButton);
        c.add(confirmButton);
        c.add(doneButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < b.length; i++) {
            if (e.getSource() == b[i]) {
                r.loadRoomTable();
                if (r.getRoomList().get(i).getRoomType().equals("standard") && limit < 4 &&
                        r.getRoomList().get(i).getRoomStatus() == 0) {
                    ++limit;
                    ++nor;
                    Report.roomPrice += 300;
                    b[i].setBackground(Color.red);
                    cbr.chosenRoom(i + 1);
                    r.bookRoomAt(i + 1);
                    break;
                } else if (r.getRoomList().get(i).getRoomType().equals("dlx") && limit < 4 &&
                        r.getRoomList().get(i).getRoomStatus() == 0) {
                    ++limit;
                    ++nor;
                    Report.roomPrice += 600;
                    b[i].setBackground(Color.red);
                    cbr.chosenRoom(i + 1);
                    r.bookRoomAt(i + 1);
                    break;
                } else if (r.getRoomList().get(i).getRoomStatus() == 1) {
                    JOptionPane.showMessageDialog(null, "already booked");
                } else if (limit >= 4) {
                    JOptionPane.showMessageDialog(null, "past the limit");
                }
            }
        }
        if (e.getSource() == confirmButton) {
            ++c;
            confirmed = true;
            if (c > 1) {
                JOptionPane.showMessageDialog(null,
                        "already registered " + Customer.currentCustomer.getCustomer_id());
            } else {
                try {
                    rp.addReport(Customer.currentCustomer.getCustomer_id(), nor,
                            rp.calcTotalRoomPrice(BookRoomFrame.cD), BookRoomFrame.cD,
                            Receptionist.getCurrentRecepUser());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null,
                        "successfully booked " + Customer.currentCustomer.getCustomer_id());
            }
        } else if (e.getSource() == cancelButton) {
                Report.roomPrice = 0;
                nor = 0;
                limit = 0;
                c = 0;
                for (int i = 0; i < cbr.roomsOfCurrentCus().size(); i++) {
                    r.freeRoomAt(cbr.roomsOfCurrentCus().get(i));
                }
                rp.deleteReportOfCurC();
                cbr.cancelCustomer(Customer.currentCustomer.getCustomer_id());
                this.setVisible(false);
                new SeekRoomFrame();

        } else if (e.getSource() == doneButton) {
            if (!confirmed) {
                cbr.loadCbR();
                for (int i = 0; i < cbr.roomsOfCurrentCus().size(); i++) {
                    r.freeRoomAt(cbr.roomsOfCurrentCus().get(i));
                }
                cbr.cancelCustomer(Customer.currentCustomer.getCustomer_id());
                cus.dropCustomer();
                JOptionPane.showMessageDialog(null, "current customer dropped");
            }
            c = 0;
            Report.roomPrice = 0;
            limit = 0;
            nor = 0;
            Customer.currentCustomer = null;
            this.setVisible(false);
            new BookRoomFrame();
        }
    }
}

