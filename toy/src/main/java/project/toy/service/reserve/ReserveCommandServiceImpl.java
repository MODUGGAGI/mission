package project.toy.service.reserve;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.toy.apiPayload.code.status.ErrorStatus;
import project.toy.apiPayload.exception.handler.DoctorHandler;
import project.toy.apiPayload.exception.handler.PatientHandler;
import project.toy.apiPayload.exception.handler.ReserveHandler;
import project.toy.converter.ReserveConverter;
import project.toy.domain.Doctor;
import project.toy.domain.Patient;
import project.toy.domain.Reserve;
import project.toy.repository.DoctorRepository;
import project.toy.repository.PatientRepository;
import project.toy.repository.ReserveRepository;
import project.toy.web.dto.reserve.ReserveRequestDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReserveCommandServiceImpl implements ReserveCommandService{

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ReserveRepository reserveRepository;
    @Override
    @Transactional
    public Reserve joinReserve(ReserveRequestDto.ReserveJoinDTO request) {

        if (reserveRepository.existsByDoctorIdAndTreatmentTime(request.getDoctorId(), request.getTreatmentTime())) {
            throw new ReserveHandler(ErrorStatus.RESERVE_ALREADY_EXISTS);
            //해당 의사에게 해당 시간대에 예약시간이 비어있는지 확인
        }

        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new DoctorHandler(ErrorStatus.DOCTOR_NOT_FOUND));
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new PatientHandler(ErrorStatus.PATIENT_NOT_FOUND));

        Reserve reserve = ReserveConverter.toReserve(request, patient, doctor);
        //이 컨버터 내부 메소드에서 빌더 패턴으로 생성자 호출할 때 생성자 내부에서 patient와 doctor에 대해 양방향 연관관계 설정완료

        return reserveRepository.save(reserve);
    }
}
