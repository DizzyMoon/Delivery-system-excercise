package com.example.delivery.api;

import com.example.delivery.dto.delivery.DeliveryRequest;
import com.example.delivery.dto.delivery.DeliveryResponse;
import com.example.delivery.repository.DeliveryRepository;
import com.example.delivery.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeliveryRestController {
    @Autowired
    DeliveryService deliveryService;

    @GetMapping("/deliveries")
    public ResponseEntity<List<DeliveryResponse>> getAllDeliveries() {
        List<DeliveryResponse> deliveryResponseList = deliveryService.getAllDeliveries();
        return new ResponseEntity<>(deliveryResponseList, HttpStatus.OK);
    }

    @GetMapping("/delivery/{id}")
    public ResponseEntity<DeliveryResponse> getDeliveryById(@PathVariable("id") Long id){
        DeliveryResponse deliveryResponse = deliveryService.getDeliveryById(id);
        return new ResponseEntity<>(deliveryResponse, HttpStatus.OK);
    }

    @PostMapping("/delivery")
    public ResponseEntity<String> createDelivery(@RequestBody DeliveryRequest deliveryRequest) {
        DeliveryResponse createDelivery = deliveryService.createDelivery(deliveryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Delivery created successfully");
    }

    @PutMapping("/delivery/{id}")
    public ResponseEntity<String> updateDelivery(@PathVariable("id") Long id, @RequestBody DeliveryRequest deliveryRequest){
        DeliveryResponse updateDelivery = deliveryService.updateDelivery(id, deliveryRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Delivery with id \"" + id + "\" updated successfully.");
    }

    @DeleteMapping("/delivery/{id}")
    public ResponseEntity<String> deleteDelivery(@PathVariable("id") Long id){
        deliveryService.deleteDelivery(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delivery deleted successfully");
    }
}
