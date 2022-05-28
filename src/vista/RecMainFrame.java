package vista;

import classes.Patient;
import classes.Petition;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class RecMainFrame extends JFrame {
    // El recepcionista carga las PETICIONES y puede consultar los RESULTADOS
    // Posible implementación de base de datos
    private JPanel mainPanel;
    private JTabbedPane optionsPane;
    private JPanel resultTab;
    private JPanel header;
    private JPanel exitButton;
    private JLabel exitLabel;
    private JPanel petitionTab;
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
    private JTextField textField2;
    private JLabel loadDateLabel;
    private DatePicker loadDateField;
    private JLabel practLabel;
    private JComboBox practField;
    private JLabel etaLabel;
    private JTextField etaField;
    private JLabel practAddedLabel;
    private JButton createPetitionButton;
    private int xMouse, yMouse;

    public RecMainFrame() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(1280, 800);
        this.getContentPane().setBackground(LoginFrame.bgColor);
        this.setResizable(false);
        this.setUndecorated(true);
        this.bindEvents();
        exitLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.etaField.setEditable(false);
        this.setLocationRelativeTo(null);
    }

    private void bindEvents() {
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - xMouse, y - yMouse);
            }
        });

        // Event to add styles to the exit button when hover it
        exitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        exitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setBackground(Color.red);
                exitLabel.setForeground(Color.black);
            }
        });
        exitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setBackground(LoginFrame.bgColor);
                exitLabel.setForeground(Color.white);
            }
        });

        // Event to create the petition and the pacient (if it doesn't exists already)
        createPetitionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Patient data
                String patientId = dniField.getText();
                String patientName = nameField.getText();
                String patientAddress = addressField.getText();
                String patientMail = emailField.getText();
                String patientGenre = genreBoxField.getSelectedItem().toString();
                String patientAge = ageField.getText();

                // Petition data
                int petitionId = Petition.getPetitionId();


                Patient patient = new Patient(patientId, patientName, patientAddress, patientMail, patientGenre, patientAge);
            }
        });
    }
}
