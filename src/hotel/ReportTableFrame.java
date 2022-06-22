package hotel;

import javax.swing.*;
import java.awt.*;

public class ReportTableFrame extends JFrame {
    Report rp = new Report();
    JTable table;

    ReportTableFrame() {
        Container co = this.getContentPane();
        co.setLayout(null);
        rp.loadReportTable();
        int rowSize = 0;
        Object[] reportToArray = rp.getReportList().toArray();
        for (int i = 0; i < reportToArray.length; i += 7) {
            ++rowSize;
        }
        Object[][] tableData = new Object[rowSize][7];

        int x = 0;
        for (int r = 0; r < rowSize; r++) {
            for (int c = 0; c < 7; c++) {
                tableData[r][c] = reportToArray[x++];
            }
        }




        String[] columnsTitle = {"transaction id", "customer id", "number of rooms", "paid amount", "check-in timestamp",
                "check-out date", "booker id"};
        table = new JTable(tableData, columnsTitle);
        table.setPreferredScrollableViewportSize(new Dimension(900, 500));
        table.setForeground(Color.black);
        //table.setFillsViewportHeight(false);


        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setResizable(true);
        this.setSize(900, 500);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.white);
    }
}
