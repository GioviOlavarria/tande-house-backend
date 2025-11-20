package tande.house.tandehouse.backend.controller;

import org.springframework.web.bind.annotation.*;
import tande.house.tandehouse.backend.model.Product;
import tande.house.tandehouse.backend.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000") // Ajustar
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /api/products
    @GetMapping
    public List<Product> getAll() {
        return productService.findAll();
    }

    // GET /api/products/{id}
    @GetMapping("/{id}")
    public Product getById(@PathVariable String id) {
        return productService.findById(id);
    }

    // POST /api/products
    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    // PUT /api/products/{id}
    @PutMapping("/{id}")
    public Product update(@PathVariable String id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    // DELETE /api/products/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        productService.delete(id);
    }
}