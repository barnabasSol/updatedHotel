package hotel;

import java.sql.*;
import java.util.ArrayList;

public class Room {
    private String roomNumber;
    private String roomType;
    private int roomStatus;

    private ArrayList<Room> roomList = new ArrayList<>();

    Room() {

    }

    public Room(String roomNumber, String roomType, int roomStatus) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomStatus = roomStatus;
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getRoomStatus() {
        return roomStatus;
    }

    public void cleanTable() {
        roomList.clear();
    }

    public void loadRoomTable() {
        String url = "jdbc:mysql://localhost:3306/hotel";
        String uname = "root";
        String password = "-----";
        try {
            Connection con = DriverManager.getConnection(url, uname, password);
            Statement statement = con.createStatement();
            ResultSet resultRoom = statement.executeQuery("select * from rooms");
            cleanTable();
            while (resultRoom.next()) {
                for (int i = 1; i < 4; i++) {
                    String roomN = resultRoom.getString(i);
                    ++i;
                    String roomT = resultRoom.getString(i);
                    ++i;
                    int roomS = resultRoom.getInt(i);
                    Room r = new Room(roomN, roomT, roomS);
                    roomList.add(r);
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void bookRoomAt(int rn){
        String url = "jdbc:mysql://localhost:3306/hotel";
        String uname = "root";
        String password = "-----";
        try {
            Connection con = DriverManager.getConnection(url, uname, password);
            Statement statement = con.createStatement();
            statement.executeUpdate("update rooms set status=1 where room_number="+rn);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeRoomAt(int rn){
        String url = "jdbc:mysql://localhost:3306/hotel";
        String uname = "root";
        String password = "-----";
        try {
            Connection con = DriverManager.getConnection(url, uname, password);
            Statement statement = con.createStatement();
            statement.executeUpdate("update rooms set status=0 where room_number="+rn+";");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


