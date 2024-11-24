package project.toy.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDepartment is a Querydsl query type for Department
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDepartment extends EntityPathBase<Department> {

    private static final long serialVersionUID = -1255532233L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDepartment department = new QDepartment("department");

    public final ListPath<Doctor, QDoctor> doctors = this.<Doctor, QDoctor>createList("doctors", Doctor.class, QDoctor.class, PathInits.DIRECT2);

    public final QHospital hospital;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> phone = createNumber("phone", Integer.class);

    public QDepartment(String variable) {
        this(Department.class, forVariable(variable), INITS);
    }

    public QDepartment(Path<? extends Department> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDepartment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDepartment(PathMetadata metadata, PathInits inits) {
        this(Department.class, metadata, inits);
    }

    public QDepartment(Class<? extends Department> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hospital = inits.isInitialized("hospital") ? new QHospital(forProperty("hospital")) : null;
    }

}

