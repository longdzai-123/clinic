package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
