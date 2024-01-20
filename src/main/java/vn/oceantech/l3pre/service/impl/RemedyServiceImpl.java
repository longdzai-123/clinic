package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.oceantech.l3pre.dto.*;
import vn.oceantech.l3pre.email.SendRemedy;
import vn.oceantech.l3pre.entity.*;
import vn.oceantech.l3pre.exception.ErrorMessage;
import vn.oceantech.l3pre.exception.NotFoundException;
import vn.oceantech.l3pre.repository.*;
import vn.oceantech.l3pre.service.BookingService;
import vn.oceantech.l3pre.service.InvoiceService;
import vn.oceantech.l3pre.service.RemedyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RemedyServiceImpl implements RemedyService {
    private final RemedyRepo remedyRepo;
    private final DrugRepo drugRepo;
    private final BookingService bookingService;
    private final BookingRepo bookingRepo;
    private final SendRemedy sendRemedy;
    private final UserRepo userRepo;
    private final RemedyDetailsRepo remedyDetailsRepo;
    private final InvoiceService invoiceService;

    @Override
    @Transactional
    public RemedyDto create(RemedyDto remedyDto) {
        Remedy remedy;
        if (remedyRepo.getByBookingId(remedyDto.getBooking().getId()) != null) {
            remedy = remedyRepo.getByBookingId(remedyDto.getBooking().getId());
            invoiceService.createByRemedy(remedy);
        } else {
            remedy = new Remedy();
            this.mapDtoToEntity(remedyDto, remedy);
            remedyRepo.save(remedy);
            invoiceService.createByRemedy(remedy);
        }
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(remedyDto.getBooking().getId());
        bookingDto.setImageRemedy(remedyDto.getImage());
        bookingDto.setStatusId("S3");
        if (remedyDto.getRemedyDetails() != null && remedyDto.getRemedyDetails().size() > 0) {
            for (RemedyDetailsDto remedyDetailsDto : remedyDto.getRemedyDetails()) {
                RemedyDetails remedyDetails = new RemedyDetails();
                this.mapRemedyDetailDtoToEntity(remedyDetailsDto, remedyDetails);
                remedyDetails.setRemedy(remedy);
                remedyDetailsRepo.save(remedyDetails);
            }
            bookingDto.setIsRemedy(true);
        }
        bookingService.update(bookingDto);
        if (remedyDto.getImage() != null) {
            sendRemedy.sendRemedyImage(remedyRepo.findById(remedy.getId()).orElseThrow(() ->
                    new NotFoundException(ErrorMessage.NOT_FOUND_REMEDY)));
        } else {
            sendRemedy.sendRemedy(remedy);
        }
        return remedyDto;
    }

    @Override
    public RemedyDto getByBookingId(Integer bookingId) {
        Remedy remedy = remedyRepo.getByBookingId(bookingId);
        RemedyDto remedyDto = new RemedyDto();
        if (Objects.nonNull(remedy)) {
            this.mapEntityToDto(remedy, remedyDto);
        }
        return remedyDto;
    }

    @Transactional
    @Override
    public RemedyDto updateRemedyDetails(RemedyDto remedyDto) {
        Remedy remedy = remedyRepo.findById(remedyDto.getId()).
                orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_REMEDY));
        if (remedyDto.getDescription() != null) {
            remedy.setDescription(remedyDto.getDescription());
        }
        if (remedyDto.getNote() != null) {
            remedy.setNote(remedyDto.getNote());
        }
        remedyRepo.save(remedy);
        sendRemedy.sendRemedy(remedy);
        remedyDetailsRepo.deleteByRemedyId(remedyDto.getId());
        if (remedyDto.getRemedyDetails() != null && remedyDto.getRemedyDetails().size() > 0) {
            for (RemedyDetailsDto remedyDetailsDto : remedyDto.getRemedyDetails()) {
                RemedyDetails remedyDetails = new RemedyDetails();
                this.mapRemedyDetailDtoToEntity(remedyDetailsDto, remedyDetails);
                remedyDetails.setRemedy(remedy);
                remedyDetailsRepo.save(remedyDetails);
            }
        }
        RemedyDto remedyDto1 = new RemedyDto();
        this.mapEntityToDto(remedy, remedyDto1);
        return remedyDto1;
    }

    @Override
    public RemedyDto updateRemedyImage(RemedyDto remedyDto) {
        Booking booking = bookingRepo.getById(remedyDto.getBooking().getId());
        Remedy remedy = remedyRepo.getByBookingId(remedyDto.getBooking().getId());
        if (remedyDto.getImage() != null) {
            remedy.setImage(remedyDto.getImage());
            booking.setImageRemedy(remedyDto.getImage());
            bookingRepo.save(booking);
        }
        if (remedyDto.getDescription() != null && !Objects.equals(remedyDto.getDescription(), "")) {
            remedy.setDescription(remedyDto.getDescription());
        }
        if (remedyDto.getNote() != null && !Objects.equals(remedyDto.getNote(), "")) {
            remedy.setNote(remedyDto.getNote());
        }
        remedyRepo.save(remedy);
        sendRemedy.sendRemedyImage(remedy);
        RemedyDto remedyDto1 = new RemedyDto();
        this.mapEntityToDto(remedy, remedyDto1);
        return remedyDto1;
    }

    private void mapDtoToEntity(RemedyDto remedyDto, Remedy remedy) {
        remedy.setDate(remedyDto.getDate());
        if (remedyDto.getDescription() != null) {
            remedy.setDescription(remedyDto.getDescription());
        }
        if (remedyDto.getNote() != null) {
            remedy.setNote(remedyDto.getNote());
        }
        remedy.setEmail(remedyDto.getEmail());
        if (remedyDto.getImage() != null) {
            remedy.setImage(remedyDto.getImage());
        }
        remedy.setPhoneNumber(remedyDto.getPhoneNumber());
        remedy.setTimeType(remedyDto.getTimeType());

        Booking booking = bookingRepo.getById(remedyDto.getBooking().getId());
        remedy.setBooking(booking);

        User doctor = userRepo.getById(remedyDto.getDoctor().getId());
        remedy.setDoctor(doctor);

        User patient = userRepo.getById(remedyDto.getPatient().getId());
        remedy.setPatient(patient);
    }

    private void mapEntityToDto(Remedy remedy, RemedyDto remedyDto) {
        remedyDto.setId(remedy.getId());
        remedyDto.setEmail(remedy.getEmail());
        remedyDto.setPhoneNumber(remedy.getPhoneNumber());
        UserDto patient = new ModelMapper().map(remedy.getPatient(), UserDto.class);
        remedyDto.setPatient(patient);
        UserDto doctor = new ModelMapper().map(remedy.getDoctor(), UserDto.class);
        remedyDto.setDoctor(doctor);
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(remedy.getBooking().getId());
        remedyDto.setBooking(bookingDto);
        List<RemedyDetailsDto> remedyDetailsDtos = new ArrayList<>();
        for (RemedyDetails remedyDetails : remedy.getRemedyDetails()) {
            RemedyDetailsDto remedyDetailsDto = new RemedyDetailsDto();
            this.mapEntityToRemedyDetailDto(remedyDetails, remedyDetailsDto);
            remedyDetailsDtos.add(remedyDetailsDto);
        }
        remedyDto.setRemedyDetails(remedyDetailsDtos);
        remedyDto.setDescription(remedy.getDescription());
        remedyDto.setNote(remedy.getNote());
        remedyDto.setTimeType(remedy.getTimeType());
        remedyDto.setDate(remedy.getDate());

    }

    private void mapRemedyDetailDtoToEntity(RemedyDetailsDto remedyDetailsDto, RemedyDetails remedyDetails) {
        Drug drug = drugRepo.getById(remedyDetailsDto.getDrug().getId());
        remedyDetails.setDrug(drug);
        remedyDetails.setAmount(remedyDetailsDto.getAmount());
        remedyDetails.setDescription(remedyDetailsDto.getDescription());
    }

    private void mapEntityToRemedyDetailDto(RemedyDetails remedyDetails, RemedyDetailsDto remedyDetailsDto) {
        RemedyDto remedyDto = new RemedyDto();
        remedyDto.setId(remedyDetails.getRemedy().getId());
        remedyDetailsDto.setRemedy(remedyDto);
        remedyDetailsDto.setDrug(new ModelMapper().map(remedyDetails.getDrug(), DrugDto.class));
        remedyDetailsDto.setAmount(remedyDetails.getAmount());
        remedyDetailsDto.setDescription(remedyDetails.getDescription());
    }

}
