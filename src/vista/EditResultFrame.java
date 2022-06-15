package vista;

import controllers.PetitionController;
import controllers.UserController;
import dtos.ResultDTO;
import dtos.SystemUserDTO;

import javax.swing.*;
import java.awt.*;

public class EditResultFrame extends JDialog {
    private ResultDTO resultDTO;
    private PetitionController petInstance;
    private EditResultFrame self;
    private JLabel practIdLabel;
    private JTextField practIdField;
    private JLabel resultValueLabel;
    private JTextField resultValueField;
    private JLabel resultTypeLabel;
    private JComboBox resultTypeField;
    private JButton addResultButton;
    private JPanel mainPanel;

    public EditResultFrame(Window owner, String title, ResultDTO resultDTO) {
        super(owner, title);
        this.resultDTO = resultDTO;
//        this.resInstance = ResultController.getInstance();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
//        this.setSize(460, 300);
        this.setResizable(false);
        this.setModal(true);
        this.self = this;
        this.bindEvents();
//        displayValues(practice);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void displayValues(ResultDTO result) {
//        resultValueField.setText(result.getValue());
//        dateField.setDate(systemUser.getDate());
    }

    private void bindEvents() {}
}
