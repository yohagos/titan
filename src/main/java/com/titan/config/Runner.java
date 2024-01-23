package com.titan.config;

import com.titan.product.ProductEntity;
import com.titan.product.ProductRepository;
import com.titan.product.category.CategoryUnit;
import com.titan.product.category.ProductCategoryEntity;
import com.titan.product.category.ProductCategoryRepository;
import com.titan.product.category.icons.IconsEntity;
import com.titan.product.category.icons.IconsRepository;
import com.titan.table.TableEntity;
import com.titan.table.TableRepository;
import com.titan.user.UserEntity;
import com.titan.user.UserRepository;
import com.titan.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class Runner {

    private final PasswordEncoder passwordEncoder;

    private static final Logger log = LoggerFactory.getLogger(Runner.class);

    @Bean
    CommandLineRunner commandLineRunner(
            UserRepository userRepository,
            TableRepository tableRepository,
            ProductRepository productRepository,
            ProductCategoryRepository categoryRepository,
            IconsRepository iconsRepository
    ) {
        return args -> {
            userRepository.saveAll(
                    List.of(
                            new UserEntity(
                                    1L, "James", "Jameson", "james@jameson.com",
                                    passwordEncoder.encode("jameson"), 1234, LocalDateTime.now(), UserRole.ADMIN
                            ),
                            new UserEntity(
                                    2L, "Anna", "Smith", "anna@smith.com",
                                    passwordEncoder.encode("smith"), 2341, LocalDateTime.now(), UserRole.MANAGER
                            ),
                            new UserEntity(
                                    3L, "Benni","Elaine", "benni@elaine.com",
                                    passwordEncoder.encode("elaine"), 4321, LocalDateTime.now(), UserRole.ADMIN
                            ),
                            new UserEntity(
                                    4L, "Yosef","Hagos", "yosef@hagos.com",
                                    passwordEncoder.encode("hagos"), 4321, LocalDateTime.now(), UserRole.ADMIN
                            )
                    )
            );
            var iconDessert = new IconsEntity(5L, "cake", "Cake");
            var iconLocalBar = new IconsEntity(8L, "local_bar", "Local Bar");

            iconsRepository.saveAll(
                    List.of(
                            new IconsEntity(1L, "coffee", "Coffee"),
                            new IconsEntity(2L, "liquor", "Liquor"),
                            new IconsEntity(3L, "wine_bar", "Wine Bar"),
                            new IconsEntity(4L, "lunch_dining", "Lunch Dining"),
                            iconDessert,
                            new IconsEntity(6L, "dinner_dining", "Dinner Dining"),
                            new IconsEntity(7L, "restaurant", "Restaurant"),
                            iconLocalBar,
                            new IconsEntity(9L, "bakery_dining", "Bakery Dining"),
                            new IconsEntity(10L, "local_dining", "Local Dining"),
                            new IconsEntity(11L, "icecream", "Icecream")
                    )
            );



            categoryRepository.saveAll(
                    List.of(
                            new ProductCategoryEntity(1L, "Drink", 0.5, CategoryUnit.ML.name(), "#86d3a0"),
                            new ProductCategoryEntity(2L, "Cocktail", 0.3, CategoryUnit.ML.name(), "#a2a2d0"),
                            new ProductCategoryEntity(3L, "Drink", 0.2, CategoryUnit.ML.name(), "#86d3a0"),
                            new ProductCategoryEntity(4L, "Drink", 0.4, CategoryUnit.ML.name(), "#86d3a0"),
                            new ProductCategoryEntity(5L, "Starter", 500.0, CategoryUnit.MG.name(), "#f4d9cc"),
                            new ProductCategoryEntity(6L, "Lunch", 800.0, CategoryUnit.G.name(), "#f6c19c"),
                            new ProductCategoryEntity(7L, "Brunch", 400.0, CategoryUnit.G.name(), "#f59794"),
                            new ProductCategoryEntity(8L, "Main", 600.0, CategoryUnit.G.name(), "#a3c1ad"),
                            new ProductCategoryEntity(9L, "Dessert", 300.0, CategoryUnit.G.name(), "#f5cc7f"),
                            new ProductCategoryEntity(10L, "Dessert", 350.0, CategoryUnit.G.name(), "#f5cc7f")
                    )
            );

            var categoryDrink = categoryRepository.findById(1L).orElseThrow();
            var categoryCocktail = categoryRepository.findById(2L).orElseThrow();
            var categoryStarter = categoryRepository.findById(1L).orElseThrow();
            var categoryLunch = categoryRepository.findById(6L).orElseThrow();

            productRepository.saveAll(
                    List.of(
                            new ProductEntity(1L, "Pomegranate Lemonade", 4.2D, categoryDrink),
                            new ProductEntity(2L, "Elderflower Lemonade", 4.2D, categoryDrink),
                            new ProductEntity(3L, "Cosmopolitan", 13.0D, categoryCocktail),
                            new ProductEntity(4L, "Espresso Martini", 13.0D, categoryCocktail),
                            new ProductEntity(5L, "Old fashioned", 11.0D, categoryCocktail),
                            new ProductEntity(6L, "Greek Salad", 6.2D, categoryStarter),
                            new ProductEntity(7L, "Soup", 5.6D, categoryStarter),
                            new ProductEntity(8L, "Ramen", 12.5D, categoryLunch),
                            new ProductEntity(9L, "Rib Eye", 17.0D, categoryLunch),
                            new ProductEntity(10L, "Penne", 10.4D, categoryLunch),
                            new ProductEntity(11L, "Yuzu Lemonade", 4.2D, categoryDrink),
                            new ProductEntity(12L, "Basil Lemonade", 4.2D, categoryDrink),
                            new ProductEntity(13L, "Mai Tai", 13.0D, categoryCocktail),
                            new ProductEntity(14L, "Martini", 13.0D, categoryCocktail),
                            new ProductEntity(15L, "Negroni", 11.0D, categoryCocktail),
                            new ProductEntity(16L, "FranceSalad", 6.2D, categoryStarter),
                            new ProductEntity(17L, "Tomato Soup", 5.6D, categoryStarter),
                            new ProductEntity(18L, "Udon", 12.5D, categoryLunch),
                            new ProductEntity(19L, "Chicken", 17.0D, categoryLunch),
                            new ProductEntity(20L, "Spaghetti", 10.4D, categoryLunch)
                    )
            );

            var tableOne = List.of(
                    new ProductEntity(13L, "Mai Tai", 13.0D, categoryCocktail),
                    new ProductEntity(14L, "Martini", 13.0D, categoryCocktail),
                    new ProductEntity(15L, "Negroni", 11.0D, categoryCocktail)
            );

            var tableTwo = List.of(
                    new ProductEntity(13L, "Mai Tai", 13.0D, categoryCocktail),
                    new ProductEntity(14L, "Martini", 13.0D, categoryCocktail),
                    new ProductEntity(15L, "Negroni", 11.0D, categoryCocktail)
            );

            tableRepository.saveAll(
                    List.of(
                            new TableEntity(1L, 100, 0.0D, 4, false,null, null, 100, 230, List.of()),
                            new TableEntity(2L, 110, 0.0D, 2, false,null, null, 46, 175, List.of()),
                            new TableEntity(3L, 120, 0.0D, 4, false,null, null, -10, 120, List.of()),
                            new TableEntity(4L, 210, 0.0D, 6, true,null, null, 65, 188, tableOne),
                            new TableEntity(5L, 220, 0.0D, 4, false,null, null, 81, 187, List.of()),
                            new TableEntity(6L, 300, 0.0D, 8, true, null, null, 190, 204, tableTwo),
                            new TableEntity(7L, 310, 0.0D, 6, false, null, null, 212, 204, List.of()),
                            new TableEntity(8L, 420, 0.0D, 4, false, null, null, -55, 382, List.of()),
                            new TableEntity(9L, 400, 0.0D, 8, false, null, null, -206, 382, List.of())
                    )
            );

            log.info(userRepository.findAll().toString());
            log.info(tableRepository.findAll().toString());
            log.info(productRepository.findAll().toString());
            log.info(categoryRepository.findAll().toString());
            log.info(iconsRepository.findAll().toString());
        };
    }
}
