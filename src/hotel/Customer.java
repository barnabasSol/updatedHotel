package hotel;

import java.sql.*;
import java.util.ArrayList;

public class Customer {
    public static Customer currentCustomer;
    private String customer_id;
    private String first_name;
    private String last_name;
    private String phone_number;
    private ArrayList<Customer> customerList = new ArrayList<>();

    Customer() {

    }


    public Customer(String customer_id, String first_name, String last_name, String phone_number) {
        this.customer_id = customer_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
    }


    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }


    public ArrayList<Customer> getCustomersList() {
        return customerList;
    }


    public void cleanList() {
        customerList.clear();
    }

    public void loadCustomerTable() {
        String url = "jdbc:mysql://localhost:3306/hotel";
        String uname = "root";
        String password = "VenuS_321";
        cleanList();
        try {
            Connection con = DriverManager.getConnection(url, uname, password);
            Statement statement = con.createStatement();
            ResultSet resultCus = statement.executeQuery("select * from customers");
            while (resultCus.next()) {
                    String cid = resultCus.getString(1);
                    String fname = resultCus.getString(2);
                    String lname = resultCus.getString(3);
                    String pnum = resultCus.getString(4);
                    Customer c = new Customer(cid, fname, lname, pnum);
                    customerList.add(c);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean redundantPhone(String p) {
        loadCustomerTable();
        for (int i = 0; i < getCustomersList().size(); i++) {
            if (p.equals(getCustomersList().get(i).getPhone_number())) {
                return true;
            }
        }
        return false;
    }


    public void addCustomer(String id, String fname, String lname, String pnum) {
        String url = "jdbc:mysql://localhost:3306/hotel";
        String uname = "root";
        String password = "VenuS_321";

        String query = "insert into customers values ('" + id + "','" + fname + "','" + lname + "','" + pnum + "')";
        try {
            Connection con = DriverManager.getConnection(url, uname, password);
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String idGenerator() {
        loadCustomerTable();
        int num = 9;
        if (customerList.size() != 0) {
            String preNewID = customerList.get(customerList.size() - 1).getCustomer_id();
            String[] split = preNewID.split("s", 2);
            num = Integer.parseInt(split[1]);
            ++num;
            return "cus" + num;
        } else
            num += 1;
        return "cus" + num;
    }

    public boolean isLetters(String s) {
        char[] c = s.toCharArray();
        for (char z : c) {
            if (!Character.isAlphabetic(z)) {
                return false;
            }
        }
        return true;
    }

    public void dropCustomer() {
        String url = "jdbc:mysql://localhost:3306/hotel";
        String uname = "root";
        String password = "VenuS_321";
        try {
            Connection con = DriverManager.getConnection(url, uname, password);
            Statement statement = con.createStatement();
            statement.executeUpdate("delete from customers where customer_id=" + "\"" + Customer.currentCustomer.getCustomer_id() + "\"" + ";");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
