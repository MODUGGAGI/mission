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

    public static final QPatient patient = new QPatient("patient");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final EnumPath<project.toy.domain.enums.Gender> gender = createEnum("gender", project.toy.domain.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<Reserve, QReserve> reserveList = this.<Reserve, QReserve>createList("reserveList", Reserve.class, QReserve.class, PathInits.DIRECT2);

    public QPatient(String variable) {
        super(Patient.class, forVariable(variable));
    }

    public QPatient(Path<? extends Patient> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPatient(PathMetadata metadata) {
        super(Patient.class, metadata);
    }

}

