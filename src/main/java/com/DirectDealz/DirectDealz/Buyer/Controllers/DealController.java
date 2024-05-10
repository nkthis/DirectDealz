package com.DirectDealz.DirectDealz.Buyer.Controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DirectDealz.DirectDealz.Buyer.Service.DealService;

@RestController
@CrossOrigin(origins = "http://localhost/5173")
@RequestMapping("/api/deals")
public class DealController {
    
    @Autowired
    private DealService dealService;

    @PostMapping("/lock")
    public ResponseEntity<Object> lockDeal(@RequestHeader UUID productId, @RequestHeader String token) {
        return dealService.lockProductForDeal(productId, token);
    }


}
