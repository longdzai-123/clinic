package vn.oceantech.l3pre.service;

import vn.oceantech.l3pre.dto.BookingDto;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    BookingDto create(BookingDto bookingDto);

    BookingDto update(BookingDto bookingDto);

    BookingDto confirmBooking(String token, Integer doctorId);

    List<BookingDto> getPatientByDoctorAndDate(int doctorId, LocalDate Date);

    BookingDto getById(int id);
}
