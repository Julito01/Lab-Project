package config;

import classes.Patient;
import classes.Petition;
import classes.Practice;
import classes.SystemUser;

import java.sql.*;
import java.util.*;

public class Database {
    private static String url = "jdbc:mysql://localhost:3306/LabDB";
    private static String username="root";
    private static String password="Isabel1234";
    private static Connection con;
    private static List<String[]> users = new ArrayList<>();
    private static List<String> practices = new ArrayList<>();
    private static List<Patient> patients = new ArrayList<>();


    public static void createConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        }
        catch (Exception error) {
            System.out.println("Error" + error);
        }
    }

    // User's methods
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

    public static List<String[]> getUsers() {
        try {
            createConnection();
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users;");
            while (result.next()) {
                String[] currentUser = {result.getString(2), result.getString(3), result.getString(4)};
                users.add(currentUser);
            }
            con.close();
        }
        catch (Exception error) {
            System.out.println("Error: " + error);
        }
        return users;
    }

    // Patient's methods
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

    public static void deletePatient(String patientId) {
        StringBuilder sql = new StringBuilder();
        try {
            createConnection();
            Statement stm = con.createStatement();
            sql.append("DELETE FROM patients WHERE patientId = ");
            sql.append(patientId);
            ResultSet result = stm.executeQuery(sql.toString());
        }
        catch (Exception error) {
            System.out.println("Error" + error);
        }
    }

    public static List<Patient> getAllPatients() {
        try {
            createConnection();
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT * FROM patients");
            while (result.next()) {
                Patient patient = new Patient(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
                patients.add(patient);
            }
            con.close();
        }
        catch (Exception error) {
            System.out.println("Error" + error);
        }
        return patients;
    }

    // Practice's methods
    public static void createPractice() {
        try {
            createConnection();
            String sql = "INSERT INTO practices (practiceCode, practiceName)" +
                    "VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, Practice.getPracticeCode());
            stmt.setString(2, Practice.getPracticeName());
            con.close();
        }
        catch (Exception error) {
            System.out.println("Error" + error);
        }
    }

    public static void deletePractice() {
        // Something
    }

    public static void editPractice() {
        // Something
    }

    public static List<String> getPractices() {
        try {
            createConnection();
            Statement stm = con.createStatement();
            ResultSet result  = stm.executeQuery("SELECT practiceName FROM practices;");
            while (result.next()) {
                String practiceName = result.getString(1);
                practices.add(practiceName);
            }
            con.close();
        }
        catch (Exception error) {
            System.out.println("Error" + error);
        }
        return practices;
    }

    public static int getPracticeId(String practiceName) {
        StringBuilder sql = new StringBuilder();
        int practiceId = 0;
        try {
            createConnection();
            Statement stm = con.createStatement();
            sql.append("SELECT practiceId FROM practices WHERE practiceName = ");
            sql.append(practiceName);
            ResultSet result = stm.executeQuery(sql.toString());
            while (result.next()) {
                practiceId = result.getInt(1);
            }
            con.close();
        } catch (Exception error) {
            System.out.println("Error" + error);
        }
        return practiceId;
    }

    // Petition's methods
    public static void createPetition(Petition petition) {
        try {
            createConnection();
            String sql = "INSERT INTO petitions (ID_Peticion, Fecha_Carga, Fecha_Entrega)" +
                    "VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, Petition.getPetitionId());
            stmt.setString(2, Petition.getLoadDate());
            stmt.setString(3, Petition.getEtaDate());
            con.close();
        }
        catch (Exception error) {
            System.out.println("Error" + error);
        }
    }

    public static void createPatientPetition(String patientId, int petitionId, int practiceId) {
        try {
            createConnection();
            String sql = "INSERT INTO patients_petitions (patientId, petitionId, practiceId)" +
                    "VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, patientId);
            stmt.setInt(2, petitionId);
            stmt.setInt(3, practiceId);
            con.close();
        }
        catch (Exception error) {
            System.out.println("Error" + error);
        }
    }
}
