package hotel;

import java.sql.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Report {
    public static double roomPrice = 0;
    private ArrayList<String> reportList = new ArrayList<>();

    public Report() {
    }

    public ArrayList<String> getReportList() {
        return reportList;
    }

    public void cleanTable() {
        reportList.clear();
    }

    public void loadReportTable() {
        String url = "jdbc:mysql://localhost:3306/hotel";
        String uname = "root";
        String password = "VenuS_321";
        try {
            Connection con = DriverManager.getConnection(url, uname, password);
            Statement statement = con.createStatement();
            ResultSet resultReport = statement.executeQuery("select * from report order by checkin_date asc");
            cleanTable();
            while (resultReport.next()) {
                for (int i = 1; i < 8; i++) {
                    String tid = resultReport.getString(i);
                    reportList.add(tid);
                    ++i;
                    String cid = resultReport.getString(i);
                    reportList.add(cid);
                    ++i;
                    String ramt = resultReport.getString(i);
                    reportList.add(ramt);
                    ++i;
                    String tp = resultReport.getString(i);
                    reportList.add(tp);
                    ++i;
                    String chin = resultReport.getString(i);
                    reportList.add(chin);
                    ++i;
                    String chod = resultReport.getString(i);
                    reportList.add(chod);
                    ++i;
                    String bookerId = resultReport.getString(i);
                    reportList.add(bookerId);
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addReport(String cusID, int amountOfRooms, double paidAmount,
                          String checkOutDate, String currentReceptionist) {
        String url = "jdbc:mysql://localhost:3306/hotel";
        String uname = "root";
        String password = "-----";

        String query = "insert into report(customer_id, number_of_rooms, paid_amount, checkout_date, booker_id) " +
                "values(" + "\"" + cusID + "\", " + amountOfRooms + ", " + paidAmount + ", " +
                "\"" + checkOutDate + "\"" + "," + "\"" + currentReceptionist + "\");";
        try {
            Connection con = DriverManager.getConnection(url, uname, password);
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteReportOfCurC() {
        String url = "jdbc:mysql://localhost:3306/hotel";
        String uname = "root";
        String password = "-----";
        try {
            Connection con = DriverManager.getConnection(url, uname, password);
            Statement statement = con.createStatement();
            statement.executeUpdate("delete from report where customer_id="
                    + "\"" + Customer.currentCustomer.getCustomer_id() + "\";");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean isProperDate(String theDate) {
        //2022-03-03
        //check if n has a length of 10
        if (theDate.length() != 10) {
            return false;
        } else {
            //check if any of the characters in n (other than the hyphens) are numbers
            if (theDate.charAt(4) != '-' || theDate.charAt(7) != '-')
                return false;
            for (int x = 0; x < 10; x++) {
                if (theDate.charAt(x) != '-' && !Character.isDigit(theDate.charAt(x))) {
                    return false;
                } else if (theDate.charAt(x) == '-') {
                    if (x != 4 && x != 7)
                        return false;
                }
            }
        }
        //extra check to see if month is higher than 12 or day is greater than 31
        if (Integer.parseInt(theDate.substring(5, 7)) > 12 || Integer.parseInt(theDate.substring(8, 10)) > 31)
            return false;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd");
        LocalDateTime now = LocalDateTime.now();
        String currentDate = dtf.format(now);
        if (Integer.parseInt(theDate.substring(0, 4)) < Integer.parseInt(currentDate.substring(0, 4))) {
            return false;
        } else if (Integer.parseInt(theDate.substring(5, 7)) < Integer.parseInt(currentDate.substring(5, 7))) {
            return false;
        }
        return true;
    }


    public double calcTotalRoomPrice(String chod) throws ParseException {
        String nights;
        char[] coutDate = chod.toCharArray();
        char[] numDate = new char[chod.length()];
        int j = 0;
        for (char c : coutDate) {
            if (Character.isDigit(c)) {
                numDate[j] = c;
            } else {
                numDate[j] = ' ';
            }
            ++j;
        }
        String d2 = String.valueOf(numDate);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd");
        LocalDateTime now = LocalDateTime.now();
        String d1 = dtf.format(now);

        SimpleDateFormat frmt = new SimpleDateFormat("yyyy MM dd");
        Date date1 = frmt.parse(d1);
        Date date2 = frmt.parse(d2);
        long diff = date2.getTime() - date1.getTime();
        nights = String.valueOf(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        if (Integer.parseInt(nights) > 1) {
            for (int i = 0; i < Integer.parseInt(nights); i++) {
                roomPrice += 200;
            }
            roomPrice -= 200;
        }
        return roomPrice;
    }
}
