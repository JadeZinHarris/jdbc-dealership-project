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
        String sql = """
                INSERT INTO  FROM vehicle
                VALUES(?,?,?,?,?,?,?,?,?);
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, vehicle.getVin());
            statement.setString(2,vehicle.getMake());
            statement.setString(3, vehicle.getModel());
            statement.setInt(4,vehicle.getYear());
            statement.setBoolean(5, vehicle.isSold());
            statement.setString(6,vehicle.getColor());
            statement.setString(7, vehicle.getVehicleType());
            statement.setInt(8,vehicle.getOdometer());
            statement.setDouble(9,vehicle.getPrice());

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeVehicle(String VIN) {
        String sql = """
                DELETE FROM vehicle (vin)
                VALUES(?);
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,VIN);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = """
                SELECT * FROM vehicles
                WHERE price
                BETWEEN (?,?);
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new ArrayList<>();
    }

    public List<Vehicle> searchByMakeModel(String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = """
                SELECT * FROM vehicles v
                WHERE make AND model
                LIKE(?,?);
                """;
        return new ArrayList<>();
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = """
               SELECT * FROM vehicles
                WHERE year
                BETWEEN (?,?);
                """;
        return new ArrayList<>();
    }

    public List<Vehicle> searchByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();
          String sql = """
              SELECT * FROM vehicles
                WHERE color
                LIKE (?);
                """;
        return new ArrayList<>();
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = """
              SELECT * FROM vehicles
                WHERE mileage
                BETWEEN (?,?);
                """;
        return new ArrayList<>();
    }

    public List<Vehicle> searchByType(String type) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = """
              SELECT * FROM vehicles
                WHERE type
                LIKE (?);
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
