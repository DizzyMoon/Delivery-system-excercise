package com.example.delivery.service;

import com.example.delivery.dto.delivery.DeliveryRequest;
import com.example.delivery.dto.delivery.DeliveryResponse;
import com.example.delivery.exception.DeliveryNotFoundException;
import com.example.delivery.model.Delivery;
import com.example.delivery.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;

    // Get all deliveries
    public List<DeliveryResponse> getAllDeliveries(){
        List<Delivery> deliveries = deliveryRepository.findAll();
        return deliveries.stream()
                .map(DeliveryResponse::new)
                .collect(Collectors.toList());
    }

    // find delivery by id
    public DeliveryResponse getDeliveryById(Long id){
        Optional<Delivery> foundDelivery = deliveryRepository.findById(id);
        if (foundDelivery.isEmpty()) {
            throw new DeliveryNotFoundException("Cannot find delivery with id: " + id);
        } else {
            return new DeliveryResponse(foundDelivery.get());
        }
    }

    public DeliveryResponse createDelivery(DeliveryRequest deliveryRequest){
        Delivery deliveryToSave = new Delivery();
        if (deliveryRepository.existsById(deliveryRequest.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Delivery with id: "
                    + deliveryRequest.getId()
                    + " does already exist.");
        } else {
            deliveryRequest.copyTo(deliveryToSave);
            deliveryRepository.save(deliveryToSave);
            return new DeliveryResponse(deliveryToSave);
        }
    }

    public DeliveryResponse updateDelivery(Long id, DeliveryRequest deliveryRequest){
        Optional<Delivery> optionalDeliveryToEdit = deliveryRepository.findById(id);
        Delivery deliveryToEdit = optionalDeliveryToEdit.get();
        if (optionalDeliveryToEdit.isEmpty()) {
            throw new DeliveryNotFoundException("Delivery with id: "
            + id
            + " could not be found.");
        } else {
            deliveryRequest.copyTo(deliveryToEdit);
            return new DeliveryResponse(deliveryRepository.save(deliveryToEdit));
        }
    }

    public void deleteDelivery(Long id) {
        Optional<Delivery> deliveryToDelete = deliveryRepository.findById(id);
        if (deliveryToDelete.isEmpty()){
            throw new DeliveryNotFoundException("Delivery with id: " +
                    id
            + " could not be found.");
        } else {
            deliveryRepository.delete(deliveryToDelete.get());
        }
    }
}
