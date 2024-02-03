package com.titan.storage;

import com.titan.product.stock.UnitConverter;
import com.titan.storage.requests.StorageAddRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
