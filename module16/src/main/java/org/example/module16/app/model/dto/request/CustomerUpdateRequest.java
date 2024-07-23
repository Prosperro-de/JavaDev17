package org.example.module16.app.model.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerUpdateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String postCode;
}
