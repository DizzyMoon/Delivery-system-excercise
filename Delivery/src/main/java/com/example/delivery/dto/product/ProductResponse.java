package com.example.delivery.dto.product;

import com.example.delivery.model.Product;
import com.example.delivery.model.ProductOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponse {
    private String name;
    private double price;
    private double weight;
    private List<String> productOrderNameList;

    public ProductResponse(Product p){
        this.name = p.getName();
        this.price = p.getPrice();
        this.weight = p.getWeight();
        if (productOrderNameList != null){
            this.productOrderNameList = p.getProductOrderList().stream()
                    .map(ProductOrder::getName)
                    .collect(Collectors.toList());
        }
    }
}
