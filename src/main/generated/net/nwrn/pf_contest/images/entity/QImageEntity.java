package net.nwrn.pf_contest.images.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QImageEntity is a Querydsl query type for ImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImageEntity extends EntityPathBase<ImageEntity> {

    private static final long serialVersionUID = -348823106L;

    public static final QImageEntity imageEntity = new QImageEntity("imageEntity");

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> objectId = createNumber("objectId", Long.class);

    public final StringPath repoName = createString("repoName");

    public QImageEntity(String variable) {
        super(ImageEntity.class, forVariable(variable));
    }

    public QImageEntity(Path<? extends ImageEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QImageEntity(PathMetadata metadata) {
        super(ImageEntity.class, metadata);
    }

}

