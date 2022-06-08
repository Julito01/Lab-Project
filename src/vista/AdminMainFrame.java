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
    private static List<dtos.PatientDTO> patients = new ArrayList<>();
    private static List<dtos.PatientDTO> defaultPatientNames = new ArrayList<>();
    private static DefaultListModel<dtos.PatientDTO> listModel = new DefaultListModel<>();
    private static List<dtos.PatientDTO> patientsArray = new ArrayList<>();
    private static boolean firstQuery = true;
    private PatientController cp;
    private int xMouse, yMouse;
    private AdminMainFrame self;

    public AdminMainFrame() {
        this.cp = PatientController.getInstance();
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
        editPatientLabel.setText("<html><u>Modificar paciente</u></html>");
        editPatientLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshLabel.setText("<html><u>Refrescar</u></html>");
        refreshLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        patientList.setModel(displayPatients());
        this.setLocationRelativeTo(null);
    }

    public static void setDefaultValuesArray(dtos.PatientDTO patient) {
        defaultPatientNames.add(patient);
    }

    private List<PatientDTO> getPatients() {
        this.patients = PatientDTO.getPatients();
        return this.patients;
    }

    public DefaultListModel<PatientDTO> displayPatients() {
        patientsArray = getPatients();
        if (firstQuery) {
            for (PatientDTO patientDTO : patientsArray) {
                defaultPatientNames.add(patientDTO);
                listModel.addElement(patientDTO);
            }
            firstQuery = false;
        }
        else {
            listModel.removeAllElements();
            for (PatientDTO defaultPatientName : defaultPatientNames) {
                listModel.addElement(defaultPatientName);
            }
        }
        return listModel;
    }

    public void filterModel(DefaultListModel<PatientDTO> model, String filter) {
        for (PatientDTO s : defaultPatientNames) {
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

    private static void getPatientId(String patientName) {
        for (int i = 0; i < patientsArray.size(); i++) {
            if (patientsArray.get(i).getName().equals(patientName)) {
                // nose
            }
        }
    }

    private void bindEvents() {
        refreshLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                patientList.setModel(displayPatients());
            }
        });

        deletePatientLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PatientDTO patient = (PatientDTO) patientList.getSelectedValue();
                int patientToRemove = patientList.getSelectedIndex();
                cp.deletePatient(patient.getPatientId());
                JOptionPane.showMessageDialog(null,"Paciente eliminado con exito.", "Paciente eliminado", JOptionPane.INFORMATION_MESSAGE);
                listModel.remove(patientToRemove);
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
