package com.example.demo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.dao.ProductDao;
import com.example.demo.entity.Product;

@SpringBootApplication
public class FlowerShopBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowerShopBackendApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(ProductDao productDao) {
        return r -> {
            if (productDao.count() == 0) {
                List<Product> products = List.of(
                    new Product("Red Rose", new BigDecimal("15.99"), 10, "/images/red-rose.png"),
                    new Product("White Lily", new BigDecimal("18.50"), 8, "/images/white-lily.png"),
                    new Product("Sunflower", new BigDecimal("12.00"), 15, "/images/sunflower.png"),
                    new Product("Purple Orchid", new BigDecimal("25.75"), 5, "/images/purple-orchid.png"),
                    new Product("Pink Tulip", new BigDecimal("14.20"), 12, "/images/pink-tulip.png"),
                    new Product("Pink Carnation", new BigDecimal("10.99"), 20, "/images/pink-carnation.png"),
                    new Product("Lavender", new BigDecimal("9.50"), 18, "/images/lavender.png"),
                    new Product("Gardenia Bridal", new BigDecimal("22.80"), 7, "/images/gardenia-bridal.png"),
                    new Product("Daisy", new BigDecimal("8.75"), 25, "/images/daisy.png"),
                    new Product("Pink Mini Snowball", new BigDecimal("19.99"), 9, "/images/pink-mini-snowball.png"),
                    new Product("Elegant Fresh Peony", new BigDecimal("16.30"), 11, "/images/elegant-fresh-peony.png"),
                    new Product("Purple Hydrangea", new BigDecimal("23.40"), 6, "/images/purple-hydrangea.png")
                );

                productDao.saveAll(products);
            }
        };
    }
}
