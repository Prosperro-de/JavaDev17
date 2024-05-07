package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    public static final String GET_ALL_CUSTOMERS_SQL =
            "SELECT * FROM customers";

    public static final String CREATE_CUSTOMER_SQL = """
            INSERT INTO customers(first_name, last_name, email) 
                    VALUES('Mykola', 'Klushyn', 'mail@gmail.com');
            """;

    public static final String CREATE_CUSTOMER_SQL_TEMPLATE = """
            INSERT INTO customers(first_name, last_name, email) 
                    VALUES('%s', '%s', '%s');
            """;
    public static final String UPDATE_CUSTOMER_SQL_TEMPLATE = """
                        UPDATE customers SET first_name = '%s', last_name = '%s', email = '%s'
            WHERE id = '%s';
                        """;


//    public void getAllCustomers() throws SQLException {
//        Connection connection = DataSource.getConnection();
//        try(Statement statement = connection.createStatement()) {
//            ResultSet resultSet = statement.executeQuery(GET_ALL_CUSTOMERS_SQL);
//            while (resultSet.next()) {
////                System.out.println(resultSet.getLong("id")); //  default 0
//                System.out.println(resultSet.getObject("id", Long.class)); // default null
//                System.out.println(resultSet.getString("first_name"));
//                System.out.println(resultSet.getString("last_name"));
//                System.out.println(resultSet.getString("email"));
//                System.out.println("___________________");
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            throw ex;
//        }
//    }

    public List<Customer> getAll() throws SQLException {
        List<Customer> result = new ArrayList<>();
        Connection connection = DataSource.getConnection();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_CUSTOMERS_SQL);
            while (resultSet.next()) {
                result.add(new Customer(
                        resultSet.getObject("id", Long.class),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return result;
    }

    public void save() throws SQLException {
        Connection connection = DataSource.getConnection();
        try(Statement statement = connection.createStatement()) {
            statement.execute(CREATE_CUSTOMER_SQL);
        }
    }

    public void save(Customer customer) throws SQLException {
        Connection connection = DataSource.getConnection();
        String createSql = CREATE_CUSTOMER_SQL_TEMPLATE.formatted(
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getEmail());
        try(Statement statement = connection.createStatement()) {
            statement.execute(createSql, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            while (generatedKeys.next()) {
                customer.setId(generatedKeys.getObject("id", Long.class));
            }
        }
    }

    public void update(Customer customer) throws SQLException {
        Connection connection = DataSource.getConnection();
        String createSql = UPDATE_CUSTOMER_SQL_TEMPLATE.formatted(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getId());
        try(Statement statement = connection.createStatement()) {
            int columnChangedCount = statement.executeUpdate(createSql);
            System.out.println(columnChangedCount);
        }
    }
}
