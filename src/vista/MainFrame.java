package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTextField userField;
    private JLabel passLabel;
    private JLabel userLabel;
    private JPasswordField passField;
    private JButton accessButton;
    private JButton createUserButton;
    private JPanel header;
    private JPanel exitButton;
    private JLabel exitLabel;
    private JSeparator userSeparator;
    private JSeparator passSeparator;
    private MainFrame self;
    private int xMouse, yMouse;
    private static Color bgColor = new Color(0x123456);

    public MainFrame(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(800, 500); // sets the x-dimension, and y-dimension of frame
        this.bindEvents();
        this.self = this;
        this.setUndecorated(true);
        this.getContentPane().setBackground(bgColor);
        this.setLocationRelativeTo(null); // center the app when starts
//        this.pack();
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame("App prueba"); // Creates a frame setting the title of the app
        frame.setVisible(true);

        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());

        // Edit of the top bar exit button behavior
        frame.exitLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));


        /* Edit of the inputs */
        // Username input
        frame.userField.setText("Ingrese su nombre de usuario...");
        frame.userField.setForeground(Color.gray);
        frame.userField.setBackground(bgColor);
        frame.userField.setBorder(BorderFactory.createEmptyBorder());


        // Password input
        frame.passField.setText("********");
        frame.passField.setForeground(Color.gray);
        frame.passField.setBackground(bgColor);
        frame.passField.setBorder(BorderFactory.createEmptyBorder());
    }

    private void bindEvents() {
        // Event to open create user panel
        createUserButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FrmCrearUsuario frame = new FrmCrearUsuario(self, "Crear usuario");
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

        accessButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String userInput = userField.getText();
                String passInput = String.valueOf(passField.getPassword());

                if (userInput != "" && passInput != "") {
                    System.out.println(userInput == "Ingrese su nombre de usuario...");
                    System.out.println(passInput == "********");
                    if (userInput == "Ingrese su nombre de usuario..." && passInput == "********"){
                        System.out.println("Error, datos incompletos");
                    }
                }
                else {
                    System.out.println("Inicia sesion");
                }
            }
        });


    }
}
