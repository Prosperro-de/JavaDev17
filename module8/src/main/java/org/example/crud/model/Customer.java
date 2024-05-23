package org.example.crud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.crud.annotation.Column;
import org.example.crud.annotation.Entity;
import org.example.crud.annotation.Id;
import org.example.crud.annotation.Table;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Setter
@Builder
@Entity
@Table(value = "customers")
public class Customer {
    @Id
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @Column(name = "tel_number")
    private String phoneNumber;
    @Column(name = "post_code")
    private String postCode;

}
