package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;
    Connection connection;
    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addVehicle(Vehicle vehicle) {

    }

    public void removeVehicle(String VIN) {
        String sql = """
                DELETE FROM vehicle (vin)
                VALUES(?)
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.clearBatch();
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        String sql = """
                SELECT * FROM vehicles AS v
                JOIN inventory AS i ON v.vin = i.vin
                WHERE price;
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new ArrayList<>();
    }

    public List<Vehicle> searchByMakeModel(String make, String model) {
        String sql = """
                SELECT * FROM vehicles AS v
                JOIN inventory AS i ON v.vin = i.vin
                WHERE model;
                """;
        return new ArrayList<>();
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        String sql = """
               SELECT * FROM vehicles AS v
                JOIN inventory AS i ON v.vin = i.vin
                WHERE year;
                """;
        return new ArrayList<>();
    }

    public List<Vehicle> searchByColor(String color) {
          String sql = """
              SELECT * FROM vehicles AS v
                JOIN inventory AS i ON v.vin = i.vin
                WHERE color;
                """;
        return new ArrayList<>();
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        String sql = """
               SELECT * FROM vehicles AS v
                JOIN inventory AS i ON v.vin = i.vin
                WHERE mileage;
                """;
        return new ArrayList<>();
    }

    public List<Vehicle> searchByType(String type) {
        String sql = """
              SELECT * FROM vehicles AS v
                JOIN inventory AS i ON v.vin = i.vin
                WHERE type;
                """;
        return new ArrayList<>();
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}
