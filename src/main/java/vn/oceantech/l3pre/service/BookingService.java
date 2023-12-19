package vn.oceantech.l3pre.service;

import vn.oceantech.l3pre.dto.BookingDto;
import vn.oceantech.l3pre.entity.Booking;

import java.util.Map;

public interface BookingService {
    BookingDto create(BookingDto bookingDto);

    BookingDto update(BookingDto bookingDto);
}
