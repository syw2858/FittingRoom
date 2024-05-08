package net.nwrn.pf_contest.person.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPersonEntity is a Querydsl query type for PersonEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPersonEntity extends EntityPathBase<PersonEntity> {

    private static final long serialVersionUID = -1681892523L;

    public static final QPersonEntity personEntity = new QPersonEntity("personEntity");

    public final NumberPath<Long> personImageId = createNumber("personImageId", Long.class);

    public final StringPath personImageUrl = createString("personImageUrl");

    public QPersonEntity(String variable) {
        super(PersonEntity.class, forVariable(variable));
    }

    public QPersonEntity(Path<? extends PersonEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPersonEntity(PathMetadata metadata) {
        super(PersonEntity.class, metadata);
    }

}

