package hotel;

import java.sql.*;
import java.util.ArrayList;

public class Manager {
    private String id;
    private String password;
    private ArrayList<Manager> mgrLoggerList = new ArrayList<>();


    public Manager(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public Manager() {

    }


    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Manager> getMgrLoggerList() {
        return mgrLoggerList;
    }

    public void cleanTable() {
        mgrLoggerList.clear();
    }

    public void loadMgrLoginTable() {
        String url = "jdbc:mysql://localhost:3306/hotel";
        String uname = "root";
        String password = "VenuS_321";
        try {
            Connection con = DriverManager.getConnection(url, uname, password);
            Statement statement = con.createStatement();
            ResultSet resultRoom = statement.executeQuery("select mgrid, psw from manager");
            cleanTable();
            while (resultRoom.next()) {
                String mgrid = resultRoom.getString(1);
                String ps = resultRoom.getString(2);
                Manager m = new Manager(mgrid, ps);
                mgrLoggerList.add(m);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validateID(String mid) {
        boolean x = false;
        loadMgrLoginTable();
        for (Manager manager : mgrLoggerList) {
            if (mid.equals(manager.getId())) {
                return true;
            }
        }
        return x;
    }



    public boolean validatePsw(String p) {
        boolean x = false;
        loadMgrLoginTable();
        for (Manager manager : mgrLoggerList) {
            if (p.equals(manager.getPassword())) {
                return true;
            }
        }
        return x;
    }
}
