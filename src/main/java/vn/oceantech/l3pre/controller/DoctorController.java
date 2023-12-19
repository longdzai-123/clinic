package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.oceantech.l3pre.common.Response;
import vn.oceantech.l3pre.dto.UserDto;
import vn.oceantech.l3pre.service.ProcedureService.DoctorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping("/get-top-doctor-home")
    public Response<List<UserDto>> getTopDoctorHome(@RequestParam("size") int size) {
        return Response.buildResponse(doctorService.getTopDoctorHome(size));
    }

    @GetMapping("/get-all-doctor")
    public Response<List<UserDto>> getAllDoctor() {
        return Response.buildResponse(doctorService.getAllDoctor());
    }

    @GetMapping("/get-details-all-doctor")
    public Response<List<UserDto>> getDetailsAllDoctor() {
        return Response.buildResponse(doctorService.getDetailsAllDoctor());
    }

    @GetMapping("/get-details-doctor/{id}")
    public Response<UserDto> getDetailsAllDoctor(@PathVariable("id") int id) {
        return Response.buildResponse(doctorService.getDetailsDoctorById(id));
    }

}
