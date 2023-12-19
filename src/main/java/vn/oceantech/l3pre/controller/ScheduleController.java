package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import vn.oceantech.l3pre.common.Response;
import vn.oceantech.l3pre.dto.SchedulesDto;
import vn.oceantech.l3pre.entity.Schedules;
import vn.oceantech.l3pre.service.SchedulesService;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final SchedulesService schedulesService;

    @PostMapping
    public Response<List<SchedulesDto>> create(@RequestBody List<Schedules> schedules) throws ParseException {
        return Response.buildResponse(schedulesService.create(schedules));
    }

    @GetMapping("/get-schedule-doctor-by-date")
    public Response<List<SchedulesDto>> getSchedulesDoctorByDate(@RequestParam("doctorId") Integer doctorId,
                                                                 @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return Response.buildResponse(schedulesService.getSchedulesDoctorByDate(doctorId, date));
    }
}
