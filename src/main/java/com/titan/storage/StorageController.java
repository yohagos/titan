package com.titan.storage;

import com.titan.storage.requests.StorageAddRequest;
import com.titan.storage.requests.StorageEditRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/storages")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @GetMapping
    public ResponseEntity<List<StorageEntity>> getInventory() {
        return ResponseEntity.ok(storageService.getStorageContent());
    }

    @PostMapping("/add")
    public ResponseEntity<StorageEntity> addStorage(
            @RequestBody StorageAddRequest request
    ) {
        return ResponseEntity.ok(storageService.addProductToStorage(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StorageEntity> editInventory(
            @PathVariable(name = "id") Long id,
            @RequestBody StorageEditRequest request
    ) {
        return ResponseEntity.ok(storageService.updateInventory(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StorageEntity> deleteInventory(
            @PathVariable(name = "id") Long id
    ) {
        return ResponseEntity.ok(storageService.deleteInventoryById(id));
    }

}
