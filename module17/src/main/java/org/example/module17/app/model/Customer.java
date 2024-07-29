package org.example.module17.app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "seq_customer_id", allocationSize = 1)
    private Long id;
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    private String email;

    @Column(name = "customer_password")
    private String password;

    @Column(name = "tel_number")
    private String phoneNumber;

    @Column(name = "post_code")
    private String postCode;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private CustomerDetails customerDetails;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        order.setCustomer(this);
        orders.add(order);
        String s = "";
    }
}
