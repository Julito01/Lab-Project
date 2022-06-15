package vista;

import com.github.lgooddatepicker.components.DatePicker;
import controllers.PracticeController;
import controllers.UserController;
import dtos.PracticeDTO;
import dtos.SystemUserDTO;

import javax.swing.*;
import java.awt.*;

public class EditUserFrame extends JDialog {
    private UserController usrInstance;
    private SystemUserDTO systemUserDTO;
    private EditUserFrame self;
    private JLabel userLabel;
    private JTextField userField;
    private JLabel passLabel;
    private JPasswordField passField;
    private JLabel confirmPassLabel;
    private JPasswordField confirmPassField;
    private JButton submitButton;
    private JLabel dateLabel;
    private DatePicker dateField;
    private JLabel userTypeLabel;
    private JComboBox userTypeField;
    private JPanel mainPanel;

    public EditUserFrame(Window owner, String title, SystemUserDTO systemUserDTO) {
        super(owner, title);
        this.systemUserDTO = systemUserDTO;
        this.usrInstance = UserController.getInstance();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(460, 300);
        this.setResizable(false);
        this.setModal(true);
        this.self = this;
        this.bindEvents();
//        displayValues(practice);
        this.setLocationRelativeTo(null);
    }

    private void displayValues(SystemUserDTO systemUser) {
        userField.setText(systemUser.getUsername());
//        dateField.setDate(systemUser.getDate());
    }

    private void bindEvents() {}
}
