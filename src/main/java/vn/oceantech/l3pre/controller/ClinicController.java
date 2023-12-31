package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.oceantech.l3pre.common.Response;
import vn.oceantech.l3pre.dto.ClinicDto;
import vn.oceantech.l3pre.service.ClinicService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clinic")
public class ClinicController {
    private final ClinicService clinicService;

    @GetMapping
    public Response<List<ClinicDto>> getAll() {
        return Response.buildResponse(clinicService.getAll());
    }

    @PostMapping
    public Response<ClinicDto> create(@RequestBody ClinicDto clinicDto) {
        return Response.buildResponse(clinicService.create(clinicDto));
    }

    @PutMapping
    public Response<ClinicDto> update(@RequestBody ClinicDto clinicDto) {
        return Response.buildResponse(clinicService.update(clinicDto));
    }

    @GetMapping("/{id}")
    public Response<ClinicDto> getById(@PathVariable("id") Integer id) {
        return Response.buildResponse(clinicService.getById(id));
    }

}
