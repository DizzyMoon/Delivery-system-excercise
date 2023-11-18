package com.example.delivery.service;

import com.example.delivery.dto.product.ProductRequest;
import com.example.delivery.dto.product.ProductResponse;
import com.example.delivery.exception.ProductNotFoundException;
import com.example.delivery.model.Product;
import com.example.delivery.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    public ProductResponse getProductByName(String name) {
        Optional<Product> foundProduct = productRepository.findById(name);
        if (foundProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with name: " + name
                    + " could not be found.");
        } else {
            return new ProductResponse(foundProduct.get());
        }
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product productToCreate = new Product();
        if (productRepository.existsById(productRequest.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with name "
                    + productRequest.getName()
                    + " already exists.");
        } else {
            productRequest.copyTo(productToCreate);
            productRepository.save(productToCreate);
            return new ProductResponse(productToCreate);
        }
    }

    public ProductResponse updateProduct(String name, ProductRequest productRequest) {
        Optional<Product> optionalProductToEdit = productRepository.findById(name);
        Product productToEdit = optionalProductToEdit.get();

        if (productRequest.getName() == null){
            throw new IllegalArgumentException("Product name cannot be empty");
        }

        if (optionalProductToEdit.isEmpty()) {
            throw new ProductNotFoundException("Product with name: "
                    + name
                    + " could not be found.");
        } else {
            productRequest.copyTo(productToEdit);
            return new ProductResponse(productRepository.save(productToEdit));
        }
    }

    public void deleteProduct(String name) {
        Optional<Product> optionalProductToDelete = productRepository.findById(name);
        if (optionalProductToDelete.isEmpty()){
            throw new ProductNotFoundException("Product with name: "
            + name
            + " could not be found.");
        } else {
            productRepository.deleteById(name);
        }
    }
}
