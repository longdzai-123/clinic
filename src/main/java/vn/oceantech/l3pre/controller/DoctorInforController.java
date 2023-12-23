package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.oceantech.l3pre.common.Response;
import vn.oceantech.l3pre.dto.DoctorExtraInformationDto;
import vn.oceantech.l3pre.dto.DoctorInformationDetailDto;
import vn.oceantech.l3pre.dto.DoctorInformationDto;
import vn.oceantech.l3pre.dto.DoctorProfileDto;
import vn.oceantech.l3pre.service.DoctorInforService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctor-infor")
public class DoctorInforController {
    private final DoctorInforService doctorInforService;

    @PostMapping
    public Response<DoctorInformationDto> create(@RequestBody DoctorInformationDto doctorInformationDto) {
        return Response.buildResponse(doctorInforService.create(doctorInformationDto));
    }

    @PutMapping
    public Response<DoctorInformationDto> update(@RequestBody DoctorInformationDto doctorInformationDto) {
        return Response.buildResponse(doctorInforService.update(doctorInformationDto));
    }

    @GetMapping("/{id}")
    public Response<DoctorInformationDetailDto> getDoctorInforDetailByDoctorId(@PathVariable("id") int doctorId) {
        return Response.buildResponse(doctorInforService.getDoctorInforDetailByDoctorId(doctorId));
    }

    @GetMapping("/extra/{id}")
    public Response<DoctorExtraInformationDto> getDoctorExtraInforByDoctorId(@PathVariable("id") int doctorId) {
        return Response.buildResponse(doctorInforService.getDoctorExtraInforByDoctorId(doctorId));
    }

    @GetMapping("/profile/{id}")
    public Response<DoctorProfileDto> getDoctorProfileByDoctorId(@PathVariable("id") int doctorId) {
        return Response.buildResponse(doctorInforService.getDoctorProfileByDoctorId(doctorId));
    }

    @GetMapping("/get-by-specialty")
    public Response<List<DoctorInformationDto>> getBySpecialtyAndProvince(@RequestParam("specialtyId") Integer specialtyId,
                                                                          @RequestParam("provinceId") String provinceId) {
        return Response.buildResponse(doctorInforService.getBySpecialtyAndProvince(specialtyId, provinceId));
    }


//    @GetMapping("/getBy/{id}")
//    public Response<DoctorInformationDto> getByDoctorId(@PathVariable("id") int doctorId){
//        return Response.buildResponse(doctorInforService.getByDoctorId(doctorId));
//    }


}
