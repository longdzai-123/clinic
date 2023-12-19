package vn.oceantech.l3pre.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.oceantech.l3pre.repository.SchedulesRepo;
import vn.oceantech.l3pre.entity.Schedules;
import vn.oceantech.l3pre.exception.DuplicateException;
import vn.oceantech.l3pre.exception.ErrorMessage;

import java.util.Date;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SchedulesValidator {
    private final SchedulesRepo schedulesRepo;

    public void existSchedule(String timeType, Integer doctorId, Date date) {
        Schedules schedule = schedulesRepo.getByTimeTypeAndDoctorId(timeType, doctorId, date);
        if (!Objects.isNull(schedule)) {
            throw new DuplicateException(ErrorMessage.DUPLICATE_SCHEDULES);
        }
    }
}
