package net.nwrn.pf_contest.clothes.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBottomEntity is a Querydsl query type for BottomEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBottomEntity extends EntityPathBase<BottomEntity> {

    private static final long serialVersionUID = 1864029544L;

    public static final QBottomEntity bottomEntity = new QBottomEntity("bottomEntity");

    public final NumberPath<Long> bottomId = createNumber("bottomId", Long.class);

    public final DateTimePath<java.sql.Timestamp> bottomRegisterDt = createDateTime("bottomRegisterDt", java.sql.Timestamp.class);

    public QBottomEntity(String variable) {
        super(BottomEntity.class, forVariable(variable));
    }

    public QBottomEntity(Path<? extends BottomEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBottomEntity(PathMetadata metadata) {
        super(BottomEntity.class, metadata);
    }

}

