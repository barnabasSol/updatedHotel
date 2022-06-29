package hotel;

import java.sql.*;
import java.util.ArrayList;

public class CbR {
    private String cid;
    private int room_n;
    ArrayList<Integer> theRooms;

    private ArrayList<CbR> CbR_List = new ArrayList<>();

    public CbR() {

    }

    public CbR(String cid, int room_n) {
        this.cid = cid;
        this.room_n = room_n;
    }

    public void cleanList() {
        CbR_List.clear();
    }

    public int getRoom_n() {
        return room_n;
    }

    public String getCid() {
        return cid;
    }

    public void loadCbR() {
        String url = "jdbc:mysql://localhost:3306/hotel";
        String uname = "root";
        String password = "----";
        cleanList();
        try {
            Connection con = DriverManager.getConnection(url, uname, password);
            Statement statement = con.createStatement();
            ResultSet resultCbR = statement.executeQuery("select * from cbr");
            while (resultCbR.next()) {
                for (int i = 1; i < 3; i++) {
                    String cid = resultCbR.getString(i);
                    ++i;
                    int room_num = resultCbR.getInt(i);
                    CbR x = new CbR(cid, room_num);
                    CbR_List.add(x);
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void chosenRoom(int roomN) {
        String url = "jdbc:mysql://localhost:3306/hotel";
        String uname = "root";
        String password = "----";
        try {
            Connection con = DriverManager.getConnection(url, uname, password);
            Statement statement = con.createStatement();
            statement.executeUpdate("insert into cbr values("+"\""+ Customer.currentCustomer.getCustomer_id()+ "\"" + "," + roomN + ");");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CbR> getCbR_List() {
        return CbR_List;
    }

    public void cancelCustomer(String id){
    String url = "jdbc:mysql://localhost:3306/hotel";
    String uname = "root";
    String password = "----";
    try {
        Connection con = DriverManager.getConnection(url, uname, password);
        Statement statement = con.createStatement();
        statement.executeUpdate("delete from cbr where cus_id="+"\""+id+"\""+";");
        con.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public ArrayList<Integer> roomsOfCurrentCus(){
    ArrayList<Integer> curRooms = new ArrayList<>();
    String url = "jdbc:mysql://localhost:3306/hotel";
    String uname = "root";
    String password = "-----";
    try {
        Connection con = DriverManager.getConnection(url, uname, password);
        Statement statement = con.createStatement();
        ResultSet rooms = statement.executeQuery("select roomNum from cbr where cus_id" +
                "="+"\""+Customer.currentCustomer.getCustomer_id()+"\""+";");
        while (rooms.next()) {
            int r = rooms.getInt(1);
                curRooms.add(r);
        }
        con.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return curRooms;
}

    public void roomsOfCustomer(String id){
         theRooms = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/hotel";
        String uname = "root";
        String password = "----";
        try {
            Connection con = DriverManager.getConnection(url, uname, password);
            Statement statement = con.createStatement();
            ResultSet rooms = statement.executeQuery("select roomNum from cbr where cus_id="+"\""+id+"\""+";");
            while (rooms.next()) {
                int r = rooms.getInt(1);
                theRooms.add(r);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Integer> getRooms(){
        return theRooms;
    }
}
