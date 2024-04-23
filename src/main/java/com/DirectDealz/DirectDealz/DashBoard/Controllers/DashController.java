package com.DirectDealz.DirectDealz.DashBoard.Controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DirectDealz.DirectDealz.DashBoard.Services.ProductListing;
import com.DirectDealz.DirectDealz.Seller.Models.Product;


@RestController
@RequestMapping("/products")
public class DashController {
    private ProductListing productListing;
    
    
    @Autowired
    public  DashController(ProductListing productListing) {
        this.productListing = productListing;
    }
    @GetMapping("/by-city")
    public List<Product> getProductsByCity(@RequestParam("productcity") String productcity) {
        return ProductListing.getProductsByCity(productcity);
    }
}


