package com.example.delivery.dto.product;

import com.example.delivery.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequest {
    private String name;
    private double price;
    private double weight;

    //Ignore in methods for this class
    private List<String> productOrderNameList;

    public Product getProductEntity(ProductRequest p){
        return Product.builder().name(p.name)
                .price(p.price)
                .weight(p.weight)
                .build();
    }

    public void copyTo(Product product){
        product.setName(name);
        product.setPrice(price);
        product.setWeight(weight);
    }

    public ProductRequest(Product p){
        this.name = p.getName();
        this.price = p.getPrice();
        this.weight = p.getWeight();
    }
}
