package hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckOutFrame extends JFrame implements ActionListener {
    JButton submitButton;
    boolean found = false;
    JTextField idText;
    CbR cbr = new CbR();
    Room r = new Room();

    CheckOutFrame() {
        Container c = this.getContentPane();
        c.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500, 200);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.BLACK);
        JLabel title = new JLabel("Checkout Customer");

        title.setForeground(Color.BLUE);
        title.setBounds(150, 15, 200, 30);
        title.setFont(new Font("MV Boli", Font.ITALIC, 20));

        JLabel idLabel = new JLabel("Enter ID: ");
        idLabel.setForeground(Color.BLUE);
        idLabel.setBounds(100, 70, 200, 30);
        idLabel.setFont(new Font("MV Boli", Font.ITALIC, 20));

        idText = new JTextField();
        idText.setBounds(200, 70, 90, 30);

        submitButton = new JButton("submit");
        submitButton.setFocusable(false);
        submitButton.setBounds(320, 70, 100, 30);
        submitButton.addActionListener(this);

        c.add(title);
        c.add(idLabel);
        c.add(idText);
        c.add(submitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitButton){
            cbr.loadCbR();
            r.loadRoomTable();
            for (int i = 0; i < cbr.getCbR_List().size(); i++) {
                if (cbr.getCbR_List().get(i).getCid().equals(idText.getText())){
                    found=true;
                    break;
                }
            }
            if (found){
                cbr.roomsOfCustomer(idText.getText());
                for (int i = 0; i < cbr.getRooms().size(); i++) {
                    r.freeRoomAt(cbr.getRooms().get(i));
                }
                JOptionPane.showMessageDialog(null, "customer checked-out!");
                idText.setText("");
            }
            else
                JOptionPane.showMessageDialog(null, "customer does not exist!");
        }
    }
}
