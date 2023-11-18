package com.example.delivery.dto.delivery;

import com.example.delivery.model.Delivery;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequest {
    private Long id;
    private LocalDate deliveryDate;
    private String fromWareHouse;
    private String destination;
    private List<String> productOrderNameList;

    public Delivery getDeliveryEntity(DeliveryRequest d){
        return Delivery.builder().id(d.id)
                .deliveryDate(d.deliveryDate)
                .fromWareHouse(d.fromWareHouse)
                .destination(d.destination)
                .build();
    }

    public void copyTo(Delivery delivery){
        delivery.setDeliveryDate(deliveryDate);
        delivery.setFromWareHouse(fromWareHouse);
        delivery.setDestination(destination);
    }

    public DeliveryRequest(Delivery d){
        this.deliveryDate = d.getDeliveryDate();
        this.fromWareHouse = d.getFromWareHouse();
        this.destination = d.getDestination();
    }

}
