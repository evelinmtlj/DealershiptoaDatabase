package com.pluralsight.persistance;

import com.pluralsight.models.Vehicle;
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
        String vin = rs.getString("vin");
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
    // Phase 2
    public void addVehicle(Vehicle v) throws SQLException {
        String sql = """
                INSERT INTO vehicles
                (vin, year, make, model, vehicle_type, color, odometer, price, sold)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, v.getVin());
            statement.setInt(2, v.getYear());
            statement.setString(3, v.getMake());
            statement.setString(4, v.getModel());
            statement.setString(5, v.getVehicleType());
            statement.setString(6, v.getColor());
            statement.setInt(7, v.getOdometer());
            statement.setDouble(8, v.getPrice());
            statement.setBoolean(9, false);

            statement.executeUpdate();

        }
    }

    public void removeVehicleByVin(int vin) throws SQLException {
        String sql  = "DELETE FROM vehicles WHERE vin = ?";

        try(Connection connection = dataSource.getConnection();
       PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,vin);
            statement.executeUpdate();
        }
    }


}
