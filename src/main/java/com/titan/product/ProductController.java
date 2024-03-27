package com.titan.product;

import com.titan.product.request.ProductAddRequest;
import com.titan.product.response.ProductResponse;
import com.titan.product.request.ProductUpdateRequest;
import com.titan.product.stock.request.ProductStockAddRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @GetMapping
    public ResponseEntity<List<ProductEntity>> productList() {
        return ResponseEntity.ok(productService.getProductList());
    }

    @PostMapping("/add")
    public ResponseEntity<ProductResponse> addProduct(
            @RequestBody ProductAddRequest request
    ) {
        return ResponseEntity.ok(productService.addProduct(request));
    }

    @PostMapping("add/{id}")
    public ResponseEntity<?> addComponentsToProduct(
            @PathVariable(name = "id") Long id,
            @RequestBody List<ProductStockAddRequest> list
    ) {
        productService.addComponentsToProduct(id, list);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> productUpdate(
            @RequestBody ProductUpdateRequest request
    ) {
        return ResponseEntity.ok(productService.updateProduct(request));
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity deleteProductById(
            @PathVariable(name = "product_id") Long id
    ) {
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }
}
