package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.oceantech.l3pre.common.Response;
import vn.oceantech.l3pre.dto.SpecialtyDto;
import vn.oceantech.l3pre.service.SpecialtyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/specialty")
public class SpecialtyController {
    private final SpecialtyService specialtyService;

    @GetMapping
    public Response<List<SpecialtyDto>> getAll() {
        return Response.buildResponse(specialtyService.getAll());
    }
}
