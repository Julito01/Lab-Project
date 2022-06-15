package vista;

import controllers.StationController;
import dtos.StationDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditStationFrame extends JDialog {
    private JPanel mainPanel;
    private JLabel addressLabel;
    private JTextField addressField;
    private JLabel techUserLabel;
    private JTextField techUserField;
    private JButton editStationButton;
    private StationController staInstance;
    private EditStationFrame self;
    private StationDTO stationDTO;

    public EditStationFrame(Window owner, String title, StationDTO station) {
        super(owner, title);
        this.stationDTO = station;
        this.staInstance = StationController.getInstance();
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
        editStationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                editStationObj(stationDTO);
                staInstance.updateStation(stationDTO);
            }
        });
    }

    private void editStationObj(StationDTO station) {
        station.setAddress(station.getAddress());
    }
}
