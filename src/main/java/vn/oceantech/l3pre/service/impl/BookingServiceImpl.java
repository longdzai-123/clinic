package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.dto.AllCodesDto;
import vn.oceantech.l3pre.dto.BookingDto;
import vn.oceantech.l3pre.dto.UserDto;
import vn.oceantech.l3pre.email.EmailSender;
import vn.oceantech.l3pre.entity.Booking;
import vn.oceantech.l3pre.entity.User;
import vn.oceantech.l3pre.exception.ConfirmBookingException;
import vn.oceantech.l3pre.exception.ErrorMessage;
import vn.oceantech.l3pre.repository.AllCodesRepo;
import vn.oceantech.l3pre.repository.BookingRepo;
import vn.oceantech.l3pre.repository.UserRepo;
import vn.oceantech.l3pre.service.BookingService;
import vn.oceantech.l3pre.service.UserService;
import vn.oceantech.l3pre.validation.BookingValidator;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepo bookingRepo;
    private final UserService userService;
    private final UserRepo userRepo;
    private final EmailSender sendEmail;
    private final BookingValidator bookingValidator;
    private final AllCodesRepo allCodesRepo;

    @Override
    public BookingDto create(BookingDto bookingDto) {
        if (!userRepo.existsByEmail(bookingDto.getEmail())) {
            UserDto userDto = new UserDto();
            userDto.setEmail(bookingDto.getEmail());
            userDto.setRoleId("R3"); // set role patient
            userDto.setFirstName(bookingDto.getPatientName());
            bookingDto.setPatient(userService.managerCreateUser(userDto));
        } else {
            User user = userRepo.getPatientIdByEmail(bookingDto.getEmail());
            UserDto userDto = new ModelMapper().map(user, UserDto.class);
            bookingDto.setPatient(userDto);
        }
        Booking booking = new Booking();
        this.mapDtoToEntity(bookingDto, booking);
        booking.setCreatedAt(LocalDateTime.now());
        booking.setStatusId("S1");
        String verifyBooking = UUID.randomUUID().toString();
        booking.setVerifyBooking(verifyBooking);
        bookingValidator.checkDuplicateBooking(booking);
        bookingRepo.save(booking);
        User doctor = userRepo.getById(bookingDto.getDoctor().getId());
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
            booking.setTimeType(bookingDto.getTimeType().getKeyMap());
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
            booking.setPatientGender(bookingDto.getPatientGender().getKeyMap());
        }
        if (bookingDto.getPatientBirthday() != null) {
            booking.setPatientBirthday(bookingDto.getPatientBirthday());
        }
        if (bookingDto.getImageRemedy() != null) {
            booking.setImageRemedy(bookingDto.getImageRemedy());
        }
        if (bookingDto.getIsRemedy() != null) {
            booking.setIsRemedy(bookingDto.getIsRemedy());
        }
        bookingRepo.save(booking);
        BookingDto bookingDtoRes = new BookingDto();
        this.mapEntityToDto(booking, bookingDtoRes);
        return bookingDtoRes;
    }

    @Override
    public boolean deleteById(int id) {
        bookingRepo.deleteById(id);
        return true;
    }

    @Override
    public BookingDto confirmBooking(String token, Integer doctorId) {
        if (token != null && doctorId != null) {
            if (bookingRepo.existsByDoctorIdAndToken(token, doctorId) == 1) {
                Booking booking = bookingRepo.getByToken(token);
                booking.setStatusId("S2");
                bookingRepo.save(booking);
                BookingDto bookingDto = new BookingDto();
                this.mapEntityToDto(booking, bookingDto);
                return bookingDto;
            } else {
                throw new ConfirmBookingException(ErrorMessage.CONFIRM_BOOKING_ERROR);
            }
        } else {
            throw new ConfirmBookingException(ErrorMessage.CONFIRM_BOOKING_ERROR);
        }
    }

    @Override
    public List<BookingDto> getPatientByDoctorAndDate(int doctorId, LocalDate date) {
        List<Booking> bookings = bookingRepo.getPatientByDoctorAndDate(doctorId, date);
        List<BookingDto> bookingDtos = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingDto bookingDto = new BookingDto();
            this.mapEntityToDto(booking, bookingDto);
            bookingDtos.add(bookingDto);
        }
        return bookingDtos;
    }

    @Override
    public BookingDto getById(int id) {
        Booking booking = bookingRepo.getById(id);
        BookingDto bookingDto = new BookingDto();
        this.mapEntityToDto(booking, bookingDto);
        return bookingDto;
    }

    private void mapDtoToEntity(BookingDto bookingDto, Booking booking) {
        if (bookingDto.getDoctor() != null) {
            User doctor = new User();
            doctor.setId(bookingDto.getDoctor().getId());
            booking.setDoctor(doctor);
        }
        if (bookingDto.getPatient() != null) {
            User patient = new User();
            patient.setId(bookingDto.getPatient().getId());
            booking.setPatient(patient);
        }
        booking.setDate(bookingDto.getDate());
        booking.setTimeType(bookingDto.getTimeType().getKeyMap());
        booking.setImageRemedy(bookingDto.getImageRemedy());
        booking.setPatientName(bookingDto.getPatientName());
        booking.setPatientPhoneNumber(bookingDto.getPatientPhoneNumber());
        booking.setPatientAddress(bookingDto.getPatientAddress());
        booking.setPatientReason(bookingDto.getPatientReason());
        booking.setIsRemedy(bookingDto.getIsRemedy());
        if (bookingDto.getTimeType() != null) {
            booking.setPatientGender(bookingDto.getPatientGender().getKeyMap());
        }
        booking.setPatientBirthday(bookingDto.getPatientBirthday());
    }

    private void mapEntityToDto(Booking booking, BookingDto bookingDto) {
        bookingDto.setId(booking.getId());
        bookingDto.setStatusId(booking.getStatusId());

        UserDto doctor = new ModelMapper().map(booking.getDoctor(), UserDto.class);
        bookingDto.setDoctor(doctor);

        UserDto patient = new ModelMapper().map(booking.getPatient(), UserDto.class);
        bookingDto.setPatient(patient);

        bookingDto.setDate(booking.getDate());

        AllCodesDto timeType = new ModelMapper().map(allCodesRepo.getByKeyMap(booking.getTimeType()), AllCodesDto.class);
        bookingDto.setTimeType(timeType);

        bookingDto.setImageRemedy(booking.getImageRemedy());
        bookingDto.setPatientName(booking.getPatientName());
        bookingDto.setPatientPhoneNumber(booking.getPatientPhoneNumber());
        bookingDto.setPatientAddress(booking.getPatientAddress());
        bookingDto.setPatientReason(booking.getPatientReason());
        bookingDto.setIsRemedy(booking.getIsRemedy());

        AllCodesDto patientGender = new ModelMapper().map(allCodesRepo.getByKeyMap(booking.getPatientGender()), AllCodesDto.class);
        bookingDto.setPatientGender(patientGender);
        bookingDto.setPatientBirthday(booking.getPatientBirthday());
    }
}
