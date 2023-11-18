package com.example.delivery.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductOrder {

    @Id
    private String name;
    private double price;
    private double weight;

    @ManyToOne()
    @JoinColumn(name = "product_name", referencedColumnName = "name")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "delivery_id", referencedColumnName = "id")
    private Delivery delivery;
}
