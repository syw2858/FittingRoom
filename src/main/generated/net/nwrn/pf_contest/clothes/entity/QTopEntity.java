package net.nwrn.pf_contest.clothes.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTopEntity is a Querydsl query type for TopEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTopEntity extends EntityPathBase<TopEntity> {

    private static final long serialVersionUID = 817186910L;

    public static final QTopEntity topEntity = new QTopEntity("topEntity");

    public final NumberPath<Long> topId = createNumber("topId", Long.class);

    public final DateTimePath<java.sql.Timestamp> topRegisterDt = createDateTime("topRegisterDt", java.sql.Timestamp.class);

    public final StringPath topUrl = createString("topUrl");

    public QTopEntity(String variable) {
        super(TopEntity.class, forVariable(variable));
    }

    public QTopEntity(Path<? extends TopEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTopEntity(PathMetadata metadata) {
        super(TopEntity.class, metadata);
    }

}

