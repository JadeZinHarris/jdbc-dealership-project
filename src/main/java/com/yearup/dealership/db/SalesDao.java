package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.*;

public class SalesDao {
    private DataSource dataSource;
    Connection connection;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addSalesContract(SalesContract salesContract) {
        String sql = """
                INSERT INTO salesContract (contractId, vin, saleDate, price )
                VALUES(?,?,?,?)
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, salesContract.getContractId());
            statement.setString(2,salesContract.getVin());
            statement.setDate(3, Date.valueOf(salesContract.getSaleDate()));
            statement.setDouble(4,salesContract.getPrice());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
