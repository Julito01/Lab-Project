package vista;

import controllers.PatientController;
import dtos.PatientDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddPatientFrame extends JDialog {
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
    private PatientController patInstance;
    private AddPatientFrame self;

    public AddPatientFrame(Window owner, String title) {
        super(owner, title);
        this.patInstance = PatientController.getInstance();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(460, 300);
        this.setResizable(false);
        this.setModal(true);
        this.self = this;
        this.bindEvents();
        setGenreModel();
        this.setLocationRelativeTo(null);
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

    private void bindEvents() {
        addPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = dniField.getText();
                String name = nameField.getText();
                String address = addressField.getText();
                String email = emailField.getText();
                String genre = genreField.getSelectedItem().toString();
                String age = ageField.getText();

                if (patInstance.setPatient(new PatientDTO(id, name, address, email, genre, age))) {
                    JOptionPane.showMessageDialog(null, "Paciente agregado con éxito.", "Paciente agregado", JOptionPane.INFORMATION_MESSAGE);
                    self.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Faltan completar campos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
