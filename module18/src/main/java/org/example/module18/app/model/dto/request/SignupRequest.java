package org.example.module18.app.model.dto.request;

import lombok.Data;

@Data
public class SignupRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String postCode;
    private String dateOfBirth;
}
