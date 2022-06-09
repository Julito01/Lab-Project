package vista;

import controllers.PatientController;

import javax.swing.*;
import java.awt.*;
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
    private PatientController cp;
    private EditPatientFrame self;

    public EditPatientFrame(Window owner, String title) {
        super(owner, title);
        this.cp = PatientController.getInstance();
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

    }
}
