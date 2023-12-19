package vn.oceantech.l3pre.service;

import vn.oceantech.l3pre.dto.SchedulesDto;
import vn.oceantech.l3pre.entity.Schedules;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


public interface SchedulesService {
    List<SchedulesDto> create(List<Schedules> schedules) throws ParseException;

    List<SchedulesDto> getSchedulesDoctorByDate(Integer doctorId, Date date);
}
