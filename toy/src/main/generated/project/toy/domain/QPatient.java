package project.toy.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPatient is a Querydsl query type for Patient
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPatient extends EntityPathBase<Patient> {

    private static final long serialVersionUID = 1905354560L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPatient patient = new QPatient("patient");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final EnumPath<project.toy.domain.enums.Gender> gender = createEnum("gender", project.toy.domain.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final project.toy.domain.embeddable.QPhoneNum phoneNum;

    public final ListPath<Reserve, QReserve> reserveList = this.<Reserve, QReserve>createList("reserveList", Reserve.class, QReserve.class, PathInits.DIRECT2);

    public QPatient(String variable) {
        this(Patient.class, forVariable(variable), INITS);
    }

    public QPatient(Path<? extends Patient> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPatient(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPatient(PathMetadata metadata, PathInits inits) {
        this(Patient.class, metadata, inits);
    }

    public QPatient(Class<? extends Patient> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.phoneNum = inits.isInitialized("phoneNum") ? new project.toy.domain.embeddable.QPhoneNum(forProperty("phoneNum")) : null;
    }

}

