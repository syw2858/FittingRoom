package net.nwrn.pf_contest.clothes.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.nwrn.pf_contest.clothes.entity.ClothesEntity;
import net.nwrn.pf_contest.origin_images.dto.filter.Category;
import net.nwrn.pf_contest.origin_images.dto.filter.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

import static net.nwrn.pf_contest.clothes.entity.QClothesEntity.clothesEntity;

@Repository
@RequiredArgsConstructor
public class ClothesQueryDslImpl implements ClothesQueryDsl {
    private final JPAQueryFactory queryFactory;

    private Predicate categoryFilter(Category category) {
        if (category == null)
            return null;
        return clothesEntity.category.eq(category);
    }

    private Predicate colorFilter(Color color) {
        if (color == null)
            return null;
        return clothesEntity.color.eq(color);
    }

    @Override
    public Page<ClothesEntity> getClothes(Category category, Color color, Integer page, Integer size) {
        JPAQuery<ClothesEntity> query = queryFactory
                .select(clothesEntity)
                .from(clothesEntity)
                .where(categoryFilter(category),
                        colorFilter(color));
        List<ClothesEntity> content = query.fetch();
        long total = content.size();
        long offset = (long) page * size;

        content = content.stream().skip(offset).limit(size).toList();

        return new PageImpl<>(content, PageRequest.of(page, size), total);
    }
}
