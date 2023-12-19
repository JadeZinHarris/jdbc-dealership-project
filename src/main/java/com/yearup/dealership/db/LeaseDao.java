package com.yearup.dealership.db;

import com.yearup.dealership.models.LeaseContract;

import javax.sql.DataSource;
import java.sql.*;

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
                VALUES(?,?,?,?,?);
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, leaseContract.getContractId());
            statement.setString(2,leaseContract.getVin());
            statement.setDate(3, Date.valueOf(leaseContract.getLeaseStart()));
            statement.setDate(4,Date.valueOf(leaseContract.getLeaseEnd()));
            statement.setDouble(5,leaseContract.getMonthlyPayment());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


//https://stackoverflow.com/questions/33184096/date-new-date-date-valueof-vs-date-now