package tande.house.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tande.house.backend.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
}
