package com.DirectDealz.DirectDealz.Seller.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DirectDealz.DirectDealz.Seller.Models.Product;



public interface ProductRepository extends JpaRepository<Product, UUID>{
    Product findByName(String name);
}
