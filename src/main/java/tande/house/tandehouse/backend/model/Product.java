package tande.house.tandehouse.backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    private String id;

    private String name;        // nombre producto
    private String description; // Descripción
    private BigDecimal price;   // Precio
    private Integer stock;      // cantidad de stock dispo
    private String category;    // categoría producto
    private String imageUrl;    // link de imagen
}