package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.oceantech.l3pre.common.Response;
import vn.oceantech.l3pre.dto.Response.DoctorResponseDto;
import vn.oceantech.l3pre.dto.UserProDto;
import vn.oceantech.l3pre.service.DoctorInforService;
import vn.oceantech.l3pre.service.ProcedureService.DoctorService;
import vn.oceantech.l3pre.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctor")
public class DoctorController {
    private final DoctorService doctorService;
    private final DoctorInforService doctorInforService;
    private final UserService userService;


    @GetMapping("/get-top-doctor-home")
    public Response<List<DoctorResponseDto>> getTopDoctorHome(@RequestParam("size") int size) {
        return Response.buildResponse(doctorInforService.getTopDoctorHome(size));
    }

    @GetMapping("/get-all-doctor")
    public Response<List<DoctorResponseDto>> getAllDoctor() {
        return Response.buildResponse(doctorInforService.getAllDoctor());
    }

    @GetMapping("/get-details-all-doctor")
    public Response<List<UserProDto>> getDetailsAllDoctor() {
        return Response.buildResponse(doctorService.getDetailsAllDoctor());
    }

    @GetMapping("/get-details-doctor/{id}")
    public Response<UserProDto> getDetailsAllDoctor(@PathVariable("id") int id) {
        return Response.buildResponse(doctorService.getDetailsDoctorById(id));
    }

}
