package hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerWorkFrame extends JFrame implements ActionListener {
    JButton showReportButton;
    JButton customerDetailButton;

    ManagerWorkFrame(){
        Container c = this.getContentPane();
        c.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700, 500);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.BLACK);

        JLabel label = new JLabel("** Manager Workspace **");
        label.setForeground(Color.yellow);
        label.setBounds(225, 15, 250, 30);
        label.setFont(new Font("MV Boli", Font.ITALIC, 20));

        showReportButton = new JButton();
        showReportButton.addActionListener(this);
        showReportButton.setBounds(250, 120, 200, 30);
        showReportButton.setText("show report");
        showReportButton.setFocusable(false);

        customerDetailButton = new JButton();
        customerDetailButton.addActionListener(this);
        customerDetailButton.setBounds(250, 185, 200, 30);
        customerDetailButton.setText("customer booking detail");
        customerDetailButton.setFocusable(false);


        c.add(showReportButton);
        c.add(customerDetailButton);
        c.add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==showReportButton){
            new ReportTableFrame();
        }
        else if (e.getSource()==customerDetailButton){
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new FlowLayout());
            frame.setResizable(true);
            frame.setSize(900, 500);
            frame.setVisible(true);
            frame.getContentPane().setBackground(Color.white);
        }

    }
}
