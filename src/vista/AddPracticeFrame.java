package vista;

import controllers.PracticeController;

import javax.swing.*;
import java.awt.*;

public class AddPracticeFrame extends JDialog {
    private PracticeController practInstance;
    private AddPracticeFrame self;
    private JPanel mainPanel;
    private JLabel addressLabel;
    private JLabel techUserLabel;
    private JTextField addressField;
    private JTextField techUserField;
    private JButton createPractButton;
    private JTextField ethField;
    private JLabel ethLabel;

    public AddPracticeFrame(Window owner, String title) {
        super(owner, title);
        this.practInstance = PracticeController.getInstance();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(460, 300);
        this.setResizable(false);
        this.setModal(true);
        this.self = this;
        this.bindEvents();
        this.setLocationRelativeTo(null);
    }

    private void bindEvents() {

    }

}
