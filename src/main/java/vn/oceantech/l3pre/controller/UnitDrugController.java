package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.oceantech.l3pre.common.Response;
import vn.oceantech.l3pre.dto.UnitDrugDto;
import vn.oceantech.l3pre.service.UnitDrugService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/unit-drug")
public class UnitDrugController {
    private final UnitDrugService unitDrugService;

    @PostMapping
    public Response<UnitDrugDto> create(@RequestBody UnitDrugDto unitDrugDto) {
        return Response.buildResponse(unitDrugService.create(unitDrugDto));
    }

    @GetMapping
    public Response<List<UnitDrugDto>> getAll() {
        return Response.buildResponse(unitDrugService.getAll());
    }

}
