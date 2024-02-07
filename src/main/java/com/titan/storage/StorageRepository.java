package com.titan.storage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<StorageEntity, Long> {

    Optional<StorageEntity> findByName(String name);
}
