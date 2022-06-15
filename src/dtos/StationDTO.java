package dtos;

import config.Database;

public class StationDTO {
    private static int counter = 1;
    private final int techUserId;
    private int stationId;
    private String address;

    public StationDTO(String address, int techUserId) {
        this.stationId = counter;
        this.address = address;
        this.techUserId = techUserId;
        counter++;
    }

    @Override
    public String toString() {
        return this.address;
    }

    // Getters
    public int getStationId() {
        return this.stationId;
    }

    public String getAddress() {
        return this.address;
    }

    public int getTechUserId() {
        return this.techUserId;
    }

    // Setters
    public void setAddress(String address) {
        this.address = address;
    }
}
