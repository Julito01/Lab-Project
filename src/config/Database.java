package config;

import classes.Patient;
import classes.SystemUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static String url = "jdbc:mysql://localhost:3306/LabDB";
    private static String username="root";
    private static String password="Isabel1234";
    private static Connection con;
    private static List<String[]> users = new ArrayList<String[]>();

    public static void createConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        }
        catch (Exception error) {
            System.out.println(error);
        }
    }

    public static List<String[]> getUsers() {
        try {
            createConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                String[] currentUser = {resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)};
                users.add(currentUser);
            }
            con.close();
        }
        catch (Exception error) {
            System.out.println("Error: " + error);
        }
        return users;
    }

    public static void createUser(SystemUser user) {
        try {
            createConnection();
            String sql = "INSERT INTO users (username, password, userType)" +
                    "VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getUserType());
            stmt.executeUpdate();
            con.close();
        }
        catch (Exception error) {
            System.out.println("Error: " + error);
        }
    }

    public static void createPatient(Patient patient) {
        try {
            createConnection();
            String sql = "INSERT INTO patients (patientId, name, address, mail, genre, age)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, patient.getId());
            stmt.setString(2, patient.getName());
            stmt.setString(3, patient.getAddress());
            stmt.setString(4, patient.getMail());
            stmt.setString(5, patient.getGenre());
            stmt.setString(6, patient.getAge());
            stmt.executeUpdate();
            con.close();
        }
        catch (Exception error) {
            System.out.println("Error: " + error);
        }
    }
}
