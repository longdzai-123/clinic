package vn.oceantech.l3pre.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.oceantech.l3pre.repository.DoctorInforRepo;
import vn.oceantech.l3pre.entity.DoctorInformation;
import vn.oceantech.l3pre.exception.DuplicateException;
import vn.oceantech.l3pre.exception.ErrorMessage;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DoctorInforValidator {
    private final DoctorInforRepo doctorInforRepo;

    public void existDoctorInfor(int doctorId) {
        DoctorInformation doctorInformation = doctorInforRepo.getByDoctorId(doctorId);
        if (!Objects.isNull(doctorInformation)) {
            throw new DuplicateException(ErrorMessage.DUPLICATE_DOCTOR_INFORMATION);
        }
    }
}
