/*
Name: Harrison Tinley
Date: 3/7/26
Program: car object for holding our car data from the csv
*/


import java.util.ArrayList;

public class Car {
    private String carID;
    private String brand;
    private String model;
    private int year;
    private String fuel_type;
    private String color;
    private double mileage;


    public Car(String id, String brand, String model, int year, String fuel_type, String color, double mileage) {
        this.carID = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.fuel_type = fuel_type;
        this.color = color;
        this.mileage = mileage;
    }

    public String getCarID() {
        return carID;
    }
    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }
    public int getYear() {
        return year;
    }
    public String getFuelType() {
        return fuel_type;
    }
    public String getColor() {
        return color;
    }
    public double getMileage() {
        return mileage;
    }

    @Override
    public String toString() {
        return "Car{" +
                "ID='" + carID + "', " +
                "brand='" + brand + "', " +
                "model='" + model + "', " +
                "year='" + year + "', " +
                "fuel_type='" + fuel_type + "', " +
                "color='" + color + "', " +
                "mileage='" + mileage + "'}\n";
    }


}