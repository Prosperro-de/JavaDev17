package org.example.annotationdemo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    @Trim
    private String name;
    private String lastName;
    @Trim
    private String email;
}
