package project.toy.repository.reserve;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import project.toy.apiPayload.code.status.ErrorStatus;
import project.toy.apiPayload.exception.handler.ReserveHandler;
import project.toy.domain.Doctor;
import project.toy.domain.Patient;
import project.toy.domain.QReserve;
import project.toy.domain.Reserve;
import project.toy.domain.enums.ReserveStatus;

import java.util.List;

@RequiredArgsConstructor
public class ReserveRepositoryImpl implements ReserveRepositoryCustom{

    private static final int NOT_COMPLETE = 1;
    private static final int COMPLETE = 2;

    private final JPAQueryFactory jpaQueryFactory;
    private final QReserve reserve = QReserve.reserve;

    @Override
    public Page<Reserve> findAllByPatientWithStatus(Patient patient, Integer statusFilter, Doctor doctor, PageRequest pageRequest) {

        BooleanExpression patientExp = createPatientDynamicExp(patient);
        BooleanExpression doctorDynamicExp = createDoctorDynamicExp(doctor);
        return dynamicQuery(patientExp, doctorDynamicExp, statusFilter, pageRequest);
    }

    private BooleanExpression createDoctorDynamicExp(Doctor doctor) {
        return doctor == null ? null : reserve.doctor.eq(doctor);
    }

    @Override
    public Page<Reserve> findAllByDoctorWithStatus(Doctor doctor, Integer statusFilter, Patient patient, PageRequest pageRequest) {
        BooleanExpression doctorExp = createDoctorDynamicExp(doctor);
        BooleanExpression patientDynamicExp = createPatientDynamicExp(patient);
        return dynamicQuery(doctorExp, patientDynamicExp, statusFilter, pageRequest);
    }

    private BooleanExpression createPatientDynamicExp(Patient patient) {
        return patient == null ? null : reserve.patient.eq(patient);
    }

    private Page<Reserve> dynamicQuery(BooleanExpression exp, BooleanExpression nameDynamicExp, Integer statusFilter, PageRequest pageRequest) {
        BooleanExpression statusExp = determineStatus(statusFilter);

        Long listSize = jpaQueryFactory
                .select(reserve.count())
                .from(reserve)
                .where(exp, nameDynamicExp, statusExp)
                .fetchOne();

        listSize = (listSize == null ? 0L : listSize);
        //조건에 맞는 데이터가 하나도 없을 경우 null이 발생해서 NullPointerException 발생. 따라서 null 인경우 0L로 바꿔주기

        List<Reserve> reserveList = jpaQueryFactory
                .selectFrom(reserve)
                .where(exp, nameDynamicExp, statusExp)
                .orderBy(reserve.treatmentTime.desc())
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();
        return new PageImpl<>(reserveList, pageRequest, listSize);
    }

    private BooleanExpression determineStatus(Integer statusFilter) {
        if (statusFilter == null) {
            return null;
        }

        return switch (statusFilter) {
            case NOT_COMPLETE -> reserve.status.in(ReserveStatus.WALK_IN, ReserveStatus.RESERVE);
            case COMPLETE -> reserve.status.eq(ReserveStatus.TREATMENT);
            default -> throw new ReserveHandler(ErrorStatus.RESERVE_STATUS_NOTFOUND);
        };
    }
}
