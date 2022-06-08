package vista;

import classes.Patient;
import controllers.PatientController;

import javax.swing.*;
import java.awt.*;

public class AddPatient {
    private JPanel mainPanel;
    private JTextField dniField;
    private JTextField nameField;
    private JLabel nameLabel;
    private JLabel dniLabel;
    private JLabel addressLabel;
    private JTextField addressField;
    private JTextField emailField;
    private JComboBox genreField;
    private JTextField ageField;
    private JLabel emailLabel;
    private JLabel genreLabel;
    private JLabel ageLabel;
    private JButton addPatientButton;

    public AddPatient(Window owner, String title) {
        super(owner, title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(460, 300);
        this.setResizable(false);
        this.setModal(true);
        this.self = this;
        this.bindEvents();
    }
}
