package com.titan.config;

import com.titan.product.ProductEntity;
import com.titan.product.ProductRepository;
import com.titan.product.category.CategoryUnit;
import com.titan.product.category.ProductCategoryEntity;
import com.titan.product.category.ProductCategoryRepository;
import com.titan.product.category.icons.IconsEntity;
import com.titan.product.category.icons.IconsRepository;
import com.titan.product.stock.ProductsStockEntity;
import com.titan.product.stock.ProductsStockRepository;
import com.titan.product.stock.UnitConverter;
import com.titan.settings.SettingRepository;
import com.titan.storage.StorageEntity;
import com.titan.storage.StorageRepository;
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
            IconsRepository iconsRepository,
            StorageRepository storageRepository,
            ProductsStockRepository productsStockRepository,
            SettingRepository settingRepository
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
                                    passwordEncoder.encode("elaine"), 4321, LocalDateTime.now(), UserRole.USER
                            ),
                            new UserEntity(
                                    4L, "Yosef","Hagos", "yosef@hagos.com",
                                    passwordEncoder.encode("hagos"), 1111, LocalDateTime.now(), UserRole.ADMIN
                            )
                    )
            );

            var belvederStock = new StorageEntity(1L, "Belveder", 22.75D, 20, UnitConverter.Unit.L, 1D, 17D, 5);
            var greyGooseStock = new StorageEntity(2L, "Grey Goose", 19.99D, 30,UnitConverter.Unit.L, 1D, 22D, 10);
            var makersMakerStock = new StorageEntity(3L, "Maker's Maker", 27.75D, 20,UnitConverter.Unit.L, 1D, 19.5D, 7);
            var bombayStock = new StorageEntity(4L, "Bombay Dry Gin", 16.5D, 30,UnitConverter.Unit.L, 1D, 26.2D, 10);
            var simpleSyrupStock = new StorageEntity(5L, "Simple Sirup", 7D, 10,UnitConverter.Unit.L, 1D, 8.9D, 3);
            var cranberryJuiceStock = new StorageEntity(6L, "Cranberry Juice", 4.99D, 10,UnitConverter.Unit.L, 1D, 7.5D, 5);
            var pomegranateSyrupStock = new StorageEntity(7L, "Pomegranate Syrup", 6.70D, 12,UnitConverter.Unit.L, 1D, 8.5D, 4);
            var elderflowerSyrupStock = new StorageEntity(8L, "Elderflower Syrup", 6.7D, 12,UnitConverter.Unit.L, 1D, 9.2D, 5);
            var sparklingWaterStock = new StorageEntity(9L, "Sparkling Water", 4.4D, 30,UnitConverter.Unit.L, 1D, 22D, 10);
            var yuzuSyrupStock = new StorageEntity(10L, "Yuzu Syrup", 6.7D, 12,UnitConverter.Unit.L, 1D, 9.2D, 5);
            var basilSyrupStock = new StorageEntity(11L, "Basil Water", 6.7D, 12,UnitConverter.Unit.L, 1D, 9.2D, 5);
            var limeJuiceStock = new StorageEntity(10L, "Yuzu Syrup", 6.7D, 12,UnitConverter.Unit.L, 1D, 9.2D, 5);
            var campariStock = new StorageEntity(12L, "Campari", 11D, 12,UnitConverter.Unit.L, 1D, 11.2D, 6);
            var vermouthStock = new StorageEntity(13L, "Vermouth", 9.2D, 12,UnitConverter.Unit.L, 1D, 9.2D, 6);

            storageRepository.saveAll(
                    List.of(
                            belvederStock, greyGooseStock, makersMakerStock, bombayStock,
                            simpleSyrupStock, cranberryJuiceStock, pomegranateSyrupStock, elderflowerSyrupStock,
                            sparklingWaterStock, yuzuSyrupStock, basilSyrupStock, limeJuiceStock, campariStock, vermouthStock
                    )
            );

            var iconDessert = new IconsEntity(5L, "cake", "Cake");
            var iconLocalBar = new IconsEntity(8L, "local_bar", "Local Bar");
            var iconIcecream = new IconsEntity(11L, "icecream", "Icecream");
            var iconDinner = new IconsEntity(6L, "dinner_dining", "Dinner Dining");

            iconsRepository.saveAll(
                    List.of(
                            new IconsEntity(1L, "coffee", "Coffee"),
                            new IconsEntity(2L, "liquor", "Liquor"),
                            new IconsEntity(3L, "wine_bar", "Wine Bar"),
                            new IconsEntity(4L, "lunch_dining", "Lunch Dining"),
                            iconDessert,
                            iconDinner,
                            new IconsEntity(7L, "restaurant", "Restaurant"),
                            iconLocalBar,
                            new IconsEntity(9L, "bakery_dining", "Bakery Dining"),
                            new IconsEntity(10L, "local_dining", "Local Dining"),
                            iconIcecream
                    )
            );

            categoryRepository.saveAll(
                    List.of(
                            new ProductCategoryEntity(1L, "Drink", 0.5, CategoryUnit.ML.name(), "#86d3a0", iconLocalBar),
                            new ProductCategoryEntity(2L, "Cocktail", 0.3, CategoryUnit.ML.name(), "#a2a2d0", iconLocalBar),
                            new ProductCategoryEntity(3L, "Drink", 0.2, CategoryUnit.ML.name(), "#86d3a0", iconLocalBar),
                            new ProductCategoryEntity(4L, "Drink", 0.4, CategoryUnit.ML.name(), "#86d3a0", iconLocalBar),
                            new ProductCategoryEntity(5L, "Starter", 500.0, CategoryUnit.MG.name(), "#f4d9cc", iconDinner),
                            new ProductCategoryEntity(6L, "Lunch", 800.0, CategoryUnit.G.name(), "#f6c19c", iconDinner),
                            new ProductCategoryEntity(7L, "Brunch", 400.0, CategoryUnit.G.name(), "#f59794", iconDinner),
                            new ProductCategoryEntity(8L, "Main", 600.0, CategoryUnit.G.name(), "#a3c1ad", iconDinner),
                            new ProductCategoryEntity(9L, "Dessert", 300.0, CategoryUnit.G.name(), "#f5cc7f", iconDessert),
                            new ProductCategoryEntity(10L, "Dessert", 350.0, CategoryUnit.G.name(), "#f5cc7f", iconDessert)
                    )
            );

            var categoryDrink = categoryRepository.findById(1L).orElseThrow();
            var categoryCocktail = categoryRepository.findById(2L).orElseThrow();

            productsStockRepository.saveAll(
                    List.of(
                            new ProductsStockEntity(1L, UnitConverter.Unit.CL, 5D, makersMakerStock),
                            new ProductsStockEntity(2L, UnitConverter.Unit.CL, 1.5D, simpleSyrupStock),
                            new ProductsStockEntity(3L, UnitConverter.Unit.CL, 5D, pomegranateSyrupStock),
                            new ProductsStockEntity(4L, UnitConverter.Unit.ML, 3D, sparklingWaterStock),
                            new ProductsStockEntity(5L, UnitConverter.Unit.CL, 5D, elderflowerSyrupStock),
                            new ProductsStockEntity(6L, UnitConverter.Unit.ML, 3D, sparklingWaterStock),
                            new ProductsStockEntity(7L, UnitConverter.Unit.CL, 5D, yuzuSyrupStock),
                            new ProductsStockEntity(8L, UnitConverter.Unit.ML, 3D, sparklingWaterStock),
                            new ProductsStockEntity(9L, UnitConverter.Unit.CL, 5D, basilSyrupStock),
                            new ProductsStockEntity(10L, UnitConverter.Unit.ML, 3D, sparklingWaterStock),
                            new ProductsStockEntity(11L, UnitConverter.Unit.CL, 5D, belvederStock),
                            new ProductsStockEntity(12L, UnitConverter.Unit.CL, 3D, cranberryJuiceStock),
                            new ProductsStockEntity(13L, UnitConverter.Unit.CL, 1D, limeJuiceStock),
                            new ProductsStockEntity(14L, UnitConverter.Unit.CL, 1D, simpleSyrupStock),
                            new ProductsStockEntity(15L, UnitConverter.Unit.CL, 3D, bombayStock),
                            new ProductsStockEntity(16L, UnitConverter.Unit.CL, 3D, campariStock),
                            new ProductsStockEntity(17L, UnitConverter.Unit.CL, 3D, vermouthStock)
                    )
            );

            var makerStockOne = productsStockRepository.findById(1L).orElseThrow();
            var makerStockTwo = productsStockRepository.findById(2L).orElseThrow();
            var pomegranateOne = productsStockRepository.findById(3L).orElseThrow();
            var pomegranateTwo = productsStockRepository.findById(4L).orElseThrow();
            var elderOne = productsStockRepository.findById(5L).orElseThrow();
            var elderTwo = productsStockRepository.findById(6L).orElseThrow();
            var yuzuOne = productsStockRepository.findById(7L).orElseThrow();
            var yuzuTwo = productsStockRepository.findById(8L).orElseThrow();
            var basilOne = productsStockRepository.findById(9L).orElseThrow();
            var basilTwo = productsStockRepository.findById(10L).orElseThrow();
            var cosmoOne = productsStockRepository.findById(11L).orElseThrow();
            var cosmoTwo = productsStockRepository.findById(12L).orElseThrow();
            var cosmoThree = productsStockRepository.findById(13L).orElseThrow();
            var cosmoFour = productsStockRepository.findById(14L).orElseThrow();
            var negroniOne = productsStockRepository.findById(15L).orElseThrow();
            var negroniTwo = productsStockRepository.findById(16L).orElseThrow();
            var negroniThree = productsStockRepository.findById(17L).orElseThrow();

            var oldFashioned = List.of(makerStockOne, makerStockTwo);
            var pomegranateLemonade = List.of(pomegranateOne, pomegranateTwo);
            var elderflowerLemonade = List.of(elderOne, elderTwo);
            var yuzuLemonade = List.of(yuzuOne, yuzuTwo);
            var basilLemonade = List.of(basilOne, basilTwo);
            var cosmo = List.of(cosmoOne, cosmoTwo, cosmoThree, cosmoFour);
            var negroni = List.of(negroniOne, negroniTwo, negroniThree);

            productRepository.saveAll(
                    List.of(
                            new ProductEntity(1L, "Pomegranate Lemonade", 4.2D, categoryDrink, pomegranateLemonade),
                            new ProductEntity(2L, "Elderflower Lemonade", 4.2D, categoryDrink, elderflowerLemonade),
                            new ProductEntity(3L, "Cosmopolitan", 13.0D, categoryCocktail, cosmo),
                            new ProductEntity(5L, "Old fashioned", 11.0D, categoryCocktail, oldFashioned),
                            new ProductEntity(11L, "Yuzu Lemonade", 4.2D, categoryDrink, yuzuLemonade),
                            new ProductEntity(12L, "Basil Lemonade", 4.2D, categoryDrink, basilLemonade),
                            new ProductEntity(15L, "Negroni", 11.0D, categoryCocktail, negroni)
                    )
            );

            tableRepository.saveAll(
                    List.of(
                            new TableEntity(1L, 100, 0.0D, 4, false,null, null, 50, 100, List.of()),
                            new TableEntity(2L, 110, 0.0D, 2, false,null, null, 100, 50, List.of()),
                            new TableEntity(3L, 120, 0.0D, 4, false,null, null, 100, 100, List.of())
                    )
            );

            log.info(userRepository.findAll().toString());
            log.info(tableRepository.findAll().toString());
            log.info(productRepository.findAll().toString());
            log.info(categoryRepository.findAll().toString());
            log.info(iconsRepository.findAll().toString());
            log.info(storageRepository.findAll().toString());
            log.info(productsStockRepository.findAll().toString());
            log.info(settingRepository.findAll().toString());
        };
    }
}
