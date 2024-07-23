package org.example.module16.app.repository;

import org.example.module16.app.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

//    Optional<Customer> getCustomersByEmail(String email);

//    @Query("FROM Customer WHERE email = :email")
//    Optional<Customer> getCustomersByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT * FROM customers c WHERE c.email = :email")
    Optional<Customer> getCustomersByEmail(@Param("email")String email);


    List<Customer> getCustomersByEmailOrFirstName(String email, String firstName);
}
