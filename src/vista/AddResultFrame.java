package vista;

import classes.enumerations.ResultType;
import controllers.PetitionController;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class AddResultFrame extends JDialog {
    private AddResultFrame self;
    private PetitionController petInstance;
    private JLabel practIdLabel;
    private JTextField practIdField;
    private JLabel resultValueLabel;
    private JTextField resultValueField;
    private JLabel resultTypeLabel;
    private JComboBox resultTypeField;
    private JButton addResultButton;
    private JPanel mainPanel;
    private List<ResultType> resultTypes = new ArrayList<>();

    public AddResultFrame(Window owner, String title) {
        super(owner, title);
//        this.resInstance = ResultController.getInstance();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(460, 300);
        this.setResizable(false);
        this.setModal(true);
        this.self = this;
        this.bindEvents();

        // Adding the result types to the result type field
        resultTypes.add(ResultType.NORMAL);
        resultTypes.add(ResultType.CRITICO);
        resultTypes.add(ResultType.RESERVADO);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addAll(resultTypes);
        resultTypeField.setModel(model);

        this.setLocationRelativeTo(null);

    }

    private void bindEvents() {}
}
