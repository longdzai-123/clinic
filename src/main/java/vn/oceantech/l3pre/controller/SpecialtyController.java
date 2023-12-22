package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.oceantech.l3pre.common.Response;
import vn.oceantech.l3pre.dto.SpecialtyDto;
import vn.oceantech.l3pre.service.SpecialtyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/specialty")
public class SpecialtyController {
    private final SpecialtyService specialtyService;

    @PostMapping
    public Response<SpecialtyDto> create(@RequestBody SpecialtyDto specialtyDto) {
        return Response.buildResponse(specialtyService.create(specialtyDto));
    }

    @PutMapping
    public Response<SpecialtyDto> update(@RequestBody SpecialtyDto specialtyDto) {
        return Response.buildResponse(specialtyService.update(specialtyDto));
    }

    @GetMapping
    public Response<List<SpecialtyDto>> getAll(@RequestParam("limit") Integer limit) {
        return Response.buildResponse(specialtyService.getAll(limit));
    }

    @GetMapping("/all")
    public Response<List<SpecialtyDto>> getAllSpecialty() {
        return Response.buildResponse(specialtyService.getAllSpecialty());
    }
}
