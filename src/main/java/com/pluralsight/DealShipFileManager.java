package com.pluralsight;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;

public class DealShipFileManager {
    //method that reads from file

    public static Dealership getDealership() {
        Dealership dealership = null;
        try {
            // BufferedReader br = new BufferedReader(new FileReader("inventory.csv"));
            FileReader fileReader = new FileReader("inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //read the first lines
            String firstLine = bufferedReader.readLine();
            String[] partsDl = firstLine.split("\\|");

            //create a dealership object
            String name = partsDl[0];
            String address = partsDl[1];
            String phone = partsDl[2];
            dealership = new Dealership(name, address, phone);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //split the file by parts
                String[] parts = line.split("\\|");

                int vin = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String make = parts[2];
                String model = parts[3];
                String vehicleType = parts[4];
                String color = parts[5];
                int odometer = Integer.parseInt(parts[6]);
                double price = Double.parseDouble(parts[7]);

                // create new vehicle object
                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                //add it to dealership
                dealership.addVehicle(vehicle);


            }
            bufferedReader.close();
            fileReader.close();

        } catch (IOException e) {
            System.out.println("Inventory file not found! ");

        }

        return dealership;
    }

    public static void saveDealership(Dealership dealership) throws IOException {
        //open the file
        BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.csv"));
        String dealershipInfo = dealership.getName() + "|" + dealership.getAddress()
                + "|" + dealership.getPhone();
        writer.write(dealershipInfo);
        writer.newLine();

        //save in dealership
        for(Vehicle v: dealership.getAllVehicles()) {
            String vehicleInfo = v.getVin() + "|" + v.getYear() + "|" +
                    v.getMake() +"|" + v.getModel() + "|" + v.getVehicleType() +
                    "|" + v.getColor() + "|" + v.getOdometer() + "|"  + v.getPrice();

            writer.write(vehicleInfo);
            writer.newLine();
        }
        //close the file
        writer.close();
        System.out.println("Saved successfully");
    }

}




