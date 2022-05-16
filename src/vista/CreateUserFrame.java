package vista;

import model.UserType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class FrmCrearUsuario extends JDialog {
    private JPanel mainPanel;
    private JTextField userField;
    private JPasswordField passField;
    private JPasswordField confirmPassField;
    private JLabel userLabel;
    private JLabel passLabel;
    private JLabel confirmPassLabel;
    private JLabel userTypeLabel;
    private JComboBox userTypeField;
    private JButton submitButton;
    private List<UserType> userTypes = new ArrayList<UserType>();

    public FrmCrearUsuario(Window owner, String title) {
        super(owner, title);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(400, 300);
        this.setResizable(false);
        this.setModal(true); // No permite volver a la pantalla anterior hasta cerrar esta
        this.asociarEventos();

        userTypes.add(new UserType(1, "Administrador"));
        userTypes.add(new UserType(2, "Laboratorista"));
        userTypes.add(new UserType(3, "Recepcionista"));
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addAll(userTypes);
        userTypeField.setModel(model);

        this.setLocationRelativeTo(null);
    }

    private void asociarEventos() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password1 = passField.getText();
                String password2 = confirmPassField.getText();
                Object userType = userTypeField.getSelectedItem();

                if (verifyUsername(username) && verifyPass(password1, password2) && verifyUserType(userType)) {
                    System.out.println("Nombre del usuario: " + userField.getText());
                    System.out.println("Contraseña del usuario: " + passField.getText());
                    System.out.println("Tipo de usuario creado: " + userTypeField.getSelectedItem());
                    JOptionPane.showMessageDialog(null, "Usuario creado con exito.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Faltan completar campos y/o las contraseñas no coinciden.");
                }
            }
        });
    }

    private boolean verifyUsername(String username) {
        if (username.length() != 0) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean verifyPass(String pass1, String pass2) {
        if (pass1.matches(pass2)) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean verifyUserType(Object userType) {
        if (userType != null) {
            return true;
        }
        else {
            return false;
        }
    }

}
