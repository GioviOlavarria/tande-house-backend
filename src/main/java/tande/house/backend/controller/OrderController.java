package tande.house.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tande.house.backend.dto.OrderItemRequest;
import tande.house.backend.dto.OrderRequest;
import tande.house.backend.model.Product;
import tande.house.backend.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {

    private final ProductRepository productRepository;

    @PostMapping
    @Transactional
    public void create(@RequestBody OrderRequest request) {
        if (request == null || request.getItems() == null || request.getItems().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carro vacío");
        }

        List<Product> toSave = new ArrayList<>();

        for (OrderItemRequest item : request.getItems()) {
            if (item == null || item.getProductId() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Producto inválido en la orden");
            }

            Product p = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Producto no encontrado"));

            int currentStock = p.getStock() == null ? 0 : p.getStock();
            int qty = item.getQuantity() == null ? 0 : item.getQuantity();

            if (qty <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cantidad inválida");
            }
            if (currentStock < qty) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Sin stock suficiente para " + p.getNombre()
                );
            }

            p.setStock(currentStock - qty);
            toSave.add(p);
        }
        productRepository.saveAll(toSave);
    }
}
