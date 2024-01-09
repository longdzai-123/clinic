package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.oceantech.l3pre.common.Response;
import vn.oceantech.l3pre.dto.RemedyDto;
import vn.oceantech.l3pre.service.RemedyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/remedy")
public class RemedyController {
    private final RemedyService remedyService;

    @PostMapping
    public Response<RemedyDto> create(@RequestBody RemedyDto remedyDto) {
        return Response.buildResponse(remedyService.create(remedyDto));
    }
}
