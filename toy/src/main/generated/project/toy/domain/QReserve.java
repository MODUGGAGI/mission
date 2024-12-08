package project.toy.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReserve is a Querydsl query type for Reserve
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReserve extends EntityPathBase<Reserve> {

    private static final long serialVersionUID = -501118729L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReserve reserve = new QReserve("reserve");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final QDoctor doctor;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPatient patient;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final EnumPath<project.toy.domain.enums.ReserveStatus> status = createEnum("status", project.toy.domain.enums.ReserveStatus.class);

    public QReserve(String variable) {
        this(Reserve.class, forVariable(variable), INITS);
    }

    public QReserve(Path<? extends Reserve> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReserve(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReserve(PathMetadata metadata, PathInits inits) {
        this(Reserve.class, metadata, inits);
    }

    public QReserve(Class<? extends Reserve> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.doctor = inits.isInitialized("doctor") ? new QDoctor(forProperty("doctor"), inits.get("doctor")) : null;
        this.patient = inits.isInitialized("patient") ? new QPatient(forProperty("patient"), inits.get("patient")) : null;
    }

}

