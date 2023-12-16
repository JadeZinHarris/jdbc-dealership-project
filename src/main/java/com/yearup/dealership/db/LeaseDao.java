package com.yearup.dealership.db;

import com.yearup.dealership.models.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LeaseDao {
    private DataSource dataSource;

    Connection connection;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addLeaseContract(LeaseContract leaseContract) {
        String sql = """
               INSERT INTO salesContract (contractId, vin, leaseStart, leaseEnd, monthlyPayment )
                VALUES(?,?,?,?,?)
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, leaseContract.getContractId());
            statement.setString(2,leaseContract.getVin());
            statement.setLocalDate(3,leaseContract.getLeaseStart());
            statement.setDate(4,leaseContract.getLeaseEnd());
            statement.setDouble(5,leaseContract.getMonthlyPayment());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
