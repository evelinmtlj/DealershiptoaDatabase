package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private BasicDataSource dataSource;

    public DataManager(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    //helper method to convert sql to vehicle object
    private Vehicle mapRowToVehicle(ResultSet rs) throws SQLException {
        int vin = rs.getInt("vin");
        int year = rs.getInt("year");
        String make = rs.getString("make");
        String model = rs.getString("model");
        String type = rs.getString("vehicle_type");
        String color = rs.getString("color");
        int odometer = rs.getInt("odometer");
        double price = rs.getDouble("price");

        return new Vehicle(vin, year, make, model, type, color, odometer, price);
    }


    //Phase 1

    public List<Vehicle> getVehiclesByPriceRange(double minPrice, double maxPrice) throws SQLException {
        String sql = """
                SELECT * FROM vehicles
                            WHERE price BETWEEN ? AND ?
      """;

        List<Vehicle> list = new ArrayList<>();

        try(Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setDouble(1,minPrice);
            statement.setDouble(2,maxPrice);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(mapRowToVehicle(rs));
            }
        }
        return list;

    }

    public List<Vehicle> getVehiclesByMakeModel (String make,String model) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE make = ? AND model = ?";
        List<Vehicle> list = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, make);
            statement.setString(2, model);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(mapRowToVehicle(rs));
            }
        }
        return list;
    }

    public List<Vehicle> getVehiclesByYearRange(int min, int max) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?";
        List<Vehicle> list = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, min);
            statement.setInt(2, max);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(mapRowToVehicle(rs));

            }
        }
        return list;
    }
    public List<Vehicle> getVehiclesByColor(String color) throws SQLException{
        String sql = "SELECT * FROM vehicles WHERE color = ?";
        List<Vehicle> list = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, color);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(mapRowToVehicle(rs));
            }
        }
        return list;
    }

    public List<Vehicle> getVehiclesByMileageRange(int min, int max) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE odometer BETWEEN ? AND ?";
        List<Vehicle> list = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, min);
            statement.setInt(2, max);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(mapRowToVehicle(rs));
            }
        }
        return list;
    }
    public List<Vehicle> getVehiclesByType(String type) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE vehicle_type = ?";
        List<Vehicle> list = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, type);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(mapRowToVehicle(rs));
            }
        }
        return list;
    }

}
