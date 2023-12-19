package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.oceantech.l3pre.common.Response;
import vn.oceantech.l3pre.dto.DrugDto;
import vn.oceantech.l3pre.service.DrugService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/drugs")
public class DrugController {
    private final DrugService drugService;

    @PostMapping
    public Response<DrugDto> create(@RequestBody DrugDto drugDto) {
        return Response.buildResponse(drugService.create(drugDto));
    }
}
