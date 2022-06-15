package config;

import classes.*;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import dtos.PatientDTO;
import dtos.PracticeDTO;

import java.sql.*;
import java.util.*;

public class Database {
    private static String url = "jdbc:mysql://localhost:3306/LabDB";
    private static String username="root";
    private static String password="Isabel1234";
    private static Connection con;
    private static List<String[]> users = new ArrayList<>();
    private static List<String> practices = new ArrayList<>();
    private static List<PatientDTO> patients = new ArrayList<>();

    private static List<String> station = new ArrayList<>();

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
            PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getPassword());
            stm.setString(3, user.getUserType());
            stm.executeUpdate();
            con.close();
        }
        catch (Exception error) {
            System.out.println("Error: " + error);
        }
    }

    public static List<String[]> getUsers() {
        try {
            createConnection();
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT * FROM users;");
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
            String sql = "INSERT INTO patients (patientId, dni, name, address, mail, genre, age)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, patient.getPatientId());
            stm.setString(2, patient.getPatientDni());
            stm.setString(3, patient.getName());
            stm.setString(4, patient.getAddress());
            stm.setString(5, patient.getMail());
            stm.setString(6, patient.getGenre());
            stm.setString(7, patient.getAge());
            stm.executeUpdate();
            con.close();
        }
        catch (Exception error) {
            System.out.println("Error: " + error);
        }
    }

    public static void deletePatient(int patientId) {
        try {
            createConnection();
            String sql = "DELETE FROM patients WHERE patientId = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, patientId);
            stm.executeUpdate(stm.toString());
        }
        catch (Exception error) {
            System.out.println("Error" + error);
        }
    }

    public static void updatePatient(PatientDTO patient) {
        try {
            createConnection();
            String sql = "UPDATE patients SET dni = ?, name = ?, address = ?, mail = ?, genre = ?, age = ? WHERE patientId = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, patient.getPatientDni());
            stm.setString(2, patient.getName());
            stm.setString(3, patient.getAddress());
            stm.setString(4, patient.getMail());
            stm.setString(5, patient.getGenre());
            stm.setString(6, patient.getAge());
            stm.setInt(7, patient.getPatientId());
            stm.executeUpdate();
            con.close();
        }
        catch (Exception error) {
            System.out.println("Error: " + error);
        }
    }

    public static List<PatientDTO> getAllPatients() {
        try {
            createConnection();
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT * FROM patients");
            while (result.next()) {
                PatientDTO patient = new PatientDTO(result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7));
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
    public static void createPractice(Practice practice) {
        try {
            createConnection();
            String sql = "INSERT INTO practices (practiceCode, practiceName)" +
                    "VALUES (?, ?)";
            PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, practice.getPracticeId());
            stm.setInt(2, practice.getPracticeCode());
            stm.setString(3, practice.getPracticeName());
            stm.setInt(4, practice.getPracticeLength());
            con.close();
        }
        catch (Exception error) {
            System.out.println("Error" + error);
        }
    }

    public static void deletePractice(int practiceId) {
        try {
            createConnection();
            String sql = "DELETE FROM practices WHERE practiceId = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, practiceId);
            stm.executeUpdate(stm.toString());
        }
        catch (Exception error) {
            System.out.println("Error" + error);
        }
    }

    public static void updatePractice(PracticeDTO practice) {
        try {
            createConnection();
            String sql = "UPDATE practices SET practiceId = ?, practiceCode = ?, practiceName = ?, practiceLenght = ?";
            PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, practice.getPracticeCode());
            stm.setString(2, practice.getPracticeName());
            con.close();
        }
        catch (Exception error) {
            System.out.println("Error" + error);
        }
    }


    public static List<String> getPractices() {
        try {
            createConnection();
            Statement stm = con.createStatement();
            ResultSet result  = stm.executeQuery("SELECT practiceName FROM practices");
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
            sql.append("SELECT practiceId FROM practices WHERE practiceName = ?");
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

    public static int getPracticeLength(int practiceId) {
        int practiceLength = 0;
        try {
            createConnection();
            String sql= "SELECT practiceLength FROM practices WHERE practiceId = ?";
            PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, practiceId);
            ResultSet result = stm.executeQuery(sql);
            while (result.next()) {
                practiceLength = result.getInt(1);
            }
            con.close();
        } catch (Exception error) {
            System.out.println("Error" + error);
        }
        return practiceLength;
    }

    // Petition's methods
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


    // Station's methods
    public static void createStation(Station station) {
        try {
            createConnection();
            String sql = "INSERT INTO station (stationId, address, techUser)" +
                    "VALUES (?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, station.getStationId());
            stm.setString(2, station.getAddress());
            stm.setString(3, station.getTechUser());
            stm.executeUpdate();
            con.close();
        }
        catch (Exception error) {
            System.out.println("Error: " + error);
        }
    }

    public static void deleteStation(int stationId) {
        try {
            createConnection();
            String sql = "DELETE FROM patients WHERE patientId = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, stationId);
            stm.executeUpdate(stm.toString());
        }
        catch (Exception error) {
            System.out.println("Error" + error);
        }
    }

    public static void updateStation(StationDTO station) {
        try {
            createConnection();
            String sql = "UPDATE patients SET dni = ?, name = ?, address = ?, mail = ?, genre = ?, age = ? WHERE patientId = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, station.getStationId());
            stm.setString(2, station.getAddress());
            stm.setString(3, station.getTechUser());
            stm.executeUpdate();
            con.close();
        }
        catch (Exception error) {
            System.out.println("Error: " + error);
        }
    }

    public static List<String> getAllStation() {
        try {
            createConnection();
            Statement stm = con.createStatement();
            ResultSet station = stm.executeQuery("SELECT * FROM station");
            while (result.next()) {
                StationDTO station = new PatientDTO(station.getString(2), station.getString(3), station.getString(4));
                station.add(station);
            }
            con.close();
        }
        catch (Exception error) {
            System.out.println("Error" + error);
        }
        return station;
    }


    // Result's methods
    public static void createResult(Result result) {
        try {
            createConnection();
            String sql = "INSERT INTO result (petitionId, resultType, resultValueType)" +
                    "VALUES (?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, result.getPetitionId());
            stm.setInt(2, result.getResultType());
            stm.setString(3, result.getResultValueType());
            stm.executeUpdate();
            con.close();
        }
        catch (Exception error) {
            System.out.println("Error: " + error);
        }
    }

    public static void deleteResult(int petitionId) {
        try {
            createConnection();
            String sql = "DELETE FROM result WHERE petitionId = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, petitionId);
            stm.executeUpdate(stm.toString());
        }
        catch (Exception error) {
            System.out.println("Error" + error);
        }
    }

    public static void updateResult(Station station) {
        try {
            createConnection();
            String sql = "UPDATE result SET petitionId = ?,  = ?, address = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, result.getPetitionId());
            stm.setInt(2, result.getResultType());
            stm.setString(3, result.getResultValueType());
            stm.executeUpdate();
            con.close();
        }
        catch (Exception error) {
            System.out.println("Error: " + error);
        }
    }

    public static List<String> getAllResults() {
        try {
            createConnection();
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT * FROM station");
            while (result.next()) {
                ResultDTO result = new PatientDTO(reslut.getString(2), result.getString(3), result.getString(4));
                result.add(result);
            }
            con.close();
        }
        catch (Exception error) {
            System.out.println("Error" + error);
        }
        return result;
}
