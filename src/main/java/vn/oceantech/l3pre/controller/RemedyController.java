package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    @PutMapping
    public Response<RemedyDto> update(@RequestBody RemedyDto remedyDto) {
        return Response.buildResponse(remedyService.updateRemedyDetails(remedyDto));
    }

    @GetMapping
    public Response<RemedyDto> getByBookingId(@RequestParam("bookingId") Integer bookingId) {
        return Response.buildResponse(remedyService.getByBookingId(bookingId));
    }
}
