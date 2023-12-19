package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.oceantech.l3pre.common.Response;
import vn.oceantech.l3pre.dto.LimitBookingDto;
import vn.oceantech.l3pre.service.LimitBookingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/limit-bookings")
public class LimitBookingController {
    private final LimitBookingService limitBookingService;

    @PostMapping
    public Response<LimitBookingDto> create(@RequestBody LimitBookingDto limitBookingDto){
        return Response.buildResponse(limitBookingService.create(limitBookingDto));
    }



}
