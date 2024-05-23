package org.example.crud.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.crud.annotation.Entity;
import org.example.crud.annotation.Id;
import org.example.crud.annotation.Table;

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
