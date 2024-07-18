package org.spring.demo.module15.app.dao.model.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String postCode;
    private LocalDate dateOfBirth;
    private Integer loyaltyPoints;
}
