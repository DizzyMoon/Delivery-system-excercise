package com.example.delivery.api;

import com.example.delivery.dto.product.ProductRequest;
import com.example.delivery.dto.product.ProductResponse;
import com.example.delivery.model.Product;
import com.example.delivery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<ProductResponse> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product/{name}")
    public ResponseEntity<ProductResponse> getProductByName(@PathVariable("name") String name){
        ProductResponse foundProduct = productService.getProductByName(name);
        return new ResponseEntity<>(foundProduct, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest productRequest){
        ProductResponse createdProduct = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully.");
    }

    @PutMapping("/product/{name}")
    public ResponseEntity<String> updateProduct(@PathVariable("name") String name, @RequestBody ProductRequest productRequest){
        ProductResponse updatedProduct = productService.updateProduct(name, productRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Product with name: \""
        + name
        + "\" created successfully.");
    }

    @DeleteMapping("/product/{name}")
    public ResponseEntity<String> deleteProduct(@PathVariable("name") String name){
        productService.deleteProduct(name);
        return ResponseEntity.status(HttpStatus.OK).body("Product with name: \""
        + name
        + "\" deleted successfully.");
    }
}
