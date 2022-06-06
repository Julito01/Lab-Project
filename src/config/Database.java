package config;

import classes.Patient;
import classes.Petition;
import classes.Practice;
import classes.SystemUser;
import com.mysql.cj.protocol.Resultset;

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
            stmt.setString(1, patient.getPatientId());
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

    public static void createPetition(Petition petition) {
        try {
            createConnection();
            String sql = "INSERT INTO petitions (ID_Peticion, Fecha_Carga, Fecha_Entrega)" +
                    "VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, petition.getPetitionId());
            stmt.setString(2, petition.getLoadDate());
            stmt.setString(3, petition.getEtaDate());
            con.close();
        }
        catch (Exception error) {
            System.out.println("Error");
        }
    }

    public static void createPacientPetition(Patient patient, Petition petition, int practId) {
        try {
            createConnection();
            String sql = "INSERT INTO pacients_petitions (ID_Paciente, ID_Peticion, ID_Practica)" +
                    "VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, patient.getPatientId());
            stmt.setInt(2, petition.getPetitionId());
            stmt.setInt(3, practId);
            con.close();
        }
        catch (Exception error) {
            System.out.println("Error");
        }
    }

    public static int getPracticeId(String practiceName) {
        StringBuilder sql = new StringBuilder();
        int practiceId = 0;
        try {
            createConnection();
            Statement stm = con.createStatement();
            sql.append("SELECT ID_Practica FROM Practice WHERE Nombre = ");
            sql.append(practiceName);
            ResultSet result = stm.executeQuery(sql.toString());

            while (result.next()) {
                practiceId = result.getInt(1);
            }
            con.close();
        } catch (Exception error) {
            System.out.println("Error");
        }
        return practiceId;
    }

    public static void createPractice() {
        try {
            createConnection();
            String sql = "INSERT INTO Practicas (ID_Practica, Cod_Practica, Nombre)" +
                    "VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, Practice.getPracticeId());
            stmt.setInt(2, Practice.getPracticeCode());
            stmt.setString(3, Practice.getPracticeName());
            con.close();
        }
        catch (Exception e) {
            System.out.println("Error");
        }
    }

    public static void deletePractice() {
        // Something
    }

    public static void editPractice() {
        // Something
    }
}
