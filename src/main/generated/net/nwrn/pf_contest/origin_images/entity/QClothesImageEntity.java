package net.nwrn.pf_contest.origin_images.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QClothesImageEntity is a Querydsl query type for ClothesImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClothesImageEntity extends EntityPathBase<ClothesImageEntity> {

    private static final long serialVersionUID = 375874585L;

    public static final QClothesImageEntity clothesImageEntity = new QClothesImageEntity("clothesImageEntity");

    public final EnumPath<net.nwrn.pf_contest.origin_images.dto.filter.Category> category = createEnum("category", net.nwrn.pf_contest.origin_images.dto.filter.Category.class);

    public final NumberPath<Long> clothesImageSn = createNumber("clothesImageSn", Long.class);

    public final StringPath clothesImageUrl = createString("clothesImageUrl");

    public final EnumPath<net.nwrn.pf_contest.origin_images.dto.filter.Color> color = createEnum("color", net.nwrn.pf_contest.origin_images.dto.filter.Color.class);

    public QClothesImageEntity(String variable) {
        super(ClothesImageEntity.class, forVariable(variable));
    }

    public QClothesImageEntity(Path<? extends ClothesImageEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClothesImageEntity(PathMetadata metadata) {
        super(ClothesImageEntity.class, metadata);
    }

}

