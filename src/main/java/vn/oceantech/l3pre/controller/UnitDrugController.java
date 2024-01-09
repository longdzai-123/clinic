package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.oceantech.l3pre.common.Response;
import vn.oceantech.l3pre.dto.UnitDrugDto;
import vn.oceantech.l3pre.service.UnitDrugService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/unit-drug")
public class UnitDrugController {
    private final UnitDrugService unitDrugService;

    @PostMapping
    public Response<UnitDrugDto> create(@RequestBody UnitDrugDto unitDrugDto) {
        return Response.buildResponse(unitDrugService.create(unitDrugDto));
    }
}
