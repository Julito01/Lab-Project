package vista;

import controllers.PracticeController;
import dtos.PatientDTO;
import dtos.PracticeDTO;

import javax.swing.*;
import java.awt.*;

public class EditPracticeFrame extends JDialog {
    private PracticeDTO practiceDTO;
    private PracticeController practInstance;
    private EditPracticeFrame self;
    private JPanel mainPanel;
    private JTextField practCodeField;
    private JButton editPractButton;
    private JLabel practCodeLabel;
    private JLabel practNameLabel;
    private JLabel ethLabel;
    private JTextField practNameField;
    private JTextField ethField;

    public EditPracticeFrame(Window owner, String title, PracticeDTO practice) {
        super(owner, title);
        this.practiceDTO = practice;
        this.practInstance = PracticeController.getInstance();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(460, 300);
        this.setResizable(false);
        this.setModal(true);
        this.self = this;
        this.bindEvents();
        displayValues(practice);
        this.setLocationRelativeTo(null);
    }

    private void displayValues(PracticeDTO practice) {
        practCodeField.setText(String.valueOf(practice.getPracticeCode()));
        practNameField.setText(practice.getPracticeName());
        ethField.setText(String.valueOf(practice.getEth()));
    }

    private void bindEvents() {

    }

}
