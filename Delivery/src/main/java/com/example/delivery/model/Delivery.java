package com.example.delivery.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate deliveryDate;
    private  String fromWareHouse;
    private String destination;

    @OneToMany(mappedBy = "delivery")
    private List<ProductOrder> productOrderList;
}
