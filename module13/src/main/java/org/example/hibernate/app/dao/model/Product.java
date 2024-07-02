package org.example.hibernate.app.dao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "products")
@Entity
public class Product {
    @Id
    private Long id;
    private String name;
    private String description;
}
