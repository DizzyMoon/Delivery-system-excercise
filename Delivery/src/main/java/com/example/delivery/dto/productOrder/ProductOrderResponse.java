package com.example.delivery.dto.productOrder;

import com.example.delivery.model.Product;
import com.example.delivery.model.ProductOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductOrderResponse {
    private String name;
    private double price;
    private double weight;
    private String productName;
    private Long deliveryId;

    public ProductOrderResponse(ProductOrder p){
        this.name = p.getName();
        this.price = p.getPrice();
        this.weight = p.getWeight();
        this.productName = p.getProduct().getName();
        this.deliveryId = p.getDelivery().getId();
    }
}
