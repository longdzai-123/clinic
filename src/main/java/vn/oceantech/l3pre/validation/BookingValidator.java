package vn.oceantech.l3pre.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.oceantech.l3pre.entity.Booking;
import vn.oceantech.l3pre.exception.DuplicateException;
import vn.oceantech.l3pre.exception.ErrorMessage;
import vn.oceantech.l3pre.repository.BookingRepo;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookingValidator {
    private final BookingRepo bookingRepo;

    public void checkDuplicateBooking(Booking booking) {
        boolean result = bookingRepo.checkDuplicateBooking(booking.getDoctorId(),
                booking.getDate(), booking.getPatientId(), booking.getTimeType());
        if (result) {
            throw new DuplicateException(ErrorMessage.DUPLICATE_BOOKING);
        }

    }
}
