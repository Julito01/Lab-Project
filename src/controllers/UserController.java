package controllers;

import classes.SystemUser;
import classes.enumerations.UserTypeEnum;
import dtos.SystemUserDTO;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

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
            System.out.println(username);
            System.out.println(password1);
            System.out.println(userType);
            SystemUser.createSystemUser(newUser);
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

    private boolean verifyUserType(Object userType) {
        if (userType != null) {
            return true;
        }
        return false;
    }

    public boolean verifyUserExist(String username) {
        return SystemUser.verifyUserExist(username);
    }

    public static List<SystemUserDTO> getAllUsers() {
        return SystemUser.getAllUsers();
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

    public UserTypeEnum getUserType(String username) {
        List<SystemUserDTO> systemUsers = SystemUser.getAllUsers();
        for (SystemUserDTO systemUserDTO : systemUsers) {
            if (username.equals(systemUserDTO.getUsername())) {
                return systemUserDTO.getUserType();
            }
        }
        return null;
    }
}
