package com.pluralsight.userInterface;

import com.pluralsight.models.LeaseContract;
import com.pluralsight.models.SalesContract;
import com.pluralsight.models.Vehicle;
import com.pluralsight.persistance.DataManager;
import com.pluralsight.persistance.LeaseDao;
import com.pluralsight.persistance.SalesDao;

import java.sql.SQLException;
import java.util.List;

public class UserInterface {

    private DataManager dataManager;
    private SalesDao salesDao;
    private LeaseDao leaseDao;

    public UserInterface(DataManager dataManager, SalesDao salesDao, LeaseDao leaseDao) {
        this.dataManager = dataManager;
        this.salesDao = salesDao;
        this.leaseDao = leaseDao;
    }
    public void display() {
        String menu = """
                    DEALERSHIP MAIN MENU
                    1) Search by price range
                    2) Search by make/model
                    3) Search by year range
                    4) Search by color
                    5) Search by mileage range
                    6) Search by type
                    7) Add a vehicle
                    8) Remove a vehicle
                    9) Create a Sale contract
                    10) Create Lease contract
                    0) Exit
                """;
        while (true) {
            System.out.println(menu);
            int choice = ConsoleHelper.promptForInt("Enter option");

            try {
                switch (choice) {
                    case 1 -> searchByPrice();
                    case 2 -> searchByMakeModel();
                    case 3 -> searchByYearRange();
                    case 4 -> searchByColor();
                    case 5 -> searchByMileage();
                    case 6 -> searchByType();
                    case 7 -> addVehicle();
                    case 8 -> removeVehicle();
                    case 9 -> createSaleContract();
                    case 10 -> createLeaseContract();
                    case 99 -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid option.");
                }

            } catch (SQLException e) {
                System.out.println("SQL ERROR: " + e.getMessage());
            }
        }
    }
    private void searchByPrice() throws SQLException {
        double min = ConsoleHelper.promptForDouble("Minimum price ");
        double max = ConsoleHelper.promptForDouble("Maximum price ");

        List<Vehicle> list = dataManager.getVehiclesByPriceRange(min, max);
        list.forEach(System.out::println);
    }

    private void searchByMakeModel() throws SQLException {
        String make = ConsoleHelper.promptForString("Make: ");
        String model = ConsoleHelper.promptForString("Model: ");

        List<Vehicle> list = dataManager.getVehiclesByMakeModel(make, model);
        list.forEach(System.out::println);
    }

    private void searchByYearRange() throws SQLException {
        int min = ConsoleHelper.promptForInt("Min year: ");
        int max = ConsoleHelper.promptForInt("Max year: ");

        List<Vehicle> list = dataManager.getVehiclesByYearRange(min, max);
        list.forEach(System.out::println);
    }

    private void searchByColor() throws SQLException {
        String color = ConsoleHelper.promptForString("Color: ");
        List<Vehicle> list = dataManager.getVehiclesByColor(color);
        list.forEach(System.out::println);
    }

    private void searchByMileage() throws SQLException {
        int min = ConsoleHelper.promptForInt("Min mileage: ");
        int max = ConsoleHelper.promptForInt("Max mileage: ");

        List<Vehicle> list = dataManager.getVehiclesByMileageRange(min, max);
        list.forEach(System.out::println);
    }

    private void searchByType() throws SQLException {
        String type = ConsoleHelper.promptForString("Vehicle type: ");
        List<Vehicle> list = dataManager.getVehiclesByType(type);
        list.forEach(System.out::println);
    }

    // ------------------- ADD / REMOVE METHODS --------------------

    private void addVehicle() throws SQLException {
        String vin = ConsoleHelper.promptForString("VIN: ");
        int year = ConsoleHelper.promptForInt("Year: ");
        String make = ConsoleHelper.promptForString("Make: ");
        String model = ConsoleHelper.promptForString("Model: ");
        String type = ConsoleHelper.promptForString("Type: ");
        String color = ConsoleHelper.promptForString("Color: ");
        int odometer = ConsoleHelper.promptForInt("Mileage: ");
        double price = ConsoleHelper.promptForDouble("Price: ");

        Vehicle v = new Vehicle(vin, year, make, model, type, color, odometer, price);
        dataManager.addVehicle(v);

        System.out.println("Vehicle added!");
    }

    private void removeVehicle() throws SQLException {
        String vin = ConsoleHelper.promptForString("Enter VIN to remove: ");
        dataManager.removeVehicleByVin(vin);
        System.out.println("Vehicle removed.");
    }

    // ------------------- CONTRACTS --------------------

    private void createSaleContract() throws SQLException {
        String vin = ConsoleHelper.promptForString("VIN to sell: ");
        Vehicle v = dataManager.geVehicleByVin(vin);

        if (v == null) {
            System.out.println("Vehicle not found!");
            return;
        }

        String name = ConsoleHelper.promptForString("Customer name: ");
        String email = ConsoleHelper.promptForString("Customer email: ");
        boolean finance = ConsoleHelper.promptForString("Finance (y/n): ")
                .equalsIgnoreCase("y");

        SalesContract c = new SalesContract(name, email, v, finance);

        salesDao.saveSalesContract(c);
        dataManager.markVehicleAsSold(vin);

        System.out.println("Sale contract created!");
    }

    private void createLeaseContract() throws SQLException {
        String vin = ConsoleHelper.promptForString("VIN to lease: ");
        Vehicle v = dataManager.geVehicleByVin(vin);

        if (v == null) {
            System.out.println("Vehicle not found!");
            return;
        }

        String name = ConsoleHelper.promptForString("Customer name: ");
        String email = ConsoleHelper.promptForString("Customer email: ");

        LeaseContract c = new LeaseContract(name, email, v);

        leaseDao.saveLeaseContract(c);
        dataManager.markVehicleAsSold(vin);

        System.out.println("Lease contract created!");
    }


}
