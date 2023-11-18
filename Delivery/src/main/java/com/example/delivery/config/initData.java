package com.example.delivery.config;

import com.example.delivery.dto.delivery.DeliveryRequest;
import com.example.delivery.model.Delivery;
import com.example.delivery.model.Product;
import com.example.delivery.repository.DeliveryRepository;
import com.example.delivery.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class initData implements CommandLineRunner {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        //Create products
        Product product1 = new Product();
        product1.setWeight(0.23);
        product1.setPrice(799.95);
        product1.setName("Very cool shoes");

        productRepository.save(product1);


        // Create deliveries
        Delivery delivery1 = new Delivery();
        delivery1.setDeliveryDate(LocalDate.now());
        delivery1.setFromWareHouse("Copenhagen");
        delivery1.setDestination("Aarhus");

        deliveryRepository.save(delivery1);
    }
}
