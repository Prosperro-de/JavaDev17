package org.example.servlet.app.dao.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.servlet.app.dao.annotation.Entity;
import org.example.servlet.app.dao.annotation.Id;
import org.example.servlet.app.dao.annotation.Table;

@Data
@NoArgsConstructor
@Table(value = "products")
@Entity
public class Product {
    @Id
    private Long id;
    private String name;
    private String description;
}
