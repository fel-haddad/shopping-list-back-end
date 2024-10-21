package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.model.ShoppingList;
import com.example.demo.model.User;
import com.example.demo.model.UserDTO;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShoppingListRepository shoppingListRepository;

    // Get all products
    public List<Product> getAllProducts(Long listId) {
                  return productRepository.findByShoppingListId(listId);
    }

    // Add a new product
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Get a product by ID
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Update a product
    public Product updateProduct(Long id, Product newProduct) {
        return productRepository.findById(id).map(product -> {
            product.setName(newProduct.getName());
            product.setQuantity(newProduct.getQuantity());
            return productRepository.save(product);
        }).orElse(null);
    }

    // Delete a product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
