package com.titan.storage;

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
}
