package vista;

import classes.SystemUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTextField userField;
    private JPasswordField passField;
    private JLabel passLabel;
    private JLabel userLabel;
    private JButton accessButton;
    private JButton createUserButton;
    private JPanel header;
    private JPanel exitButton;
    private JLabel exitLabel;
    private JSeparator userSeparator;
    private JSeparator passSeparator;
    private LoginFrame self;
    private int xMouse, yMouse;
    public static Color bgColor = new Color(0x459aad);

    //Color verde 45932F
    public LoginFrame(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(1280, 800); // sets the x-dimension, and y-dimension of frame
        this.bindEvents();
        this.self = this;
        this.setUndecorated(true);
        this.getContentPane().setBackground(bgColor);

        // Edit of the top bar exit button behavior
        this.exitLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        /* Edit of the inputs */
        // Username input
        this.userField.setText("Ingrese su nombre de usuario...");
        this.userField.setForeground(Color.gray);
        this.userField.setBackground(bgColor);
        this.userField.setBorder(BorderFactory.createEmptyBorder());

        // Password input
        this.passField.setText("********");
        this.passField.setForeground(Color.gray);
        this.passField.setBackground(bgColor);
        this.passField.setBorder(BorderFactory.createEmptyBorder());

        this.setLocationRelativeTo(null); // center the app when starts
//        this.pack();
    }

    public static void main(String[] args) {
        LoginFrame frame = new LoginFrame("App Laboratorios"); // Creates a frame setting the title of the app
        frame.setVisible(true);

//        ImageIcon image = new ImageIcon("logo.png");
//        frame.setIconImage(image.getImage());
    }

    private void verifyLogin() {
        // TO DO something
    }

    private void bindEvents() {
        // Event to open create user panel
        createUserButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                CreateUserFrame frame = new CreateUserFrame(self, "Crear usuario");
                frame.setVisible(true);
            }
        });

        // Event to drag and relocate the app in the screen
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
                exitButton.setBackground(bgColor);
                exitLabel.setForeground(Color.white);
            }
        });

        // Event to empty the user and password input
        userField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // User input behavior
                if (userField.getText().equals("Ingrese su nombre de usuario...")) {
                    userField.setText("");
                    userField.setForeground(Color.white);
                }
                // Password input behavior
                if (String.valueOf(passField.getPassword()).isEmpty()) {
                    passField.setText("********");
                    passField.setForeground(Color.gray);
                }
            }
        });

        userField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                // User input behavior
                if (userField.getText().equals("Ingrese su nombre de usuario...")) {
                    userField.setText("");
                    userField.setForeground(Color.white);
                }
                // Password input behavior
                if (String.valueOf(passField.getPassword()).isEmpty()) {
                    passField.setText("********");
                    passField.setForeground(Color.gray);
                }
            }
        });

        passField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Password input behavior
                if (String.valueOf(passField.getPassword()).equals("********")) {
                    passField.setText("");
                    passField.setForeground(Color.white);
                }
                // User input behavior
                if (userField.getText().isEmpty()) {
                    userField.setText("Ingrese su nombre de usuario...");
                    userField.setForeground(Color.gray);
                }
            }
        });

        passField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                // Password input behavior
                if (String.valueOf(passField.getPassword()).equals("********")) {
                    passField.setText("");
                    passField.setForeground(Color.white);
                }
                // User input behavior
                if (userField.getText().isEmpty()) {
                    userField.setText("Ingrese su nombre de usuario...");
                    userField.setForeground(Color.gray);
                }
            }
        });

        accessButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String userInput = userField.getText();
                String passInput = String.valueOf(passField.getPassword());

                if (userInput.equals("") || passInput.equals("") || userInput.equals("Ingrese su nombre de usuario...") || passInput.equals("********")) {
                    JOptionPane.showMessageDialog(self, "Faltan completar campos", "Error", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    System.out.println("Usuario: " + userInput + "\nContraseña: " + userInput);
                    if (SystemUser.verifyUserExist(userInput)) {
                        self.dispose();
                        String userType = SystemUser.verifyUserType(userInput);
                        System.out.println("Tipo de usuario: " + userType);
                        if (userType.equals("ADMINISTRADOR")) {
                            AdminMainFrame frame = new AdminMainFrame();
                            frame.setVisible(true);
                        } else if (userType.equals("LABORATORISTA")) {
                            LabMainFrame frame = new LabMainFrame();
                            frame.setVisible(true);
                        }
                        else if (userType.equals("RECEPCIONISTA")) {
                            RecMainFrame frame = new RecMainFrame();
                            frame.setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El usuario ingresado no existe.");
                    }
                }
            }
        });
    }
}
