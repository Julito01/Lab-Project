package classes;
import classes.enumerations.UserTypeEnum;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import config.Database;

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

    public SystemUser(String username, String password, UserTypeEnum userType) {
        super();
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.self = this;
        createSystemUser(this);
    }

    private void createSystemUser(SystemUser user) {
        // Creates a new user in the system and adds it to users.csv
        // Implementation of DB
        Database.createUser(user);
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
        systemUsers = Database.getUsers();
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

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUserType() {
        return this.userType.toString();
    }
}
