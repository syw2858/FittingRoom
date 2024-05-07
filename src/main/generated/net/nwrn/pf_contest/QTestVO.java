package net.nwrn.pf_contest;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTestVO is a Querydsl query type for TestVO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTestVO extends EntityPathBase<TestVO> {

    private static final long serialVersionUID = 683568630L;

    public static final QTestVO testVO = new QTestVO("testVO");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QTestVO(String variable) {
        super(TestVO.class, forVariable(variable));
    }

    public QTestVO(Path<? extends TestVO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTestVO(PathMetadata metadata) {
        super(TestVO.class, metadata);
    }

}

