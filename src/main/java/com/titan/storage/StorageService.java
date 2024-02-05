package com.titan.storage;

import com.titan.product.stock.UnitConverter;
import com.titan.storage.requests.StorageAddRequest;
import com.titan.storage.requests.StorageEditRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository storageRepository;

    public List<StorageEntity> getStorageContent() {
        return storageRepository.findAll();
    }

    public StorageEntity addProductToStorage(StorageAddRequest request) {
        var unit = UnitConverter.Unit.valueOf(request.getUnit());
        return storageRepository.save(
                new StorageEntity(
                        request.getName(),
                        request.getPricePerBottle(),
                        request.getStockOfBottles(),
                        unit,
                        request.getMeasurement(),
                        request.getCurrentStock(),
                        request.getCriticalStockOfBottles()
                )
        );
    }

    public StorageEntity updateInventory(Long id, StorageEditRequest request) {
        var inventory = storageRepository.findById(id).orElseThrow();

        var unit = UnitConverter.Unit.valueOf(request.getUnit());

        Optional.of(request.getName())
                .filter(
                        name -> !name.isEmpty() || !name.contentEquals(inventory.getName())
                ).ifPresent(inventory::setName);
        Optional.of(request.getPricePerBottle())
                .filter(
                        price -> !price.isNaN() || !price.equals(inventory.getPricePerBottle())
                ).ifPresent(inventory::setPricePerBottle);
        Optional.of(request.getStockOfBottles())
                .filter(
                        stock -> stock >= 0 || !stock.equals(inventory.getStockOfBottles())
                ).ifPresent(inventory::setStockOfBottles);
        Optional.of(request.getCurrentStock())
                .filter(
                        current -> !current.isNaN()
                ).ifPresent(inventory::setCurrentStock);
        Optional.of(request.getCriticalStockOfBottles())
                .filter(
                        critical -> critical >= 0 || !critical.equals(inventory.getCriticalStockOfBottles())
                ).ifPresent(inventory::setCriticalStockOfBottles);
        Optional.of(request.getMeasurement())
                .filter(
                        measure -> !measure.isNaN() || !measure.equals(inventory.getMeasurement())
                ).ifPresent(inventory::setMeasurement);
        Optional.of(unit)
                .filter(
                        u -> false
                ).ifPresent(inventory::setUnit);

        return storageRepository.save(inventory);
    }
}
