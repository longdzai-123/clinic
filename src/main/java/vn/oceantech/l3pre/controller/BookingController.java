package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import vn.oceantech.l3pre.common.Response;
import vn.oceantech.l3pre.dto.BookingDto;
import vn.oceantech.l3pre.service.BookingService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public Response<BookingDto> create(@RequestBody @Valid BookingDto bookingDto) {
        return Response.buildResponse(bookingService.create(bookingDto));
    }

    @PutMapping
    public Response<BookingDto> update(@RequestBody BookingDto bookingDto) {
        return Response.buildResponse(bookingService.update(bookingDto));
    }

    @PostMapping("/verify-booking-appointment")
    public Response<BookingDto> confirmBooking(@RequestParam("token") String token, @RequestParam("doctorId") Integer doctorId) {
        return Response.buildResponse(bookingService.confirmBooking(token, doctorId));
    }

    @DeleteMapping("/{id}")
    public Response<Boolean> deleteById(@PathVariable("id") Integer id) {
        return Response.buildResponse(bookingService.deleteById(id));
    }

    @GetMapping
    public Response<List<BookingDto>> getPatientByDoctorAndDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                                @RequestParam("doctorId") Integer doctorId) {
        return Response.buildResponse(bookingService.getPatientByDoctorAndDate(doctorId, date));
    }

    @GetMapping("/{id}")
    public Response<BookingDto> getById(@PathVariable("id") Integer id) {
        return Response.buildResponse(bookingService.getById(id));
    }
}
