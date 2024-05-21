package org.example.crud;

import org.example.crud.dao.GenericDao;
import org.example.crud.model.Customer;
import org.example.crud.model.Product;
import org.example.crud.util.SqlGenerator;

import java.sql.SQLException;

public class Demo {
    public static void main(String[] args) throws SQLException {
        GenericDao genericDao = new GenericDao();
        Customer customerById = genericDao.findById(1L, Customer.class);
        System.out.println("customerById = " + customerById);

        Product productById = genericDao.findById(1L, Product.class);
        System.out.println("productById = " + productById);
    }
}
