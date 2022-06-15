package vista;

import classes.DisplayClasses;
import classes.FilterModel;
import config.Database;
import controllers.*;
import dtos.*;

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
    private JLabel refreshPatientLabel;
    private JPanel stationTab;
    private JPanel stationPanel;
    private JScrollPane stationListPanel;
    private JLabel addStationLabel;
    private JLabel deleteStationLabel;
    private JLabel editStationLabel;
    private JTextField searchStationField;
    private JLabel searchStationLabel;
    private JLabel refreshStationLabel;
    private JList stationList;
    private JPanel petitionTab;
    private JPanel petitionPanel;
    private JScrollPane petitionListPanel;
    private JLabel addPetitionLabel;
    private JLabel deletePetitionLabel;
    private JLabel editPetitionLabel;
    private JTextField searchPetitionField;
    private JLabel searchPetitionLabel;
    private JLabel refreshPetitionLabel;
    private JPanel practiceTab;
    private JPanel practicePanel;
    private JScrollPane practiceListPanel;
    private JLabel addPracticeLabel;
    private JLabel deletePracticeLabel;
    private JLabel editPracticeLabel;
    private JTextField searchPractField;
    private JLabel searchPractLabel;
    private JLabel refreshPractLabel;
    private JList practiceList;
    private JPanel resultTab;
    private JPanel resultPanel;
    private JScrollPane resultListPanel;
    private JLabel addResultLabel;
    private JLabel deleteResultLabel;
    private JLabel editResultLabel;
    private JTextField searchResultField;
    private JLabel searchResultLabel;
    private JLabel refreshResultLabel;
    private JPanel userTab;
    private JPanel userPanel;
    private JScrollPane userListPanel;
    private JLabel addUserLabel;
    private JLabel deleteUserLabel;
    private JLabel editUserLabel;
    private JTextField searchUserField;
    private JLabel searchUserLabel;
    private JLabel refreshUserLabel;
    private JList petitionList;
    private JList resultList;
    private JList userList;

    // Default values arrays
    private List<PatientDTO> patients = new ArrayList<>();
    private static List<PatientDTO> defaultPatient = new ArrayList<>();
    private static List<StationDTO> defaultStation = new ArrayList<>();
    private static List<PetitionDTO> defaultPetition = new ArrayList<>();
    private static List<SystemUserDTO> defaultUser = new ArrayList<>();
    private static List<ResultDTO> defaultResult = new ArrayList<>();
    private static List<PracticeDTO> defaultPractice = new ArrayList<>();
    private DefaultListModel<PatientDTO> patientListModel = new DefaultListModel<>();
    private DefaultListModel<StationDTO> stationListModel = new DefaultListModel<>();
    private DefaultListModel<PetitionDTO> petitionListModel = new DefaultListModel<>();
    private DefaultListModel<SystemUserDTO> userListModel = new DefaultListModel<>();
    private DefaultListModel<ResultDTO> resultListModel = new DefaultListModel<>();
    private DefaultListModel<PracticeDTO> practiceListModel = new DefaultListModel<>();

    private List<PatientDTO> patientsArray = new ArrayList<>();
    private List<StationDTO> stationsArray = new ArrayList<>();
    private List<PracticeDTO> practicesArray = new ArrayList<>();
    private List<PetitionDTO> petitionsArray = new ArrayList<>();
    private List<ResultDTO> resultsArray = new ArrayList<>();
    private List<SystemUserDTO> usersArray = new ArrayList<>();
    private static boolean firstQuery = true;

    private PatientController patInstance;
    private StationController staInstance;
    private PetitionController petInstance;
    private PracticeController practInstance;
    private UserController usrInstance;
//    private ResultController resultInstance;

    private int xMouse, yMouse;
    private AdminMainFrame self;

    public AdminMainFrame() {
        this.patInstance = PatientController.getInstance();
        this.usrInstance = UserController.getInstance();
        this.petInstance = PetitionController.getInstance();
        this.staInstance = StationController.getInstance();
        this.practInstance = PracticeController.getInstance();
//        this.resultInstance = ResultController.getInstance();
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
        setUI();
        this.setLocationRelativeTo(null);
    }

    public static void setDefaultPatientArray(PatientDTO patient) {
        defaultPatient.add(patient);
    }
    public static void setDefaultStationArray(StationDTO station) {
        defaultStation.add(station);
    }


    private void bindEvents() {
        // Result events
        addResultLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                AddResultFrame frame  = new AddResultFrame(self, "Agregar resultado");
                frame.setVisible(true);
            }
        });

        editResultLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                EditResultFrame frame = new EditResultFrame(self, "Editar resultado", (ResultDTO)resultList.getSelectedValue());
                frame.setVisible(true);
            }
        });

        deleteResultLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ResultDTO resultDTO = (ResultDTO)resultList.getSelectedValue();
                int resultToRemove = resultList.getSelectedIndex();
