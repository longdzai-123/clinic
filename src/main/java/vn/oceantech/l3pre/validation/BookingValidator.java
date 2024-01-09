package vn.oceantech.l3pre.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.oceantech.l3pre.entity.Booking;
import vn.oceantech.l3pre.exception.DuplicateException;
import vn.oceantech.l3pre.exception.ErrorMessage;
import vn.oceantech.l3pre.repository.BookingRepo;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookingValidator {
    private final BookingRepo bookingRepo;

    public void checkDuplicateBooking(Booking booking) {
        int result = bookingRepo.checkDuplicateBooking(booking.getDoctor().getId(),
                booking.getDate(), booking.getPatient().getId(), booking.getTimeType());
        if (result == 1) {
            throw new DuplicateException(ErrorMessage.DUPLICATE_BOOKING);
        }
    }
}
