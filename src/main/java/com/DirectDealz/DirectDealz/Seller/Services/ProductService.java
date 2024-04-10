package com.DirectDealz.DirectDealz.Seller.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.DirectDealz.DirectDealz.Authentication.Enum.UserRole;
import com.DirectDealz.DirectDealz.Authentication.Models.UserModel;
import com.DirectDealz.DirectDealz.Authentication.Services.AuthService;
import com.DirectDealz.DirectDealz.Authentication.Services.UserService;
import com.DirectDealz.DirectDealz.Seller.Enum.ProductStatus;
import com.DirectDealz.DirectDealz.Seller.Models.Product;
import com.DirectDealz.DirectDealz.Seller.Repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;
     @Autowired
    private UserModel userModel;

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public boolean isSeller(UserModel userModel) {
        // Assuming user has a role attribute indicating their role
        UserRole role = userModel.getUserRole();

        // Check if the user has a role indicating they are a seller
        return "SELLER".equals(role);
    }

    public ResponseEntity<Object> getProducts(String token) {
        try {
            // Verify the token and user role
            if (!authService.isTokenValid(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }

            if (!isSeller(userModel)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not a seller");
            }

            // Get all products
            List<Product> products = repository.findAll();

            return ResponseEntity.ok().body(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal Server Error: " + e.getMessage());
        }
    }

    public List<Product> saveProducts(List<Product> products) {
        return repository.saveAll(products);
    }

    public ResponseEntity<Object> getProductById(UUID id, String token) {
        try {
            // Verify the token and user role
            if (!authService.isTokenValid(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }

            if (!isSeller(userModel)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not a seller");
            }

            // Get product by ID
            Product product = repository.findById(id).orElse(null);

            if (product == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
            }

            return ResponseEntity.ok().body(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal Server Error: " + e.getMessage());
        }
    }

    public ResponseEntity<Object> deleteProduct(UUID id, String token) {
        try {
            // Verify the token and user role
            if (!authService.isTokenValid(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }

            if (!isSeller(userModel)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not a seller");
            }

            // Check if the product exists
            if (!repository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
            }

            // Delete the product
            repository.deleteById(id);

            return ResponseEntity.ok().body("Product deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal Server Error: " + e.getMessage());
        }
    }

    public void updateProductStatus(ProductStatus status) {
        // Your service logic here
        System.out.println("Updating product status to: " + status);
    }

    public ResponseEntity<Object> updateProduct(UUID id, Product updatedProduct, String token) {
        try {
            // Verify the token and user role
            if (!authService.isTokenValid(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }

            if (!isSeller(userModel)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not a seller");
            }

            // Get the existing product
            Product existingProduct = repository.findById(id).orElse(null);

            if (existingProduct == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
            }

            // Update product details
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());

            // Save the updated product
            repository.save(existingProduct);

            return ResponseEntity.ok().body("Product updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal Server Error: " + e.getMessage());
        }
    }

}
