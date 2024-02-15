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

    @DeleteMapping("/{id}")
    public Response<Boolean> deleteById(@PathVariable("id") Integer id) {
        return Response.buildResponse(specialtyService.deleteById(id));
    }

    @GetMapping
    public Response<List<SpecialtyDto>> getAllByLimit(@RequestParam("limit") Integer limit) {
        return Response.buildResponse(specialtyService.getAllByLimit(limit));
    }

    @GetMapping("/all")
    public Response<List<SpecialtyDto>> getAllSpecialty() {
        return Response.buildResponse(specialtyService.getAllSpecialty());
    }

    @GetMapping("/{id}")
    public Response<SpecialtyDto> getById(@PathVariable("id") Integer id) {
        return Response.buildResponse(specialtyService.getById(id));
    }

    @GetMapping("/search")
    public Response<List<SpecialtyDto>> search(@RequestParam("keyWord") String keyWord) {
        return Response.buildResponse(specialtyService.search(keyWord));
    }
}
