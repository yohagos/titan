package com.titan.product.category;

import com.titan.product.ProductController;
import com.titan.product.ProductService;
import com.titan.product.request.ProductCategoryRequest;
import com.titan.product.response.ProductCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService categoryService;

    private static final Logger log = LoggerFactory.getLogger(ProductCategoryController.class);

    @GetMapping
    public ResponseEntity<List<ProductCategoryEntity>> getCategoryList() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @PostMapping("/add")
    public ResponseEntity<ProductCategoryResponse> addCategory(
            @RequestBody ProductCategoryRequest request
    ) {
        log.info(request.toString());
        return ResponseEntity.ok(categoryService.addProductCategory(request));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ProductCategoryResponse> updateCategory(
            @PathVariable(name = "categoryId") Long id,
            @RequestBody ProductCategoryRequest request
    ) {
        return ResponseEntity.ok(categoryService.updateCategory(id, request));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ProductCategoryResponse> deleteCategory(
            @PathVariable(name = "categoryId") Long id
    ) {
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }
}
