package classes;

import config.Database;
import dtos.PatientDTO;

import java.util.List;

public class Station {
    private int stationId;
    private String address;

    public Station(int stationId, String address) {
        this.stationId = stationId;
        this.address = address;
    }

    public static void main(String[] args) {

    }

    private void createStation() {Database.createStation(); }
        // Creates a new station


    private void deleteStation() {
        // Delete the desired station through the station id
    }

    private void editStation() {
        // Modifies a desired station through the station id
    }

    public static List<String> getAllStation() {
        List<String> station = Database.getAllStation();
        return station;
    }
}
