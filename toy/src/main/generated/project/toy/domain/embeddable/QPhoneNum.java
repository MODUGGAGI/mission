package project.toy.domain.embeddable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPhoneNum is a Querydsl query type for PhoneNum
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QPhoneNum extends BeanPath<PhoneNum> {

    private static final long serialVersionUID = 2065224316L;

    public static final QPhoneNum phoneNum1 = new QPhoneNum("phoneNum1");

    public final StringPath phoneNum = createString("phoneNum");

    public QPhoneNum(String variable) {
        super(PhoneNum.class, forVariable(variable));
    }

    public QPhoneNum(Path<? extends PhoneNum> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPhoneNum(PathMetadata metadata) {
        super(PhoneNum.class, metadata);
    }

}

