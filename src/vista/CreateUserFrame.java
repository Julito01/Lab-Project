package vista;

import classes.enumerations.UserTypeEnum;
import com.github.lgooddatepicker.components.DatePicker;
import config.Database;
import controllers.UserController;
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
    private List<UserTypeEnum> userTypes = new ArrayList<>();
    private List<String[]> usersFetched = new ArrayList<>();
    private UserController userInstance;

    public CreateUserFrame(Window owner, String title) {
        super(owner, title);
        this.userInstance = UserController.getInstance();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(460, 300);
        this.setResizable(false);
        this.setModal(true);
        this.self = this;
        this.bindEvents();

        // Adding the user types to the user type field
        userTypes.add(UserTypeEnum.ADMINISTRADOR);
        userTypes.add(UserTypeEnum.LABORATORISTA);
        userTypes.add(UserTypeEnum.RECEPCIONISTA);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addAll(userTypes);
        userTypeField.setModel(model);

        this.setLocationRelativeTo(null);
    }

    private void bindEvents() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password1 = String.valueOf(passField.getPassword());
                String password2 = String.valueOf(passField.getPassword());
                LocalDate birthdate = dateField.getDate();
                UserTypeEnum userType = (UserTypeEnum)userTypeField.getSelectedItem();

                if (userInstance.createUser(username, password1, password2, birthdate, userType)) {
                    self.dispose();
                }
            }
        });
    }
}
