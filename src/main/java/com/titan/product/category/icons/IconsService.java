package com.titan.product.category.icons;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IconsService {

    private final IconsRepository iconsRepository;

    public List<IconsEntity> getIconsList() {
        return iconsRepository.findAll();
    }
}
