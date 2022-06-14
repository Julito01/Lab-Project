package vista;

import dtos.PatientDTO;
import controllers.PatientController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.*;
import java.util.List;
public class AdminMainFrame extends JFrame {
    private JPanel mainPanel;
    private JLabel passLabel;
    private JPanel header;
    private JPanel exitButton;
    private JLabel exitLabel;
    private JTabbedPane optionsPane;
    private JPanel stationTab;
    private JPanel practiceTab;
    private JPanel petitionTab;
    private JPanel resultTab;
    private JPanel userTab;
    private JLabel accountLabel;
    private JSeparator accountSeparator;
    private JPanel accountButton;
    private JPanel patientTab;
    private JLabel addPatientLabel;
    private JLabel deletePatientLabel;
    private JLabel editPatientLabel;
    private JScrollPane patientListPanel;
    private JList patientList;
    private JTextField searchPatientField;
    private JLabel searchPatientLabel;
    private JLabel refreshLabel;
    private JLabel addStationLabel;
    private JLabel deleteStationLabel;
    private JLabel editStationLabel;
    private JTextField stationField;
    private JLabel addressStationLabel;
    private JLabel refreshStationLabel;
    private JLabel addPracticeLabel;
    private JLabel deletePracticeLabel;
    private JLabel editPracticeLabel;
    private JTextField practiceField;
    private JLabel practiceLabel;
    private JLabel refreshPractLabel;
    private JLabel addPetitionLabel;
    private JLabel deletePetitionLabel;
    private JLabel editPetitionLabel;
    private JTextField petitionField;
    private JLabel petitionLabel;
    private JLabel addResultLabel;
    private JLabel deleteResultLabel;
    private JLabel editResultLabel;
    private JTextField resultField;
    private JLabel resultLabel;
    private JLabel refreshResultLabel;
    private JLabel addUserLabel;
    private JLabel deleteUserLabel;
    private List<PatientDTO> patients = new ArrayList<>();
    private static List<PatientDTO> defaultPatient = new ArrayList<>();
    private DefaultListModel<PatientDTO> listModel = new DefaultListModel<>();
    private List<PatientDTO> patientsArray = new ArrayList<>();
    private static boolean firstQuery = true;
    private PatientController patInstance;
    private int xMouse, yMouse;
    private AdminMainFrame self;

    public AdminMainFrame() {
        this.patInstance = PatientController.getInstance();
        this.self = this;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(1280, 800);
        this.getContentPane().setBackground(LoginFrame.bgColor);
        this.setResizable(false);
        this.setUndecorated(true);
        this.bindEvents();
        exitLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        accountLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addPatientLabel.setText("<html><u>Agregar paciente</u></html>");
        addPatientLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deletePatientLabel.setText("<html><u>Eliminar paciente</u></html>");
        deletePatientLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editPatientLabel.setText("<html><u>Editar paciente</u></html>");
        editPatientLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshLabel.setText("<html><u>Refrescar</u></html>");
        refreshLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        patientList.setModel(displayPatients());
        this.setLocationRelativeTo(null);
    }

    public static void setDefaultValuesArray(PatientDTO patient) {
        defaultPatient.add(patient);
    }

    public DefaultListModel<PatientDTO> displayPatients() {
        if (firstQuery) {
            patientsArray = patInstance.getAllPatients();
            for (PatientDTO patientDTO : patientsArray) {
                defaultPatient.add(patientDTO);
                listModel.addElement(patientDTO);
            }
            firstQuery = false;
        }
        else {
            listModel.removeAllElements();
            for (PatientDTO patient : defaultPatient) {
                listModel.addElement(patient);
            }
        }
        return listModel;
    }

    public void filterModel(DefaultListModel<PatientDTO> model, String filter) {
        for (PatientDTO s : defaultPatient) {
            if (!s.toString().startsWith(filter)) {
                if (model.contains(s)) {
                    model.removeElement(s);
                }
            } else {
                if (!model.contains(s)) {
                    model.addElement(s);
                }
            }
        }
    }

    private void getPatientId(String patientName) {
        for (int i = 0; i < patientsArray.size(); i++) {
            if (patientsArray.get(i).getName().equals(patientName)) {
                // nose
            }
        }
    }

    private void bindEvents() {
        editPatientLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                EditPatientFrame frame = new EditPatientFrame(self, "Editar paciente", (PatientDTO)patientList.getSelectedValue());
                frame.setVisible(true);
            }
        });

        refreshLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                patientList.setModel(displayPatients());
            }
        });

        deletePatientLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PatientDTO patientDTO = (PatientDTO) patientList.getSelectedValue();
                int patientToRemove = patientList.getSelectedIndex();
                patInstance.deletePatient(patientDTO.getPatientId());
                JOptionPane.showMessageDialog(null,"Paciente eliminado con exito.", "Paciente eliminado", JOptionPane.INFORMATION_MESSAGE);
                listModel.remove(patientToRemove);
                defaultPatient.remove(patientToRemove);
            }
        });

        searchPatientField.getDocument().addDocumentListener(new DocumentListener(){
            @Override public void insertUpdate(DocumentEvent e) { filter(); }
            @Override public void removeUpdate(DocumentEvent e) { filter(); }
            @Override public void changedUpdate(DocumentEvent e) {}
            private void filter() {
                String filter = searchPatientField.getText();
                filterModel((DefaultListModel<PatientDTO>) patientList.getModel(), filter);
            }
        });

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

        accountLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                LoginFrame frame = new LoginFrame("App Laboratorios");
                frame.setVisible(true);
                self.dispose();
            }
        });

        addPatientLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                AddPatientFrame frame = new AddPatientFrame(self, "Agregar paciente");
                frame.setVisible(true);
            }
        });
    }
}
