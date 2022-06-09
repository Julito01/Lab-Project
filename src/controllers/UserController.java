package controllers;

import classes.SystemUser;
import classes.enumerations.UserTypeEnum;
import dtos.SystemUserDTO;

import javax.swing.*;
import java.time.LocalDate;

public class UserController {
    private String username;
    private String password;
    private UserTypeEnum userType;
    private static UserController ucObject;

    private UserController() {}

    public static UserController getInstance() {
        if (ucObject == null) {
            ucObject = new UserController();
        }
        return ucObject;
    }
    public boolean createUser(String username, String password1, String password2, LocalDate birthdate, UserTypeEnum userType) {
        // User validations
        if (verifyUsername(username) && verifyPass(password1, password2) && verifyUserType(userType) && !SystemUser.verifyUserExist(username)) {
            SystemUserDTO newUser = new SystemUserDTO(username, password1, userType);
            JOptionPane.showMessageDialog(null, "Usuario creado con exito.", "Usuario creado", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        else if (username.equals("") || password1.equals("") || password2.equals("") || birthdate == null) {
            JOptionPane.showMessageDialog(null, "Faltan completar campos y/o las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if (SystemUser.verifyUserExist(username)) {
            JOptionPane.showMessageDialog(null, "El usuario ingresado ya existe.", "Usuario existente", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else {
            JOptionPane.showMessageDialog(null, "Ingrese un usuario de mas de 4 caracteres.", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    private boolean verifyUsername(String username) {
        if (username.length() > 4) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean verifyPass(String pass1, String pass2) {
        if (pass1.matches(pass2)) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean verifyUserType(Object userType) {
        if (userType != null) {
            return true;
        }
        else {
            return false;
        }
    }
}
