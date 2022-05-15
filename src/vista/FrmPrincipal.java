package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmPrincipal extends JFrame {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTextField userField;
    private JLabel passLabel;
    private JLabel userLabel;
    private JPasswordField passField;
    private JButton accessButton;
    private JButton createUserButton;
    private FrmPrincipal self;

    public FrmPrincipal(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(500, 400); // sets the x-dimension, and y-dimension of frame
        this.asociarEventos();
        this.self = this;
        this.setLocationRelativeTo(null); // center the app when starts
//        this.pack();
    }

    public static void main(String[] args) {
        FrmPrincipal frame = new FrmPrincipal("App prueba"); // Creates a frame setting the title of the app
        frame.setVisible(true);

        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(new Color(0x123456));
    }

    private void asociarEventos() {
        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmCrearUsuario frame = new FrmCrearUsuario(self, "Crear usuario");
                frame.setVisible(true);
            }
        });
    }
}
