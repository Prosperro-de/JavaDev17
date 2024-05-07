package org.example;

import java.sql.SQLException;
import java.util.List;

public class JdbcDemo {
    public static final String CREATE_PAYMENT_TABLE_SQL = """
                    CREATE TABLE payments(
            id BIGSERIAL PRIMARY KEY,
            amount DECIMAL,
            customer_id BIGINT,
            FOREIGN KEY (customer_id) REFERENCES customers(id));""";

    public static void main(String[] args) throws SQLException {
//        Connection connection = DataSource.getConnection();
//        try(Statement statement = connection.createStatement()) {
//            statement.execute(CREATE_PAYMENT_TABLE_SQL);
//        }
        CustomerDao customerDao = new CustomerDao();
//        List<Customer> allCustomers = customerDao.getAll();
//        allCustomers.forEach(System.out::println);
//        customerDao.save();
        Customer customer = new Customer();
        customer.setId(15L);
        customer.setFirstName("Taras");
        customer.setLastName("Shevchenko");
        customer.setEmail("shevchenko@gmail.com");
//        customerDao.save(customer);

        customerDao.update(customer);
        String s = "";
    }

}
