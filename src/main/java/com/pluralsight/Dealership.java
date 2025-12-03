package com.pluralsight;

import java.util.ArrayList;

public class Dealership {
    private String name; //instance variables
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;


    //constructor
    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }
    //getters/setters

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
        // System.out.println("Vehicle: " + vehicle + "has been added");
    }

    public ArrayList<Vehicle> getAllVehicles() {

        return inventory;
    }


    public ArrayList<Vehicle> getVehiclesByPrice2(Double min, Double max) { //<- parameters inside
        ArrayList<Vehicle> foundVehicles = new ArrayList<>();

        for (Vehicle v : inventory) {
            if (v.getPrice() >= min && v.getPrice() <= max) {
                foundVehicles.add(v);
            }
        }
        return foundVehicles;
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {

        ArrayList<Vehicle> mmV = new ArrayList<>();


        for (Vehicle v : inventory) {
            if (v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model)) {
               mmV.add(v);

            }
        }
        return mmV;

    }

    public ArrayList<Vehicle> getVehiclesByYear(int min, int max) {

        ArrayList<Vehicle> vehicleYear = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getYear() >= min && v.getYear() <= max) {
                vehicleYear.add(v);
            }
        }
        return vehicleYear;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> vehicleColor = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getColor().equalsIgnoreCase(color)) {
                vehicleColor.add(v);

            }
        }
        return vehicleColor;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max) {
        ArrayList<Vehicle> vehicleMileage = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getOdometer() >= min && v.getOdometer() <= max) {
                vehicleMileage.add(v);
            }
        }
       return vehicleMileage;
    }

    public ArrayList<Vehicle> getVehiclesByType(String vehicleType) {
        ArrayList<Vehicle> vehiclesType = new ArrayList<>();

        for (Vehicle v : inventory) {
            if (v.getVehicleType().equalsIgnoreCase(vehicleType)) {
                vehiclesType.add(v);

            }
        }
        return vehiclesType;

    }


public Vehicle getVehicleByVIN(int vin){
        for ( Vehicle v : this.getAllVehicles()){
            if (v.getVin() == vin){
                return v;
            }
        }
        return null;
}

public boolean removeVehicle(int vin){
    Vehicle v = getVehicleByVIN(vin);
    if(v == null){
        return false;
    }
    return inventory.remove(v);
}



}

