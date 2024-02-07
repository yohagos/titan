package com.titan.product;

import com.titan.product.category.ProductCategoryRepository;
import com.titan.product.request.ProductAddRequest;
import com.titan.product.response.ProductResponse;
import com.titan.product.request.ProductUpdateRequest;
import com.titan.product.stock.ProductsStockEntity;
import com.titan.product.stock.ProductsStockRepository;
import com.titan.product.stock.UnitConverter;
import com.titan.product.stock.request.ProductStockAddRequest;
import com.titan.storage.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository categoryRepository;
    private final ProductsStockRepository productsStockRepository;
    private final StorageRepository storageRepository;

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public List<ProductEntity> getProductList() {
        return productRepository.findAll();
    }

    public ProductResponse addProduct(ProductAddRequest request) {
        var category = categoryRepository.findById(request.getProductCategoryId()).orElseThrow();

        var product = new ProductEntity(
                request.getName(),
                request.getPrice(),
                category
        );
        var addedProduct = productRepository.save(product);
        return new ProductResponse(
                addedProduct.getName(),
                addedProduct.getId()
        );
    }

    public ProductResponse updateProduct(ProductUpdateRequest request) {
        var product = productRepository.findById(request.getId()).orElseThrow();
        var category = categoryRepository.findById(request.getCategoryId()).orElseThrow();
        Optional.ofNullable(request.getName())
                .filter(
                        name -> !name.isEmpty() || !name.contentEquals(product.getName())
                ).ifPresent(product::setName);
        Optional.ofNullable(request.getPrice())
                .filter(
                        price -> !price.isNaN() || !price.equals(product.getPrice())
                ).ifPresent(product::setPrice);
        Optional.of(category)
                .filter(
                        cat -> false
                )
                .ifPresent(product::setCategory);

        productRepository.save(product);
        return new ProductResponse(
                product.getName(),
                product.getId()
        );
    }

    public void deleteProductById(Long id) {
        var product = productRepository.findById(id).orElseThrow();
        productRepository.deleteById(product.getId());
    }

    public void addComponentsToProduct(Long id, List<ProductStockAddRequest> list) {
        var product = productRepository.findById(id).orElseThrow();
        List<ProductsStockEntity> stocks = new ArrayList<>();
        for (var stock: list) {
            var data = storageRepository.findByName(stock.getGood()).orElseThrow();
            var store = new ProductsStockEntity(
                    UnitConverter.Unit.valueOf(stock.getUnit()),
                    stock.getMeasurement(),
                    data
            );
            var productStock = productsStockRepository.save(store);
            stocks.add(productStock);
        }
        product.setComponents(stocks);
    }
}
