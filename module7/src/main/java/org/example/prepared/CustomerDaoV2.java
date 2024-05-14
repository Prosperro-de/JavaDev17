package org.example.prepared;

import org.example.Customer;
import org.example.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDaoV2 {
    public static final String CREATE_CUSTOMER_SQL_TEMPLATE = """
            INSERT INTO customers(first_name, last_name, email) 
                    VALUES(?, ?, ?); 
            """;
    public static final String SELECT_CUSTOMER_BY_ID = """
            SELECT * FROM customers WHERE id = ?
            """;

    public void save(Customer customer) throws SQLException {
        Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CUSTOMER_SQL_TEMPLATE,
                PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, customer.getFirstName());
        preparedStatement.setString(2, customer.getLastName());
        preparedStatement.setString(3, customer.getEmail());
//        preparedStatement.setMaxFieldSize(255);
        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        while (generatedKeys.next()) {
            customer.setId(generatedKeys.getObject(1, Long.class));
        }
    }

    public Customer findById(Long id) throws SQLException {
        Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        Customer customer = new Customer();
        while (resultSet.next()) {
            customer.setId(resultSet.getObject(1, Long.class));
            customer.setFirstName(resultSet.getString(2));
            customer.setLastName(resultSet.getString(3));
            customer.setEmail(resultSet.getString(4));
        }
        return customer;
    }

    public void saveCustomers(List<Customer> customers) throws SQLException {
        Connection connection = DataSource.getConnection();
        connection.setAutoCommit(false);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CUSTOMER_SQL_TEMPLATE,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            for (Customer customer : customers) {
                preparedStatement.setString(1, customer.getFirstName());
                preparedStatement.setString(2, customer.getLastName());
                preparedStatement.setString(3, customer.getEmail());
                preparedStatement.addBatch();
//                preparedStatement.executeUpdate();
            }
            preparedStatement.executeBatch();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            int counter = 0;
            while (generatedKeys.next()) {
                customers.get(counter).setId(generatedKeys.getObject(1, Long.class));
                counter++;
            }
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }

    }
}
