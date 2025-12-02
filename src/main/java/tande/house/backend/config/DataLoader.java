package tande.house.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tande.house.backend.model.Product;
import tande.house.backend.repository.ProductRepository;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final ProductRepository productRepository;

    @Bean
    CommandLineRunner initProducts() {
        return args -> {

            if (productRepository.count() > 0) {
                return;
            }

            // prod1
            Product p1 = new Product();
            p1.setId("p1");
            p1.setNombre("Lucario VSTAR");
            p1.setPrecio(24990);
            p1.setPortada("assets/cartas/lucario-vstar.jpg");
            p1.setCategoria("Competitivo");
            p1.setOferta(false);
            p1.setSku("LUC-VS-001");

            // prod2
            Product p2 = new Product();
            p2.setId("p2");
            p2.setNombre("Tapu Bulu");
            p2.setPrecio(13990);
            p2.setPortada("assets/cartas/tapu-bulu.png");
            p2.setCategoria("Sellos");
            p2.setOferta(true);
            p2.setSku("TPB-001");

            // prod3
            Product p3 = new Product();
            p3.setId("p3");
            p3.setNombre("Victini Promo");
            p3.setPrecio(9990);
            p3.setPortada("assets/cartas/victini-promo.jpg");
            p3.setCategoria("Promos");
            p3.setOferta(true);
            p3.setSku("VIC-PRO-001");

            // prod4
            Product p4 = new Product();
            p4.setId("p4");
            p4.setNombre("Zekrom EX");
            p4.setPrecio(18990);
            p4.setPortada("assets/cartas/zekrom-ex.jpg");
            p4.setCategoria("Competitivo");
            p4.setOferta(false);
            p4.setSku("ZEK-EX-001");

            // prod5
            Product p5 = new Product();
            p5.setId("p5");
            p5.setNombre("M Lucario EX");
            p5.setPrecio(21990);
            p5.setPortada("assets/cartas/m-lucario-ex.jpg");
            p5.setCategoria("Competitivo");
            p5.setOferta(false);
            p5.setSku("MLU-EX-001");

            // prod6
            Product p6 = new Product();
            p6.setId("p6");
            p6.setNombre("Dipplin");
            p6.setPrecio(7990);
            p6.setPortada("assets/cartas/dipplin.png");
            p6.setCategoria("Sellos");
            p6.setOferta(false);
            p6.setSku("DIP-001");

            // prod7
            Product p7 = new Product();
            p7.setId("p7");
            p7.setNombre("Kingdra IR");
            p7.setPrecio(15990);
            p7.setPortada("assets/cartas/kingdraIR.jpg");
            p7.setCategoria("Sellos");
            p7.setOferta(false);
            p7.setSku("KIN-IR-001");

            // prod8
            Product p8 = new Product();
            p8.setId("p8");
            p8.setNombre("Morpeko");
            p8.setPrecio(6990);
            p8.setPortada("assets/cartas/morpeko.jpg");
            p8.setCategoria("Sellos");
            p8.setOferta(true);
            p8.setSku("MOR-001");

            // prod9
            Product p9 = new Product();
            p9.setId("p9");
            p9.setNombre("N Reshiram Stamp");
            p9.setPrecio(12990);
            p9.setPortada("assets/cartas/n-reshiram-stamp.jpg");
            p9.setCategoria("Sellos");
            p9.setOferta(false);
            p9.setSku("NRS-001");

            productRepository.saveAll(List.of(
                    p1, p2, p3, p4, p5, p6, p7, p8, p9
            ));
        };
    }
}
