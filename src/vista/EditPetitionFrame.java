package vista;

import com.github.lgooddatepicker.components.DatePicker;
import controllers.PetitionController;
import dtos.PetitionDTO;

import javax.swing.*;
import java.awt.*;

public class EditPetitionFrame extends JDialog {
    private EditPetitionFrame self;
    private PetitionController petInstance;
    private JPanel mainPanel;
    private JLabel dniLabel;
    private JTextField dniField;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel addressLabel;
    private JTextField addressField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel genreLabel;
    private JComboBox genreBoxField;
    private JLabel ageLabel;
    private JTextField ageField;
    private JLabel medInsLabel;
    private JLabel loadDateLabel;
    private DatePicker loadDateField;
    private JLabel practLabel;
    private JComboBox practField;
    private JLabel etaLabel;
    private JTextField etaField;
    private JButton createPetitionButton;
    private JComboBox medInsField;
    private JLabel addLabel;
    private JLabel practAddedLabel;
    private PetitionDTO petitionDTO;

    public EditPetitionFrame(Window owner, String title, PetitionDTO petitionDTO) {
        super(owner, title);
        this.petitionDTO = petitionDTO;
        this.petInstance = PetitionController.getInstance();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
//        this.setSize(600, 300);
        this.setResizable(false);
        this.setModal(true);
        this.self = this;
        this.bindEvents();
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void bindEvents() {}
}
