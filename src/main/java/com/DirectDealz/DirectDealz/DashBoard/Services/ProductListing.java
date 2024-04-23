package com.DirectDealz.DirectDealz.DashBoard.Services;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DirectDealz.DirectDealz.Seller.Models.Product;
import com.DirectDealz.DirectDealz.DashBoard.Repository.BuyerRepository;


@Service
public class ProductListing {
    private static BuyerRepository buyerRepository;

    @Autowired  // Ensure this annotation is present
    public ProductListing(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }
    public static List<Product> getProductsByCity(String city) {
        return buyerRepository.findByProductcity(city);
    }

}
