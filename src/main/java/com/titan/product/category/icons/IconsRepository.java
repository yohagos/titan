package com.titan.product.category.icons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IconsRepository extends JpaRepository<IconsEntity, Long> {

    Optional<IconsEntity> findIconsByLabel(String label);
}
