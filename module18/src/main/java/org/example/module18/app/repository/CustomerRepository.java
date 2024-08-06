package org.example.module18.app.repository;


import org.example.module18.app.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    @Query(nativeQuery = true, value = "SELECT * FROM customers c WHERE c.email = :email")
    Optional<Customer> getCustomersByEmail(@Param("email")String email);

    List<Customer> findAllOrderById(Long id);

    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.orders LEFT JOIN c.customerDetails")
    List<Customer> findAllCustomers();

    List<Customer> getCustomersByEmailOrFirstName(String email, String firstName);
}
