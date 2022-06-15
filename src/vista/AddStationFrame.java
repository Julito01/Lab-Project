package vista;

import controllers.StationController;
import dtos.StationDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddStationFrame extends JDialog {
    private JPanel mainPanel;
    private JLabel addressLabel;
    private JTextField addressField;
    private JTextField techUserField;
    private JLabel techUserLabel;
    private JButton createStationButton;
    private StationController sucInstance;
    private AddStationFrame self;

    public AddStationFrame(Window owner, String title) {
        super(owner, title);
        this.sucInstance = StationController.getInstance();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(460, 300);
        this.setResizable(false);
        this.setModal(true);
        this.self = this;
        this.bindEvents();
        this.setLocationRelativeTo(null);
    }

    private void bindEvents() {
        createStationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String address = addressField.getText();
                int techUserId = Integer.parseInt(techUserField.getText());
                if (sucInstance.setStation(new StationDTO(address, techUserId))) {
                    JOptionPane.showMessageDialog(null, "Sucursal agregada con éxito", "Sucursal agregada", JOptionPane.INFORMATION_MESSAGE);
                    self.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Faltan completar campos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
