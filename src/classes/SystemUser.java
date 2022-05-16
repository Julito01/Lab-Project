package classes;
import classes.enumerations.UserTypeEnum;

import java.util.*;

public class SystemUser extends Person {
    private int systemUserId;
    private String username;
    private String password;
    private Date birthdate;
    private UserTypeEnum userType;

    public SystemUser() {}

    public SystemUser(int systemUserId, String username, String password, Date birthdate, UserTypeEnum userType) {
        this.systemUserId = systemUserId;
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.userType = userType;
    }

    private void createSystemUser() {
        // Creates a new user in the system
    }

    private void deleteSystemUser() {
        // Deletes a desired user through user id
    }

    private void editSystemUser() {
        // Modifies the permissions or data of a user through the user id
    }
}
