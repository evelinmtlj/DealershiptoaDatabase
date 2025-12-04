package com.pluralsight.persistance;

import com.pluralsight.models.LeaseContract;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseDao {

    private BasicDataSource dataSource;

    public LeaseDao(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveLeaseContract(LeaseContract c) throws SQLException {

        String sql = """
                INSERT INTO lease_contracts
                            (vin, customer_name, customer_email, expected_ending_value,
                             lease_fee, total_price, monthly_payment, lease_date)
                            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                            """;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1,c.getVehicleSold().getVin());
            statement.setString(2,c.getCustomerName());
            statement.setString(3,c.getCustomerEmail());
            statement.setDouble(4,c.getExpectedEndingValue());
            statement.setDouble(5, c.getLeaseFee());
            statement.setDouble(6,c.getTotalPrice());
            statement.setDouble(7,c.getMonthlyPayment());
            statement.setDate(8, Date.valueOf(c.getDateOfContract()));

            statement.executeUpdate();
        }
    }

}
