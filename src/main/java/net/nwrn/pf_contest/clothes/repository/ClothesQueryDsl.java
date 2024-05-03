package net.nwrn.pf_contest.clothes.repository;

import net.nwrn.pf_contest.clothes.entity.ClothesEntity;
import net.nwrn.pf_contest.origin_images.dto.filter.Category;
import net.nwrn.pf_contest.origin_images.dto.filter.Color;
import org.springframework.data.domain.Page;

public interface ClothesQueryDsl {
    Page<ClothesEntity> getClothes(Category category, Color color, Integer page, Integer size);
}
