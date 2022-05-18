package vista;

import classes.enumerations.UserTypeEnum;
import com.github.lgooddatepicker.components.DatePicker;
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
    private List<UserTypeEnum> userTypes = new ArrayList<UserTypeEnum>();

    public CreateUserFrame(Window owner, String title) {
        super(owner, title);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(460, 300);
        this.setResizable(false);
        this.setModal(true);
        this.asociarEventos();

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

                if (verifyUsername(username) && verifyPass(password1, password2) && verifyUserType(userType)) {
                    SystemUser newUser = new SystemUser(1, username, password1, birthdate, userType);
                    System.out.println("Usuario: " + username);
                    System.out.println("Contraseña: " + password1);
                    System.out.println("Fecha de nacimiento: " + birthdate);
                    System.out.println("Tipo de rol: " + userType);
                    JOptionPane.showMessageDialog(null, "Usuario creado con exito.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Faltan completar campos y/o las contraseñas no coinciden.");
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
