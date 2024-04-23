package com.DirectDealz.DirectDealz.DashBoard.Repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.DirectDealz.DirectDealz.Seller.Models.Product;

@Repository
public interface BuyerRepository extends JpaRepository<Product,UUID> {
    List<Product> findByProductcity(String productcity);
}