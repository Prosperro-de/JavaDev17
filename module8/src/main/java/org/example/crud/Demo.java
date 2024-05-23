package org.example.crud;

import org.example.crud.dao.GenericDao;
import org.example.crud.model.Customer;
import org.example.crud.model.Product;
import org.example.crud.util.SqlGenerator;

import java.sql.SQLException;

public class Demo {
    public static void main(String[] args) throws SQLException {
        GenericDao genericDao = new GenericDao();
//        Customer customerById = genericDao.findById(1L, Customer.class);
//        System.out.println("customerById = " + customerById);
//
//        Product productById = genericDao.findById(1L, Product.class);
//        System.out.println("productById = " + productById);
//
//        Customer newCustomer = Customer.builder()
//                .firstName("Mykola")
//                .lastName("Klushyn")
//                .email("mail@gmail.com")
//                .phoneNumber("555-555")
//                .postCode("49000")
//                .build();
//        genericDao.save(newCustomer);
//
//        Product newProduct = Product.builder()
//                .name("Apple")
//                .description("Phone")
//                .build();
//
//        genericDao.save(newProduct);
//
//        System.out.println(newCustomer);
//        System.out.println(newProduct);

//        Customer customerByIdForUpdate = genericDao.findById(1L, Customer.class);
//        customerByIdForUpdate.setFirstName("Taras");
//        customerByIdForUpdate.setLastName("Shevchenko");
//        System.out.println(customerByIdForUpdate);
//
//        genericDao.update(customerByIdForUpdate);
//        Customer customerByIdForUpdate2 = genericDao.findById(1L, Customer.class);
//        System.out.println(customerByIdForUpdate2);


//        Customer customerByIdForDelete = genericDao.findById(24L, Customer.class);
//        System.out.println("customerByIdForDelete = " + customerByIdForDelete);
//        genericDao.delete(customerByIdForDelete);
//
//        Customer customerByIdForDelete2 = genericDao.findById(24L, Customer.class);
//        System.out.println("customerByIdForDelete2 = " + customerByIdForDelete2);

        Product byId = genericDao.findById(6L, Product.class);
        genericDao.delete(byId);
    }
}
