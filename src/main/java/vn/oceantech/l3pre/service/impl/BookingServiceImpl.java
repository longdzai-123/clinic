package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.dto.BookingDto;
import vn.oceantech.l3pre.dto.UserDto;
import vn.oceantech.l3pre.email.EmailSender;
import vn.oceantech.l3pre.entity.Booking;
import vn.oceantech.l3pre.entity.User;
import vn.oceantech.l3pre.exception.ConfirmBookingException;
import vn.oceantech.l3pre.exception.ErrorMessage;
import vn.oceantech.l3pre.repository.BookingRepo;
import vn.oceantech.l3pre.repository.UserRepo;
import vn.oceantech.l3pre.service.BookingService;
import vn.oceantech.l3pre.service.UserService;
import vn.oceantech.l3pre.validation.BookingValidator;

import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepo bookingRepo;
    private final UserService userService;
    private final UserRepo userRepo;
    private final EmailSender sendEmail;
    private final BookingValidator bookingValidator;

    @Override
    public BookingDto create(BookingDto bookingDto) {
        if (!userRepo.existsByEmail(bookingDto.getEmail())) {
            UserDto userDto = new UserDto();
            userDto.setEmail(bookingDto.getEmail());
            userDto.setRoleId("R3"); // set role patient
            userDto.setFirstName(bookingDto.getPatientName());
            int patientId = userService.create(userDto).getId();
            bookingDto.setPatientId(patientId);
        } else {
            bookingDto.setPatientId(userRepo.getPatientIdByEmail(bookingDto.getEmail()));
        }
        Booking booking = new ModelMapper().map(bookingDto, Booking.class);
        booking.setCreatedAt(LocalDateTime.now());
        booking.setStatusId("S1");
        String verifyBooking = UUID.randomUUID().toString();
        booking.setVerifyBooking(verifyBooking);
        bookingValidator.checkDuplicateBooking(booking);
        bookingRepo.save(booking);
        User doctor = userRepo.getById(bookingDto.getDoctorId());
        String doctorName = doctor.getLastName() + " " + doctor.getFirstName();
        bookingDto.setId(booking.getId());
        bookingDto.setVerifyBooking(verifyBooking);
        sendEmail.sendEmailBooking(bookingDto, doctorName);
        return bookingDto;
    }

    @Override
    public BookingDto update(BookingDto bookingDto) {
        Booking booking = bookingRepo.findById(bookingDto.getId()).orElseThrow(NoResultException::new);
        if (bookingDto.getStatusId() != null) {
            booking.setStatusId(bookingDto.getStatusId());
        }
        if (bookingDto.getDate() != null) {
            booking.setDate(bookingDto.getDate());
        }
        if (bookingDto.getTimeType() != null) {
            booking.setTimeType(bookingDto.getTimeType());
        }
        if (bookingDto.getPatientName() != null) {
            booking.setPatientPhoneNumber(bookingDto.getPatientName());
        }
        if (bookingDto.getPatientPhoneNumber() != null) {
            booking.setPatientPhoneNumber(bookingDto.getPatientPhoneNumber());
        }
        if (bookingDto.getPatientAddress() != null) {
            booking.setPatientAddress(bookingDto.getPatientAddress());
        }
        if (bookingDto.getPatientReason() != null) {
            booking.setPatientReason(bookingDto.getPatientReason());
        }
        if (bookingDto.getPatientGender() != null) {
            booking.setPatientGender(bookingDto.getPatientGender());
        }
        if (bookingDto.getPatientBirthday() != null) {
            booking.setPatientBirthday(bookingDto.getPatientBirthday());
        }
        bookingRepo.save(booking);
        return new ModelMapper().map(booking, BookingDto.class);
    }

    @Override
    public BookingDto confirmBooking(String token, Integer doctorId) {
        if (token != null && doctorId != null) {
            if (bookingRepo.existsByDoctorIdAndToken(token, doctorId)) {
                Booking booking = bookingRepo.getByToken(token);
                booking.setStatusId("S2");
                bookingRepo.save(booking);
                return new ModelMapper().map(booking, BookingDto.class);
            } else {
                throw new ConfirmBookingException(ErrorMessage.CONFIRM_BOOKING_ERROR);
            }
        } else {
            throw new ConfirmBookingException(ErrorMessage.CONFIRM_BOOKING_ERROR);
        }
    }
}
