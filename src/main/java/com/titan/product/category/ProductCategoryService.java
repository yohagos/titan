package com.titan.product.category;

import com.titan.product.ProductService;
import com.titan.product.request.ProductCategoryRequest;
import com.titan.product.response.ProductCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private final ProductCategoryRepository categoryRepository;

    private static final Logger log = LoggerFactory.getLogger(ProductCategoryService.class);

    public List<ProductCategoryEntity> getCategories() {
        return categoryRepository.findAll();
    }

    public ProductCategoryResponse addProductCategory(ProductCategoryRequest request) {
        if (request.getCategoryName() == null || request.getCategoryName().isEmpty()) {
            throw new IllegalArgumentException("Category name is null or empty, cannot be added");
        }
        if (request.getUnit() == null || request.getUnit().isEmpty()) {
            throw new IllegalArgumentException("Category unit is null or empty, cannot be added");
        }
        if (request.getMeasurement() > 0.0) {
            throw new IllegalArgumentException("Category unit is 0, cannot be added");
        }

        var category = categoryRepository.save(
                new ProductCategoryEntity(request.getCategoryName(), request.getMeasurement(), request.getUnit())
        );

        return new ProductCategoryResponse(
                category.getId(),
                category.getCategoryName(),
                category.getMeasurement(),
                category.getUnit()
        );
    }

    public ProductCategoryResponse updateCategory(Long id, ProductCategoryRequest request) {
        var category = categoryRepository.findById(id).orElseThrow();

        Optional.ofNullable(request.getCategoryName())
                        .filter(name -> !name.isEmpty() || !name.contentEquals(category.getCategoryName()))
                                .ifPresent(category::setCategoryName);
        Optional.ofNullable(request.getUnit())
                        .filter(unit -> !unit.isEmpty() || !unit.contentEquals(category.getUnit()))
                                .ifPresent(category::setUnit);
        Optional.ofNullable(request.getMeasurement())
                        .filter(measure -> !measure.isInfinite())
                                .ifPresent(category::setMeasurement);
        categoryRepository.save(category);
        return new ProductCategoryResponse(
                category.getId(),
                category.getCategoryName(),
                category.getMeasurement(),
                category.getUnit()
        );
    }

    public ProductCategoryResponse deleteCategory(Long id) {
        var category = categoryRepository.findById(id).orElseThrow();
        categoryRepository.deleteById(id);
        return new ProductCategoryResponse(
                category.getId(),
                category.getCategoryName(),
                category.getMeasurement(),
                category.getUnit()
        );
    }
}
