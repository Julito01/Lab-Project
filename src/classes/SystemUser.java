package classes;
import classes.enumerations.UserTypeEnum;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class SystemUser extends Person {
    private int systemUserId;
    private String username;
    private String password;
    private LocalDate birthdate;
    private static UserTypeEnum userType;
    private static SystemUser self;
    private static String csvPath = "/Users/julito/Documents/Tecnicatura de Software/Paradigma Orientado a Objetos/Projecto-Laboratorio/users.csv";
    private static List<String[]> systemUsers = new ArrayList<String[]>();

    public SystemUser() {}

    public SystemUser(int systemUserId, String username, String password, LocalDate birthdate, UserTypeEnum userType) {
        super();
        this.systemUserId = systemUserId;
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.userType = userType;
        this.self = this;
        createSystemUser(this);
    }

    private void createSystemUser(SystemUser user) {
        // Creates a new user in the system and adds it to users.csv
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(csvPath, true));
            String[] userData = {user.username, user.password, user.userType.toString()};
            writer.writeNext(userData);
            writer.close();
        }
        catch (FileNotFoundException error) {
            System.out.println("No se encontro el archivo: " + error);
        }
        catch (IOException error) {
            System.out.println("Error: " + error);
        }
    }

    public static void getUsers() {
        try {
            CSVReader reader = new CSVReader(new FileReader(csvPath));
            String[] fila;
            while ((fila = reader.readNext()) != null) {
                if (fila != null){
                    String[] userData = {fila[0], fila[1], fila[2]};
                    systemUsers.add(userData);
                }
            }
            reader.close();
        }
        catch (FileNotFoundException error) {
            System.out.println("No se encontro el archivo: " + error);
        }
        catch (IOException error) {
            System.out.println("Error: " + error);
        }
        catch (CsvValidationException error) {
            System.out.println("Error: " + error);
        }
        catch (NoClassDefFoundError error) {
            System.out.println("Error: " + error);
        }
    }

    public static String verifyUserType(String username) {
        String typeOfUser = null;
        for (int i = 0; i < systemUsers.size(); i++) {
            if (username.equals(systemUsers.get(i)[0])) {
                typeOfUser = systemUsers.get(i)[2];
            }
        }
        return typeOfUser;
    }

    public static boolean verifyUserExist(String username) {
        getUsers();
        for (int i = 0; i < systemUsers.size(); i++) {
            if (username.equals(systemUsers.get(i)[0])) {
                return true;
            }
        }
        return false;
    }

    private void deleteSystemUser() {
        // Deletes a desired user through user id
    }

    private void editSystemUser() {
        // Modifies the permissions or data of a user through the user id
    }
}
