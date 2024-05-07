package net.nwrn.pf_contest.origin_images.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPersonImageEntity is a Querydsl query type for PersonImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPersonImageEntity extends EntityPathBase<PersonImageEntity> {

    private static final long serialVersionUID = 37128646L;

    public static final QPersonImageEntity personImageEntity = new QPersonImageEntity("personImageEntity");

    public final NumberPath<Long> personImageId = createNumber("personImageId", Long.class);

    public final StringPath personImageUrl = createString("personImageUrl");

    public QPersonImageEntity(String variable) {
        super(PersonImageEntity.class, forVariable(variable));
    }

    public QPersonImageEntity(Path<? extends PersonImageEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPersonImageEntity(PathMetadata metadata) {
        super(PersonImageEntity.class, metadata);
    }

}

