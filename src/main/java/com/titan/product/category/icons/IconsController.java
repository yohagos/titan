package com.titan.product.category.icons;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/icons")
@RequiredArgsConstructor
public class IconsController {

    private final IconsService iconsService;

    @GetMapping
    public ResponseEntity<List<IconsEntity>> getIconsList() {
        return ResponseEntity.ok(iconsService.getIconsList());
    }
}
