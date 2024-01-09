package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.oceantech.l3pre.dto.BookingDto;
import vn.oceantech.l3pre.dto.RemedyDto;
import vn.oceantech.l3pre.email.SendRemedy;
import vn.oceantech.l3pre.entity.Booking;
import vn.oceantech.l3pre.entity.Remedy;
import vn.oceantech.l3pre.entity.User;
import vn.oceantech.l3pre.exception.ErrorMessage;
import vn.oceantech.l3pre.exception.NotFoundException;
import vn.oceantech.l3pre.repository.BookingRepo;
import vn.oceantech.l3pre.repository.RemedyRepo;
import vn.oceantech.l3pre.repository.UserRepo;
import vn.oceantech.l3pre.service.BookingService;
import vn.oceantech.l3pre.service.RemedyService;

@Service
@RequiredArgsConstructor
public class RemedyServiceImpl implements RemedyService {
    private final RemedyRepo remedyRepo;
    private final BookingService bookingService;
    private final BookingRepo bookingRepo;
    private final SendRemedy sendRemedy;
    private final UserRepo userRepo;

    @Override
    @Transactional
    public RemedyDto create(RemedyDto remedyDto) {
        Remedy remedy = new Remedy();
        this.mapDtoToEntity(remedyDto, remedy);
        remedyRepo.save(remedy);
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(remedyDto.getBooking().getId());
        bookingDto.setImageRemedy(remedyDto.getImage());
        bookingDto.setStatusId("S3");
        bookingService.update(bookingDto);
        if (remedyDto.getImage() != null) {
            sendRemedy.sendRemedyImage(remedyRepo.findById(remedy.getId()).orElseThrow(() ->
                    new NotFoundException(ErrorMessage.NOT_FOUND_REMEDY)));
        }
        sendRemedy.sendRemedy();
        return remedyDto;
    }

    private void mapDtoToEntity(RemedyDto remedyDto, Remedy remedy) {
        remedy.setDate(remedyDto.getDate());
        remedy.setDescription(remedyDto.getDescription());
        remedy.setEmail(remedyDto.getEmail());
        remedy.setImage(remedyDto.getImage());
        remedy.setPhoneNumber(remedyDto.getPhoneNumber());
        remedy.setTimeType(remedyDto.getTimeType());

        Booking booking = bookingRepo.getById(remedyDto.getBooking().getId());
        remedy.setBooking(booking);

        User doctor = userRepo.getById(remedyDto.getDoctor().getId());
        remedy.setDoctor(doctor);

        User patient = userRepo.getById(remedyDto.getPatient().getId());
        remedy.setPatient(patient);
    }
}
