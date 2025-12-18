package org.aymen.rhroussi.inventoryservice;

import org.aymen.rhroussi.inventoryservice.entities.Product;
import org.aymen.rhroussi.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                            .id(UUID.randomUUID().toString())
                            .name("Product 1")
                            .price(3200)
                            .quantity(22)
                    .build());
            productRepository.save(Product.builder()
                                 .id(UUID.randomUUID().toString())
                                 .name("Product 2")
                                 .price(4200)
                                 .quantity(52)
                         .build());
            productRepository.save(Product.builder()
                                 .id(UUID.randomUUID().toString())
                                 .name("Product 3")
                                 .price(6200)
                                 .quantity(12)
                         .build());

            productRepository.findAll().forEach(p->{
                System.out.println("=========================");
                System.out.println(p.toString());
                System.out.println("=========================");
            });

        };



    }

}
