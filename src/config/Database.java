package config;

import classes.*;
import classes.enumerations.UserTypeEnum;
import dtos.*;

import java.sql.*;
import java.util.*;

public class Database {
    private static String url = "jdbc:mysql://localhost:3306/LabDB";
    private static String username = "root";
    private static String password = "Isabel1234";
    private static Connection con;
    private static List<SystemUserDTO> users = new ArrayList<>();
    private static List<PracticeDTO> practices = new ArrayList<>();
    private static List<PatientDTO> patients = new ArrayList<>();
    private static List<StationDTO> stations = new ArrayList<>();
    private static List<ResultDTO> results = new ArrayList<>();

    public static void createConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception error) {
            System.out.println("Error createConnection(): " + error);
        }
    }

    // User's methods
    public static void createUser(SystemUserDTO user) {
        try {
            createConnection();
            String sql = "INSERT INTO users (username, password, userType) VALUES (?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getPassword());
            stm.setString(3, user.getUserType().toString());
            stm.executeUpdate();
            con.close();
        } catch (Exception error) {
            System.out.println("Error createUser(): " + error);
        }
    }

    public static void deleteUser(int userId) {
        try {
            createConnection();
            String sql = "DELETE FROM users WHERE userId = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.executeUpdate(stm.toString());
        } catch (Exception error) {
            System.out.println("Error deleteUser(): " + error);
        }
    }

    public static void updateUser(SystemUser user) {
        try {
            createConnection();
            String sql = "UPDATE users SET username = ?, password = ?, userType = ?)";
            PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getUsername());
            stm.setString(3, user.getPassword());
            stm.setString(4, user.getUserType());
            stm.executeUpdate();
            con.close();
        } catch (Exception error) {
            System.out.println("Error updateUser(): " + error);
        }
    }

    public static List<SystemUserDTO> getAllUsers() {
        try {
            createConnection();
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT * FROM users");
            while (result.next()) {
                SystemUserDTO currentUser = new SystemUserDTO(result.getString(2), result.getString(3), getUserType(result.getString(4)));
                users.add(currentUser);
            }
            con.close();
        } catch (Exception error) {
            System.out.println("Error getAllUsers(): " + error);
        }
        return users;
    }

    private static UserTypeEnum getUserType(String userType) {
        if (userType.equals("ADMINISTRADOR")) {
            return UserTypeEnum.ADMINISTRADOR;
        }
        else if (userType.equals("LABORATORISTA")) {
            return UserTypeEnum.LABORATORISTA;
        }
        else {
            return UserTypeEnum.RECEPCIONISTA;
        }
    }

    // Patient's methods
    public static void createPatient(Patient patient) {
        try {
            createConnection();
            String sql = "INSERT INTO patients (patientId, dni, name, address, mail, genre, age) VALUES (?, ?, ?, ?, ?, ? ,?)";
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
        } catch (Exception error) {
            System.out.println("Error createPatient(): " + error);
        }
    }

    public static void deletePatient(int patientId) {
        try {
            createConnection();
            String sql = "DELETE FROM patients WHERE patientId = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, patientId);
            stm.executeUpdate(stm.toString());
        } catch (Exception error) {
            System.out.println("Error deletePatient(): " + error);
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
        } catch (Exception error) {
            System.out.println("Error updatePatient(): " + error);
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
        } catch (Exception error) {
            System.out.println("Error getAllPatients(): " + error);
        }
        return patients;
    }

    // Practice's methods
    public static void createPractice(PracticeDTO practice) {
        try {
            createConnection();
            String sql = "INSERT INTO practices (practiceCode, practiceName)" +
                    "VALUES (?, ?)";
            PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, practice.getPracticeId());
            stm.setInt(2, practice.getPracticeCode());
            stm.setString(3, practice.getPracticeName());
            stm.setString(4, practice.getEth());
            con.close();
        } catch (Exception error) {
            System.out.println("Error createPractice(): " + error);
        }
    }

    public static void deletePractice(int practiceId) {
        try {
            createConnection();
            String sql = "DELETE FROM practices WHERE practiceId = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, practiceId);
            stm.executeUpdate(stm.toString());
        } catch (Exception error) {
            System.out.println("Error deletePractice(): " + error);
        }
    }

    public static void updatePractice(PracticeDTO practice) {
        try {
            createConnection();
            String sql = "UPDATE practices SET practiceId = ?, practiceCode = ?, practiceName = ?, practiceLength = ?";
            PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, practice.getPracticeCode());
            stm.setString(2, practice.getPracticeName());
            con.close();
        } catch (Exception error) {
            System.out.println("Error updatePractice()" + error);
        }
    }


    public static List<PracticeDTO> getAllPractices() {
        try {
            createConnection();
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT * FROM practices");
            while (result.next()) {
                PracticeDTO practiceName = new PracticeDTO(result.getInt(1), result.getString(2), result.getString(3));
                practices.add(practiceName);
            }
            con.close();
        } catch (Exception error) {
            System.out.println("Error getAllPractices(): " + error);
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
            System.out.println("Error getPracticeId(): " + error);
        }
        return practiceId;
    }

    public static String getPracticeLength(int practiceId) {
        StringBuilder sql = new StringBuilder();
        String practiceLength = "";
        try {
            createConnection();
            Statement stm = con.createStatement();
            sql.append("SELECT practiceLength FROM practices WHERE practiceId = ");
            sql.append(practiceId);
            ResultSet result = stm.executeQuery(sql.toString());
            while (result.next()) {
                practiceLength = result.getString(1);
            }
            con.close();
        } catch (Exception error) {
            System.out.println("Error getPracticeLength(): " + error);
        }
        return practiceLength;
    }

    // Petition's methods
    public static void createPetition(Petition petition) {
        try {
            createConnection();
            String sql = "INSERT INTO petitions (petitionId, loadDate, deliverDate)" +
                    "VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, petition.getPetitionId());
            stmt.setString(2, petition.getLoadDate());
            stmt.setString(3, petition.getEtaDate());
            con.close();
        } catch (Exception error) {
            System.out.println("Error createPetition(): " + error);
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
        } catch (Exception error) {
            System.out.println("Error createPatientPetition(): " + error);
        }
    }

    // Station's methods
    public static void createStation(StationDTO station) {
        try {
            createConnection();
            String sql = "INSERT INTO stations (stationId, address, techUserId)" +
                    "VALUES (?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, station.getStationId());
            stm.setString(2, station.getAddress());
            stm.setInt(3, station.getTechUserId());
            stm.executeUpdate();
            con.close();
        } catch (Exception error) {
            System.out.println("Error createStation(): " + error);
        }
    }

    public static void deleteStation(int stationId) {
        try {
            createConnection();
            String sql = "DELETE FROM stations WHERE stationId = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, stationId);
            stm.executeUpdate(stm.toString());
        } catch (Exception error) {
            System.out.println("Error deleteStation(): " + error);
        }
    }

    public static void updateStation(StationDTO station) {
        try {
            createConnection();
            String sql = "UPDATE stations SET stationId = ?, address = ?, techUser = ? WHERE stationId = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, station.getStationId());
            stm.setString(2, station.getAddress());
            stm.setInt(3, station.getTechUserId());
            stm.setInt(4, station.getStationId());
            stm.executeUpdate();
            con.close();
        } catch (Exception error) {
            System.out.println("Error updateStation(): " + error);
        }
    }

    public static List<StationDTO> getAllStations() {
        try {
            createConnection();
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT * FROM stations");
            while (result.next()) {
                StationDTO station = new StationDTO(result.getString(2), result.getInt(3));
                stations.add(station);
            }
            con.close();
        } catch (Exception error) {
            System.out.println("Error getAllStations(): " + error);
        }
        return stations;
    }


    // Result's methods
    public static void createResult(ResultDTO result) {
        try {
            createConnection();
            String sql = "INSERT INTO result (resultId, resultValue, resultValueType)" +
                    "VALUES (?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            stm.setInt(1, result.getPetitionId());
//            stm.setInt(2, result.getResultType());
//            stm.setString(3, result.getResultValueType());
//            stm.executeUpdate();
            con.close();
        } catch (Exception error) {
            System.out.println("Error createResult(): " + error);
        }
    }

    public static void deleteResult(int resultId) {
        try {
            createConnection();
            String sql = "DELETE FROM result WHERE resultId = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, resultId);
            stm.executeUpdate(stm.toString());
        } catch (Exception error) {
            System.out.println("Error deleteResult(): " + error);
        }
    }

    public static void updateResult(ResultDTO result) {
        try {
            createConnection();
            String sql = "UPDATE result SET resultValue = ?,  resultType = ? WHERE resultId = ?";
            PreparedStatement stm = con.prepareStatement(sql);
//            stm.setInt(1, result.getPetitionId());
//            stm.setInt(2, result.getResultType());
//            stm.setString(3, result.getResultValueType());
//            stm.executeUpdate();
            con.close();
        } catch (Exception error) {
            System.out.println("Error updateResult(): " + error);
        }
    }

    public static List<ResultDTO> getAllResults() {
        try {
            createConnection();
            Statement stm = con.createStatement();
            ResultSet queryResult = stm.executeQuery("SELECT * FROM results");
            while (queryResult.next()) {
//                ResultDTO result = new ResultDTO(queryResult.getString(2), queryResult.getString(3), queryResult.getString(4));
//                results.add(result);
            }
            con.close();
        } catch (Exception error) {
            System.out.println("Error getAllResults(): " + error);
        }
        return results;
    }
}