package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class AdminMainFrame extends JFrame {
    private JPanel mainPanel;
    private JLabel passLabel;
    private JPanel header;
    private JPanel exitButton;
    private JLabel exitLabel;
    private JTabbedPane optionsPane;
    private JPanel patientTab;
    private JPanel stationTab;
    private JPanel practiceTab;
    private JPanel petitionTab;
    private JPanel resultTab;
    private JPanel userTab;
    private JLabel accountLabel;
    private JSeparator accountSeparator;
    private JPanel accountButton;
    private int xMouse, yMouse;
    private AdminMainFrame self;


    public AdminMainFrame() {
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

        accountLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                LoginFrame frame = new LoginFrame("App Laboratorios");
                frame.setVisible(true);
                self.dispose();
            }
        });
    }
}
