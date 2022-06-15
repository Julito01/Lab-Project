package classes;

import config.Database;
<<<<<<< HEAD
import dtos.PatientDTO;
=======
import dtos.StationDTO;
>>>>>>> d96e22e5c5e4c26d4ec68b50cb7b03cd84cd1cf0

import java.util.List;

public class Station {
    private static int counter = 1;
    private int stationId;
    private String address;

<<<<<<< HEAD
    public Station(int stationId, String address) {
        this.stationId = stationId;
        this.address = address;
    }

    public static void main(String[] args) {

    }

    private void createStation() {Database.createStation(); }
        // Creates a new station


    private void deleteStation() {
=======
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
>>>>>>> d96e22e5c5e4c26d4ec68b50cb7b03cd84cd1cf0
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

    public static List<String> getAllStation() {
        List<String> station = Database.getAllStation();
        return station;
    }
}
