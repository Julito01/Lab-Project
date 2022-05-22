package vista;

import classes.enumerations.UserTypeEnum;
import com.github.lgooddatepicker.components.DatePicker;
import config.Database;
import model.UserType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import classes.SystemUser;

public class CreateUserFrame extends JDialog {
    private JPanel mainPanel;
    private JTextField userField;
    private JPasswordField passField;
    private JPasswordField confirmPassField;
    private JLabel userLabel;
    private JLabel passLabel;
    private JLabel confirmPassLabel;
    private JLabel userTypeLabel;
    private JComboBox userTypeField;
    private JButton submitButton;
    private JLabel dateLabel;
    private DatePicker dateField;
    private CreateUserFrame self;
    private List<UserTypeEnum> userTypes = new ArrayList<UserTypeEnum>();
    private List<String[]> usersFetched = new ArrayList<String[]>();

    public CreateUserFrame(Window owner, String title) {
        super(owner, title);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(460, 300);
        this.setResizable(false);
        this.setModal(true);
        this.self = this;
        this.asociarEventos();

        // Adding the user types to the user type field
        userTypes.add(UserTypeEnum.ADMINISTRADOR);
        userTypes.add(UserTypeEnum.LABORATORISTA);
        userTypes.add(UserTypeEnum.RECEPCIONISTA);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addAll(userTypes);
        userTypeField.setModel(model);

        this.setLocationRelativeTo(null);
    }

    private void asociarEventos() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password1 = String.valueOf(passField.getPassword());
                String password2 = String.valueOf(passField.getPassword());
                LocalDate birthdate = dateField.getDate();
                UserTypeEnum userType = (UserTypeEnum)userTypeField.getSelectedItem();

                // User validations
                if (verifyUsername(username) && verifyPass(password1, password2) && verifyUserType(userType) && !SystemUser.verifyUserExist(username)) {
                    SystemUser newUser = new SystemUser(username, password1, userType);
                    JOptionPane.showMessageDialog(null, "Usuario creado con exito.", "Usuario creado", JOptionPane.INFORMATION_MESSAGE);
                    self.dispose();
                }
                else if (username.equals("") || password1.equals("") || password2.equals("") || birthdate == null) {
                    JOptionPane.showMessageDialog(null, "Faltan completar campos y/o las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (SystemUser.verifyUserExist(username)) {
                    JOptionPane.showMessageDialog(null, "El usuario ingresado ya existe.", "Usuario existente", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
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
