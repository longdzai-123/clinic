package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.oceantech.l3pre.common.Response;
import vn.oceantech.l3pre.dto.DoctorClinicSpecialtyDto;
import vn.oceantech.l3pre.service.DoctorClinicSpecialtyService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctor-clinic-specialty")
public class DoctorClinicSpecialtyController {
    private final DoctorClinicSpecialtyService doctorClinicSpecialtyService;

    @PostMapping
    public Response<DoctorClinicSpecialtyDto> create(@RequestBody @Valid DoctorClinicSpecialtyDto doctorClinicSpecialtyDto) {
        return Response.buildResponse(doctorClinicSpecialtyService.create(doctorClinicSpecialtyDto));
    }
}
