package vista;

import classes.Practice;
import com.github.lgooddatepicker.components.DatePicker;
import controllers.PatientController;
import controllers.PetitionController;
import dtos.PatientDTO;
import dtos.PetitionDTO;
import dtos.PracticeDTO;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RecMainFrame extends JFrame {
    // The RECEPTIONIST loads the PETITIONS and can check the RESULTS
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
    private JLabel loadDateLabel;
    private DatePicker loadDateField;
    private JLabel practLabel;
    private JComboBox practField;
    private JLabel etaLabel;
    private JTextField etaField;
    private JButton createPetitionButton;
    private JComboBox medInsField;
    private JPanel accountButton;
    private JLabel accountLabel;
    private JSeparator accountSeparator;
    private JLabel addLabel;
    private JLabel practAddedLabel;
    private JButton addPractButton;
    private List<PracticeDTO> practicesArray = new ArrayList<>();
    private RecMainFrame self;
    private PatientController patInstance;
    private PetitionController petInstance;
    private int xMouse, yMouse;

    public RecMainFrame() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.patInstance = PatientController.getInstance();
        this.petInstance = PetitionController.getInstance();
        this.setContentPane(mainPanel);
        this.setSize(1280, 800);
        this.getContentPane().setBackground(LoginFrame.bgColor);
        this.setResizable(false);
        this.setUndecorated(true);
        this.bindEvents();
        exitLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.etaField.setEditable(false);
        this.addLabel.setText("<html><u>Agregar</u></html>");
        this.addLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.self = this;
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

        // Event to change the color of the underline's account label
        accountLabel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                accountSeparator.setForeground(Color.white);
            }
        });
        accountLabel.addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent e) {
                accountSeparator.setForeground(Color.black);
            }
        });

        addLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (!practField.getSelectedItem().equals("...")) {
                    System.out.println(practField.getSelectedItem());
                    practAddedLabel.setText("Práctica agregada.");
                    practicesArray.add((PracticeDTO) practField.getSelectedItem());
                    startCountdownFromNow();
                    int practIndex = practField.getSelectedIndex();
                    practField.removeItemAt(practIndex);
                }
            }
        });

        accountLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                LoginFrame frame = new LoginFrame("App Laboratorios");
                frame.setVisible(true);
                self.dispose();
            }
        });

        // Event to create the petition and the patient (if it doesn't exist already)
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
                String medInsurance = medInsField.getSelectedItem().toString();
                LocalDate loadDate = loadDateField.getDate();
                List<PracticeDTO> practices = practicesArray;

                patInstance.setPatient(patientId, patientName, patientAddress, patientMail, patientGenre, patientAge);
                petInstance.setPetition(medInsurance, loadDate, practices);
                int practId = Practice.getCurrPracticeId(practField.getSelectedItem().toString());

            }
        });
    }

    private void startCountdownFromNow() {
        Timer t = new Timer(1050, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                practAddedLabel.setText("");
            }
        });
        t.setRepeats(false);
        t.start();
    }
}
