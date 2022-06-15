package controllers;

import classes.Patient;
import classes.Station;
import dtos.PatientDTO;
import dtos.StationDTO;
import vista.AdminMainFrame;

public class SucursalController {
    private static SucursalController scObject;
    private SucursalController() {}

    public static SucursalController getInstance() {
        if (scObject == null) {
            scObject = new SucursalController();
        }
        return scObject;
    }

    public boolean setSTation(StationDTO station) {
        if (verifyAddress(station.getAddress())) {
            Station newStation = new Station(station);
            newStation.createStation();
            AdminMainFrame.setDefaultStationArray(station);
            return true;
        } else {
            return false;
        }
    }

    private boolean verifyAddress(String address) {
        if (address.length() > 5) {
            return true;
        }
        return false;
    }
}
