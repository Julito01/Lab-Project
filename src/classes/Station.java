package classes;

import config.Database;
import dtos.StationDTO;

import java.util.List;

public class Station {
    private static int counter = 1;
    private int stationId;
    private String address;

    public Station(StationDTO station) {
        this.stationId = counter;
        this.address = station.getAddress();
        counter++;
    }

    public void createStation(StationDTO station) {
        // Creates a new station
        Database.createStation(station);
    }

    public static void deleteStation(int stationId) {
        // Delete the desired station through the station id
        Database.deleteStation(stationId);
    }

    public static void updateStation(StationDTO station) {
        // Modifies a desired station through the station id
        Database.updateStation(station);
    }

    public static List<StationDTO> getAllStations() {
        return Database.getAllStations();
    }

    public String getAddress() {
        return this.address;
    }
}