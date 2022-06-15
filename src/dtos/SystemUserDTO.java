package dtos;
import classes.Person;
import classes.SystemUser;
import classes.enumerations.UserTypeEnum;
import config.Database;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class SystemUserDTO extends Person {
    private static int counter;
    private int systemUserId;
    private String username;
    private String password;
    private LocalDate birthdate;
    private static UserTypeEnum userType;
    private static List<SystemUserDTO> systemUsers = new ArrayList<>();

    public SystemUserDTO(String username, String password, UserTypeEnum userType) {
        this.systemUserId = counter;
        this.username = username;
        this.password = password;
        this.userType = userType;
        counter++;
    }
    public UserTypeEnum verifyUserType(String username) {
        UserTypeEnum typeOfUser = null;
        for (int i = 0; i < systemUsers.size(); i++) {
            if (username.equals(systemUsers.get(i).getUsername())) {
                typeOfUser = systemUsers.get(i).getUserType();
            }
        }
        return typeOfUser;
    }

    public boolean verifyUserExist(String username) {
        systemUsers = Database.getAllUsers();
        for (int i = 0; i < systemUsers.size(); i++) {
            if (username.equals(systemUsers.get(i).getUsername())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.username;
    }
    private void deleteSystemUser() {
        // Deletes a desired user through user id
    }

    private void editSystemUser() {
        // Modifies the permissions or data of a user through the user id
    }

    //Getters
    public int getUserId() {
        return this.systemUserId;
    }
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public UserTypeEnum getUserType() {
        return this.userType;
    }
}

