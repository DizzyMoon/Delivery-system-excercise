package com.example.delivery.dto.delivery;

import com.example.delivery.model.Delivery;
import com.example.delivery.model.ProductOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryResponse {
    private Long id;
    private LocalDate deliveryDate;
    private String fromWareHouse;
    private String destination;
    private List<String> productOrderNameList;

    public DeliveryResponse(Delivery d){
        this.id = d.getId();
        this.deliveryDate = d.getDeliveryDate();
        this.fromWareHouse = d.getFromWareHouse();
        this.destination = d.getDestination();
        if (this.productOrderNameList != null) {
            this.productOrderNameList = d.getProductOrderList().stream()
                    .map(ProductOrder::getName)
                .collect(Collectors.toList());
        }
    }
}
