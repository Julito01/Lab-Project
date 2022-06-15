package classes;
import classes.enumerations.UserTypeEnum;
import config.Database;
import dtos.*;

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
    private static List<SystemUserDTO> systemUsers = new ArrayList<>();

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
        // Creates a new user and adds it to the database
        Database.createUser(user);
    }

    public static UserTypeEnum verifyUserType(String username) {
        UserTypeEnum typeOfUser = null;
        for (SystemUserDTO systemUser: systemUsers) {
            if (username.equals(systemUser.getUsername())) {
                typeOfUser = systemUser.getUserType();
            }
        }
        return typeOfUser;
    }

    public static boolean verifyUserExist(String username) {
        systemUsers = Database.getAllUsers();
        for (SystemUserDTO systemUser: systemUsers) {
            if (username.equals(systemUser.getUsername())) {
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

    public static List<SystemUserDTO> getAllUsers() {
        return Database.getAllUsers();
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
