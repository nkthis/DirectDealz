package com.DirectDealz.DirectDealz.Seller.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.DirectDealz.DirectDealz.Seller.Enum.ProductStatus;
import com.DirectDealz.DirectDealz.Seller.Models.Product;
import com.DirectDealz.DirectDealz.Seller.Services.ProductService;

@RestController
public class ProductController {
     @Autowired
    private ProductService service;

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){
        return service.saveProduct(product);
    }
    
    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products){
        return service.saveProducts(products);
    }

    @GetMapping("/products")
    public ResponseEntity<Object> findAllProducts(@RequestHeader String token){
        return service.getProducts(token);
    }

    @GetMapping("/productById/{id}")
    public ResponseEntity<Object> findProductById(@PathVariable UUID id, @RequestHeader String token){
        return service.getProductById(id, token);
    }


    @PutMapping("/update")
    public ResponseEntity<Object> updateProduct(@PathVariable UUID id, @RequestBody Product product,@RequestHeader String token){
        return service.updateProduct(id, product, token);
    }

    @PutMapping("/productStatus/{id}/status/{status}")
    public String updateProductStatus(@PathVariable UUID id, @PathVariable ProductStatus status) {
        service.updateProductStatus(status);
        return "Product status updated successfully";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable UUID id,@RequestHeader String token ){
        return service.deleteProduct(id, token);
    }

 


}
