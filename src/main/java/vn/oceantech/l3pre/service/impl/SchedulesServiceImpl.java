package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.consts.Constants;
import vn.oceantech.l3pre.dto.SchedulesDto;
import vn.oceantech.l3pre.dto.UserDto;
import vn.oceantech.l3pre.entity.Schedules;
import vn.oceantech.l3pre.entity.User;
import vn.oceantech.l3pre.projection.SchedulesPro;
import vn.oceantech.l3pre.repository.SchedulesRepo;
import vn.oceantech.l3pre.repository.UserRepo;
import vn.oceantech.l3pre.service.SchedulesService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulesServiceImpl implements SchedulesService {
    private final SchedulesRepo schedulesRepo;
    private final UserRepo userRepo;

    public List<SchedulesDto> create(List<Schedules> schedules) throws ParseException {
        List<SchedulesDto> schedulesDtos = new ArrayList<>();
        for (Schedules schedule : schedules) {
            schedule.setMaxNumber(Constants.MAX_NUMBER_SCHEDULES);
            if (schedulesRepo.existsSchedule(schedule.getTimeType(), schedule.getDoctor().getId(),
                    formatDate(schedule.getDate())) != 1) {
                schedulesRepo.save(schedule);
            }
            SchedulesDto schedulesDto = new ModelMapper().map(schedule, SchedulesDto.class);
            schedulesDtos.add(schedulesDto);
        }
        return schedulesDtos;
    }

    public List<SchedulesDto> getSchedulesDoctorByDate(Integer doctorId, Date date) {
        List<SchedulesDto> schedulesDtos = new ArrayList<>();
        List<SchedulesPro> schedulesPros = schedulesRepo.getSchedulesDoctorByDate(doctorId, date);
        for (SchedulesPro schedulesPro : schedulesPros) {
            User doctor = userRepo.getById(schedulesPro.getDoctorId());
            UserDto doctorDto = new ModelMapper().map(doctor, UserDto.class);
            SchedulesDto schedulesDto = new SchedulesDto(
                    schedulesPro.getId(),
                    schedulesPro.getCurrentNumber(),
                    schedulesPro.getMaxNumber(),
                    schedulesPro.getDate(),
                    schedulesPro.getTimeType(),
                    doctorDto,
                    schedulesPro.getCreatedAt(),
                    schedulesPro.getValueEn(),
                    schedulesPro.getValueVi(),
                    schedulesPro.getValue());
            schedulesDtos.add(schedulesDto);
        }
        return schedulesDtos;
    }

    private Date formatDate(Date date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = dateFormat.format(date);
        return dateFormat.parse(formatDate);
    }
}
