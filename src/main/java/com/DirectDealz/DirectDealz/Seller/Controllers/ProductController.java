package com.DirectDealz.DirectDealz.Seller.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public List<Product> findAllProducts(){
        return service.getProducts();
    }

    @GetMapping("/productById/{id}")
    public Product findProductById(@PathVariable UUID id){
        return service.getProductById(id);
    }

    @GetMapping("/product/{name}")
    public Product findProductbyName(@PathVariable String name){
        return service.getProductByName(name);
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product){
        return service.updateProduct(product);
    }

    @PutMapping("/productStatus/{id}/status/{status}")
    public String updateProductStatus(@PathVariable UUID id, @PathVariable ProductStatus status) {
        service.updateProductStatus(status);
        return "Product status updated successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable UUID id){
        return service.deleteProduct(id);
    }

 


}
