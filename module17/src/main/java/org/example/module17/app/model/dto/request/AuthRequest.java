package org.example.module17.app.model.dto.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String postCode;
    private String dateOfBirth;
}