//                resInstace.deleteResult(resultDTO.getResultId());
                JOptionPane.showMessageDialog(null,"Resultado eliminado con éxito.", "Resultado eliminado", JOptionPane.INFORMATION_MESSAGE);
                resultListModel.remove(resultToRemove);
                defaultResult.remove(resultToRemove);
            }
        });

        // Petition events
        addPetitionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                AddPetitionFrame frame = new AddPetitionFrame(self, "Agregar petición");
                frame.setVisible(true);
            }
        });

        editPetitionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                EditPetitionFrame frame = new EditPetitionFrame(self, "Editar petición", (PetitionDTO)petitionList.getSelectedValue());
                frame.setVisible(true);
            }
        });

        deletePetitionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PetitionDTO petitionDTO = (PetitionDTO)petitionList.getSelectedValue();
                int petitionToRemove = petitionList.getSelectedIndex();
                usrInstance.deleteUser(petitionDTO.getPetitionId());
                JOptionPane.showMessageDialog(null,"Petición eliminada con éxito.", "Petición eliminada", JOptionPane.INFORMATION_MESSAGE);
                petitionListModel.remove(petitionToRemove);
                defaultPatient.remove(petitionToRemove);
            }
        });

        // User events
        addUserLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                AddUserFrame frame = new AddUserFrame(self, "Agregar usuario");
                frame.setVisible(true);
            }
        });

        editUserLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                EditUserFrame frame = new EditUserFrame(self, "Editar usuario", (SystemUserDTO)userList.getSelectedValue());
                frame.setVisible(true);
            }
        });

        deleteUserLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                SystemUserDTO userDTO = (SystemUserDTO)userList.getSelectedValue();
                int patientToRemove = userList.getSelectedIndex();
                usrInstance.deleteUser(userDTO.getUserId());
                JOptionPane.showMessageDialog(null,"Usuario eliminado con éxito.", "Usuario eliminado", JOptionPane.INFORMATION_MESSAGE);
                userListModel.remove(patientToRemove);
                defaultUser.remove(patientToRemove);
            }
        });

        // Practice events
        addPracticeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                AddPracticeFrame frame = new AddPracticeFrame(self, "Agregar práctica");
                frame.setVisible(true);
            }
        });

        editPracticeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                EditPracticeFrame frame = new EditPracticeFrame(self, "Editar práctica", (PracticeDTO)practiceList.getSelectedValue());
                frame.setVisible(true);
            }
        });

        deletePracticeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PracticeDTO practiceDTO = (PracticeDTO) practiceList.getSelectedValue();
                int practiceToRemove = practiceList.getSelectedIndex();
                staInstance.deleteStation(practiceDTO.getPracticeId());
                JOptionPane.showMessageDialog(null,"Práctica eliminada con éxito.", "Práctica eliminada", JOptionPane.INFORMATION_MESSAGE);
                practiceListModel.remove(practiceToRemove);
                defaultPractice.remove(practiceToRemove);
            }
        });

        // Station events
        addStationLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                AddStationFrame frame = new AddStationFrame(self, "Agregar sucursal");
                frame.setVisible(true);
            }
        });

        editStationLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                EditStationFrame frame = new EditStationFrame(self, "Editar sucursal.", (StationDTO)stationList.getSelectedValue());
                frame.setVisible(true);
            }
        });

        deleteStationLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                StationDTO stationDTO = (StationDTO) stationList.getSelectedValue();
                int stationToRemove = stationList.getSelectedIndex();
                staInstance.deleteStation(stationDTO.getStationId());
                JOptionPane.showMessageDialog(null,"Sucursal eliminada con éxito.", "Sucursal eliminada", JOptionPane.INFORMATION_MESSAGE);
                stationListModel.remove(stationToRemove);
                defaultStation.remove(stationToRemove);
            }
        });

        searchStationField.getDocument().addDocumentListener(new DocumentListener(){
            @Override public void insertUpdate(DocumentEvent e) { filter(); }
            @Override public void removeUpdate(DocumentEvent e) { filter(); }
            @Override public void changedUpdate(DocumentEvent e) {}
            private void filter() {
                String filter = searchStationField.getText();
                FilterModel.filterModelStation((DefaultListModel<StationDTO>) stationList.getModel(), filter, defaultStation);
            }
        });

        // Patient events
        addPatientLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                AddPatientFrame frame = new AddPatientFrame(self, "Agregar paciente");
                frame.setVisible(true);
            }
        });

        editPatientLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                EditPatientFrame frame = new EditPatientFrame(self, "Editar paciente", (PatientDTO)patientList.getSelectedValue());
                frame.setVisible(true);
            }
        });

        deletePatientLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PatientDTO patientDTO = (PatientDTO) patientList.getSelectedValue();
                int patientToRemove = patientList.getSelectedIndex();
                patInstance.deletePatient(patientDTO.getPatientId());
                JOptionPane.showMessageDialog(null,"Paciente eliminado con éxito.", "Paciente eliminado", JOptionPane.INFORMATION_MESSAGE);
                patientListModel.remove(patientToRemove);
                defaultPatient.remove(patientToRemove);
            }
        });

        refreshPatientLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                patientList.setModel(DisplayClasses.displayPatients(patientsArray, defaultPatient, patientListModel));
            }
        });

        searchPatientField.getDocument().addDocumentListener(new DocumentListener(){
            @Override public void insertUpdate(DocumentEvent e) { filter(); }
            @Override public void removeUpdate(DocumentEvent e) { filter(); }
            @Override public void changedUpdate(DocumentEvent e) {}
            private void filter() {
                String filter = searchPatientField.getText();
                FilterModel.filterModelPatient((DefaultListModel<PatientDTO>) patientList.getModel(), filter, defaultPatient);
            }
        });

        // UI Events
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
    }

    private void getPatientId(String patientName) {
        for (int i = 0; i < patientsArray.size(); i++) {
            if (patientsArray.get(i).getName().equals(patientName)) {
                // nose
            }
        }
    }

    private void setUI() {
        // Patient panel
        addPatientLabel.setText("<html><u>Agregar paciente</u></html>");
        addPatientLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deletePatientLabel.setText("<html><u>Eliminar paciente</u></html>");
        deletePatientLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editPatientLabel.setText("<html><u>Editar paciente</u></html>");
        editPatientLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshPatientLabel.setText("<html><u>Refrescar</u></html>");
        refreshPatientLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        patientList.setModel(DisplayClasses.displayPatients(patientsArray, defaultPatient, patientListModel));

        // Station panel
        addStationLabel.setText("<html><u>Agregar sucursal</u></html>");
        addStationLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteStationLabel.setText("<html><u>Eliminar sucursal</u></html>");
        deleteStationLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editStationLabel.setText("<html><u>Editar sucursal</u></html>");
        editStationLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshStationLabel.setText("<html><u>Refrescar</u></html>");
        refreshStationLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        stationList.setModel(DisplayClasses.displayStations(stationsArray, defaultStation, stationListModel));

        // Practice panel
        addPracticeLabel.setText("<html><u>Agregar práctica</u></html>");
        addPracticeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deletePracticeLabel.setText("<html><u>Eliminar práctica</u></html>");
        deletePracticeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editPracticeLabel.setText("<html><u>Editar práctica</u></html>");
        editPracticeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshPractLabel.setText("<html><u>Refrescar</u></html>");
        refreshPractLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        practiceList.setModel(DisplayClasses.displayPractices(practicesArray, defaultPractice, practiceListModel));

        // Petition panel
        addPetitionLabel.setText("<html><u>Agregar petición</u></html>");
        addPetitionLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deletePetitionLabel.setText("<html><u>Eliminar petición</u></html>");
        deletePetitionLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editPetitionLabel.setText("<html><u>Editar petición</u></html>");
        editPetitionLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshPetitionLabel.setText("<html><u>Refrescar</u></html>");
        refreshPetitionLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        petitionList.setModel(DisplayClasses.displayPetitions(petitionsArray, defaultPetition, petitionListModel));

        // Result panel
        addResultLabel.setText("<html><u>Agregar resultado</u></html>");
        addResultLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteResultLabel.setText("<html><u>Eliminar resultado</u></html>");
        deleteResultLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editResultLabel.setText("<html><u>Editar resultado</u></html>");
        editResultLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshResultLabel.setText("<html><u>Refrescar</u></html>");
        refreshResultLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resultList.setModel(DisplayClasses.displayResults(resultsArray, defaultResult, resultListModel));

        // User panel
        addUserLabel.setText("<html><u>Agregar usuario</u></html>");
        addUserLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteUserLabel.setText("<html><u>Eliminar usuario</u></html>");
        deleteUserLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editUserLabel.setText("<html><u>Editar usuario</u></html>");
        editUserLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshUserLabel.setText("<html><u>Refrescar</u></html>");
        refreshUserLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        userList.setModel(DisplayClasses.displayUsers(usersArray, defaultUser, userListModel));

    }
}
