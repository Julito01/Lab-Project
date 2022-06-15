package controllers;

import classes.Station;
import dtos.StationDTO;
import vista.AdminMainFrame;

import java.util.List;

public class StationController {
    private static StationController scObject;
    private StationController() {}

    public static StationController getInstance() {
        if (scObject == null) {
            scObject = new StationController();
        }
        return scObject;
    }

    public boolean setStation(StationDTO station) {
        if (verifyAddress(station.getAddress())) {
            Station newStation = new Station(station);
            newStation.createStation(station);
            AdminMainFrame.setDefaultStationArray(station);
            return true;
        }
        return false;
    }

    public List<StationDTO> getAllStations() {
        return Station.getAllStations();
    }

    public void deleteStation(int stationId) {
        Station.deleteStation(stationId);
    }

    public void updateStation(StationDTO station) {
        Station.updateStation(station);
    }

    private boolean verifyAddress(String address) {
        if (address.length() > 5) {
            return true;
        }
        return false;
    }
}
