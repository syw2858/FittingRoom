package net.nwrn.pf_contest.clothes.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QClothesEntity is a Querydsl query type for ClothesEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClothesEntity extends EntityPathBase<ClothesEntity> {

    private static final long serialVersionUID = 1365655057L;

    public static final QClothesEntity clothesEntity = new QClothesEntity("clothesEntity");

    public final EnumPath<net.nwrn.pf_contest.origin_images.dto.filter.Category> category = createEnum("category", net.nwrn.pf_contest.origin_images.dto.filter.Category.class);

    public final EnumPath<net.nwrn.pf_contest.origin_images.dto.filter.Color> color = createEnum("color", net.nwrn.pf_contest.origin_images.dto.filter.Color.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QClothesEntity(String variable) {
        super(ClothesEntity.class, forVariable(variable));
    }

    public QClothesEntity(Path<? extends ClothesEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClothesEntity(PathMetadata metadata) {
        super(ClothesEntity.class, metadata);
    }

}

