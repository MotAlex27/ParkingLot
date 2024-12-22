package com.parking.demo.common;

public class CarsDto {

    private Long id;
    private String licensePlate;
    private String brand;
    private String model;
    private String color;
    private String parkingSpot;

    public CarsDto() {
    }

    public CarsDto(String licensePlate, String brand, String model, String color, String parkingSpot) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.parkingSpot = parkingSpot;
    }

    public CarsDto(Long id, String licensePlate, String brand, String model, String color, String parkingSpot) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.parkingSpot = parkingSpot;
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(String parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
