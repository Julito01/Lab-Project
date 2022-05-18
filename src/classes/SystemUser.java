package classes;
import classes.enumerations.UserTypeEnum;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class SystemUser extends Person {
    private int systemUserId;
    private String username;
    private String password;
    private LocalDate birthdate;
    private UserTypeEnum userType;
    private List<SystemUser> systemUsers = new ArrayList<SystemUser>();

    public SystemUser() {}

    public SystemUser(int systemUserId, String username, String password, LocalDate birthdate, UserTypeEnum userType) {
        super();
        this.systemUserId = systemUserId;
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.userType = userType;
        createSystemUser(this);
    }

    private void createSystemUser(SystemUser user) {
        // Creates a new user in the system
        systemUsers.add(user);
    }

    private void deleteSystemUser() {
        // Deletes a desired user through user id
    }

    private void editSystemUser() {
        // Modifies the permissions or data of a user through the user id
    }
}
