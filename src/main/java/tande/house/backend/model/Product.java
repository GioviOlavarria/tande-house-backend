package tande.house.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

    @Id
    private String id;

    private String nombre;

    private Integer precio;

    @Column(columnDefinition = "TEXT")
    private String portada;

    private String categoria;

    private Boolean oferta;

    private String sku;

    private Integer stock;
}
