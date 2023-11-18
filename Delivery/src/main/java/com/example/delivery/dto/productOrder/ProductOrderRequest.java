package com.example.delivery.dto.productOrder;


import com.example.delivery.model.ProductOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductOrderRequest {
    private String name;
    private double price;
    private double weight;

    //Ignore in methods
    private String productName;
    private Long deliveryId;

    private ProductOrder getProductOrderEntity(ProductOrderRequest p){
        return ProductOrder.builder().name(name)
                .price(price)
                .weight(weight)
                .build();
    }

    public void copyTo(ProductOrder productOrder){
        productOrder.setName(name);
        productOrder.setPrice(price);
        productOrder.setWeight(weight);
    }

    public ProductOrderRequest(ProductOrder p){
        this.name = p.getName();
        this.price = p.getPrice();
        this.weight = p.getWeight();
    }
}
