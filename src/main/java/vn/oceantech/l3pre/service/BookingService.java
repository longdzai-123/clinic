package vn.oceantech.l3pre.service;

import vn.oceantech.l3pre.dto.BookingDto;

public interface BookingService {
    BookingDto create(BookingDto bookingDto);

    BookingDto update(BookingDto bookingDto);

    BookingDto confirmBooking(String token, Integer doctorId);
}
