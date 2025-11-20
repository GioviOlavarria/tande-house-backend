package tande.house.tandehouse.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tande.house.tandehouse.backend.model.Product;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {


    List<Product> findByCategory(String category);


    List<Product> findByNameContainingIgnoreCase(String name);
}
