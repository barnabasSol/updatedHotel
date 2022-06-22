package hotel;

import java.sql.*;
import java.util.ArrayList;


public class Receptionist {
    private String first_name;
    private String last_name;
    private String recLoginID;
    private String password;
    private ArrayList<Receptionist> receptionistLoggerList = new ArrayList<>();
    private ArrayList<String> phone_number = new ArrayList<>();
    private static String currentRecepUser;

    public Receptionist() {

    }

    public Receptionist(String recLoginID, String password) {
        this.recLoginID = recLoginID;
        this.password = password;
    }

    public static String getCurrentRecepUser() {
        return currentRecepUser;
    }

    public static void setCurrentRecepUser(String x) {
        currentRecepUser = x;
    }

    public ArrayList<String> getPhone_numberList() {
        return phone_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getRecLoginID() {
        return recLoginID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Receptionist> getReceptionistLoggerList() {
        return receptionistLoggerList;
    }



    public void cleanLoggerList() {
        receptionistLoggerList.clear();
    }


    public void loadRecLoginTable() {
        String url = "jdbc:mysql://localhost:3306/hotel";
        String uname = "root";
        String password = "VenuS_321";
        try {
            Connection con = DriverManager.getConnection(url, uname, password);
            Statement statement = con.createStatement();
            ResultSet resultRec = statement.executeQuery("select loginID, password from receptionist");
            cleanLoggerList();
            while (resultRec.next()) {
                String id = resultRec.getString(1);
                String pw = resultRec.getString(2);
                Receptionist x = new Receptionist(id, pw);
                receptionistLoggerList.add(x);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean redundantPhone(String p) {
        for (String s : phone_number) {
            if (p.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public void loadPhoneList() {
        String url = "jdbc:mysql://localhost:3306/hotel";
        String uname = "root";
        String password = "VenuS_321";
        try {
            Connection con = DriverManager.getConnection(url, uname, password);
            Statement statement = con.createStatement();
            ResultSet resultRec = statement.executeQuery("select phone_number from receptionist");
            phone_number.clear();
            while (resultRec.next()) {
                String phone = resultRec.getString(1);
                phone_number.add(phone);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkID(String u) {
        boolean match = false;
        for (Receptionist receptionist : receptionistLoggerList) {
            if (u.equals(receptionist.getRecLoginID())) {
                match = true;
                break;
            }
        }
        return match;
    }

    public boolean checkPassword(String p) {
        boolean match = false;
        for (Receptionist receptionist : receptionistLoggerList) {
            if (p.equals(receptionist.getPassword())) {
                match = true;
                break;
            }
        }
        return match;
    }

    public boolean isLetters(String str) {
        char[] ch = str.toCharArray();
        for (char c : ch) {
            if (!Character.isAlphabetic(c)) {
                return false;
            }
        }
        return true;
    }

    public boolean isNumeric(String str) {
        char[] ch = str.toCharArray();
        for (char c : ch) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }



    public String idGenerator() {
        loadRecLoginTable();
        int num = 9;
        if (receptionistLoggerList.size() != 0) {
            String preNewID = receptionistLoggerList.get(receptionistLoggerList.size() - 1).getRecLoginID();
            String[] split = preNewID.split("c", 2);
            num = Integer.parseInt(split[1]);
            ++num;
            return "rec" + num;
        } else
            num += 1;
        return "rec" + num;
    }


    public void signupReceptionist(String fname, String lname, String phone, String psw) {
        String url = "jdbc:mysql://localhost:3306/hotel";
        String uname = "root";
        String password = "VenuS_321";
        try {
            Connection con = DriverManager.getConnection(url, uname, password);
            Statement statement = con.createStatement();
            statement.executeUpdate("insert into receptionist values (" + "\"" + idGenerator() + "\"" + "," +
                    "\"" + fname + "\"" + "," + "\"" + lname + "\"" + "," + "\"" + phone + "\"" + "," + "\"" +
                    psw + "\"" + ")" + ";");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
