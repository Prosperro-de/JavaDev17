package org.example.servlet.app.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.servlet.app.dao.annotation.Column;
import org.example.servlet.app.dao.annotation.Entity;
import org.example.servlet.app.dao.annotation.Id;
import org.example.servlet.app.dao.annotation.Table;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Setter
@Getter
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
