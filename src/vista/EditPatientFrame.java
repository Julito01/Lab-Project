package vista;

import controllers.PatientController;
import dtos.PatientDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class EditPatientFrame extends JDialog {
    private JPanel mainPanel;
    private JLabel dniLabel;
    private JLabel nameLabel;
    private JLabel addressLabel;
    private JLabel mailLabel;
    private JLabel genreLabel;
    private JLabel ageLabel;
    private JTextField dniField;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField mailField;
    private JTextField ageField;
    private JComboBox genreField;
    private JButton editPatientButton;
    private PatientController patInstance;
    private EditPatientFrame self;
    private PatientDTO patientDTO;

    public EditPatientFrame(Window owner, String title, PatientDTO patient) {
        super(owner, title);
        this.patientDTO = patient;
        this.patInstance = PatientController.getInstance();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(460, 300);
        this.setResizable(false);
        this.setModal(true);
        this.self = this;
        this.bindEvents();
        setGenreModel();
        displayValues(patient);
        this.setLocationRelativeTo(null);
    }

    private void displayValues(PatientDTO patient) {
        dniField.setText(patient.getPatientDni());
        nameField.setText(patient.getName());
        addressField.setText(patient.getAddress());
        mailField.setText(patient.getMail());
        genreField.setSelectedItem(patient.getGenre());
        ageField.setText(patient.getAge());
    }

    private void setGenreModel() {
        List<String> genres = new ArrayList<>();
        genres.add("M");
        genres.add("F");
        genres.add("Otro");
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addAll(genres);
        genreField.setModel(model);
    }

    private void editPatientObj(PatientDTO patient) {
        patient.setPatientDni(dniField.getText());
        patient.setName(nameField.getText());
        patient.setAddress(addressField.getText());
        patient.setMail(mailField.getText());
        patient.setGenre(genreField.getSelectedItem().toString());
        patient.setAge(ageField.getText());
    }

    private void bindEvents() {
        editPatientButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                editPatientObj(patientDTO);
                patInstance.updatePatient(patientDTO);
            }
        });
    }
}
