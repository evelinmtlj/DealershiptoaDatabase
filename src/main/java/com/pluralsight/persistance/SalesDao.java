package com.pluralsight.persistance;

import com.pluralsight.models.SalesContract;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {

    private BasicDataSource dataSource;

    public SalesDao(BasicDataSource dataSource){
        this.dataSource = dataSource;
    }

    public void saveSalesContract(SalesContract c ) throws SQLException {
        String sql = """
                INSERT INTO sales_contracts
                (vin, customer_name, sale_price, sale_date)
                VALUES (?, ?, ?, ?)
                """;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1,c.getVehicleSold().getVin());
            statement.setString(2,c.getCustomerName());
            statement.setDouble(3, c.getVehicleSold().getPrice());
            statement.setDate(4, Date.valueOf(c.getDateOfContract()));

            statement.executeUpdate();
        }

    }
}
