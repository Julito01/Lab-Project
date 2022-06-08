package dtos;
import classes.Person;
import classes.SystemUser;
import classes.enumerations.UserTypeEnum;
import config.Database;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class SystemUserDTO extends Person {
    private int systemUserId;
    private String username;
    private String password;
    private LocalDate birthdate;
    private static UserTypeEnum userType;
    private static SystemUserDTO self;
    private static List<String[]> systemUsers = new ArrayList<>();

    public SystemUserDTO() {}

    public SystemUserDTO(String username, String password, UserTypeEnum userType) {
        super();
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.self = this;
        createSystemUser(this);
    }

    private void createSystemUser(SystemUserDTO userDTO) {
        // Creates a new user and adds it to the database
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        UserTypeEnum userType = userDTO.getUserType();

        SystemUser systemUser = new SystemUser(username, password, userType);
    }

    public String verifyUserType(String username) {
        String typeOfUser = null;
        for (int i = 0; i < systemUsers.size(); i++) {
            if (username.equals(systemUsers.get(i)[0])) {
                typeOfUser = systemUsers.get(i)[2];
            }
        }
        return typeOfUser;
    }

    public boolean verifyUserExist(String username) {
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

    public UserTypeEnum getUserType() {
        return this.userType;
    }
}

