package org.spring.demo.module15.app.dao.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
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
//      @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "seq_customer_id", allocationSize = 1)
    private Long id;
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
//    @Pattern(regexp = "[A-Z][a-z]*")
    private String lastName;

    @Email
    private String email;

    @Column(name = "tel_number")
//    @Min(10)
    private String phoneNumber;

    @Column(name = "post_code")
//    @Range(min = 2, max = 6)
    private String postCode;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private CustomerDetails customerDetails;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        order.setCustomer(this);
        orders.add(order);
        String s = "";
    }

    public LocalDate getDateOfBirth() {
        return customerDetails != null ? customerDetails.getDateOfBirth() : null;
    }

    public Integer getLoyaltyPoints() {
        return customerDetails != null ? customerDetails.getLoyaltyPoints() : null;
    }
}
