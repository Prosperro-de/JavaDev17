package org.spring.demo.module14.app.dao.model.projection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerProjection {
    private String firstName;
    private String lastName;
}
