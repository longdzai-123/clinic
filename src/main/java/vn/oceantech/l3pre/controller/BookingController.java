package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.oceantech.l3pre.common.Response;
import vn.oceantech.l3pre.dto.BookingDto;
import vn.oceantech.l3pre.service.BookingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public Response<BookingDto> create(@RequestBody BookingDto bookingDto) {
        return Response.buildResponse(bookingService.create(bookingDto));
    }

    @PutMapping
    public Response<BookingDto> update(@RequestBody BookingDto bookingDto) {
        return Response.buildResponse(bookingService.update(bookingDto));
    }
}
