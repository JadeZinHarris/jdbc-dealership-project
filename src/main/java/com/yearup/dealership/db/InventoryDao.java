package com.yearup.dealership.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryDao {
    Connection connection;
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void addVehicleToInventory(String vin, int dealershipId) {
        String sql = """
                INSERT INTO inventory (vin , dealershipId)
                VALUES(?,?)
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)){
          statement.setString(1, vin);
          statement.setInt(2, dealershipId);
          statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeVehicleFromInventory(String vin) {
        String sql = """
                DELETE FROM inventory (vin)
                VALUES(?)
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,vin);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
