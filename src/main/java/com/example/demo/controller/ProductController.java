package com.example.demo.controller;

import com.example.demo.model.ShoppingList;
import com.example.demo.model.User;
import com.example.demo.service.ProductService;
import com.example.demo.model.Product;
import com.example.demo.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ShoppingListService shoppingListService;

    // Get all products
    @GetMapping("/{listId}")
    public List<Product> getAllProducts(@PathVariable Long listId) {
        return productService.getAllProducts(listId);
    }

    // Get a product by ID
    @GetMapping("/{listId}/{id}")
    public Product getProductById(@PathVariable Long id, @PathVariable Long listId) {
        return productService.getProductById(id);
    }

    // Add a new product
    @PostMapping("/{listId}")
    public ResponseEntity<Product> addProduct(@RequestBody Product product, @PathVariable Long listId) {
        Optional<ShoppingList> shoppingListOptional = shoppingListService.getShoppingListById(listId);

        if (shoppingListOptional.isPresent()) {
            ShoppingList shoppingList = shoppingListService.getShoppingListById(listId).get();
            product.setShoppingList(shoppingList);
            return ResponseEntity.ok(productService.addProduct(product));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Update an existing product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
