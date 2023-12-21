package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.dto.DoctorClinicSpecialtyDto;
import vn.oceantech.l3pre.entity.Clinic;
import vn.oceantech.l3pre.entity.DoctorClinicSpecialty;
import vn.oceantech.l3pre.entity.Specialty;
import vn.oceantech.l3pre.entity.User;
import vn.oceantech.l3pre.exception.ErrorMessage;
import vn.oceantech.l3pre.exception.ProException;
import vn.oceantech.l3pre.repository.DoctorClinicSpecialtyRepo;
import vn.oceantech.l3pre.repository.UserRepo;
import vn.oceantech.l3pre.service.DoctorClinicSpecialtyService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DoctorClinicSpecialtyServiceImpl implements DoctorClinicSpecialtyService {
    private final UserRepo userRepo;
    private final DoctorClinicSpecialtyRepo doctorClinicSpecialtyRepo;

    @Override
    public DoctorClinicSpecialtyDto create(DoctorClinicSpecialtyDto doctorClinicSpecialtyDto) {
        DoctorClinicSpecialty doctorClinicSpecialty = new DoctorClinicSpecialty();
        this.mapDtoToEntity(doctorClinicSpecialty, doctorClinicSpecialtyDto);
        doctorClinicSpecialtyRepo.save(doctorClinicSpecialty);
        return new ModelMapper().map(doctorClinicSpecialty, DoctorClinicSpecialtyDto.class);
    }

    private void mapDtoToEntity(DoctorClinicSpecialty doctorClinicSpecialty, DoctorClinicSpecialtyDto doctorClinicSpecialtyDto) {
        User user = userRepo.findById(doctorClinicSpecialtyDto.getUser().getId()).
                orElseThrow(() -> new ProException(ErrorMessage.NOT_FOUND_USER));
        doctorClinicSpecialty.setUser(user);
        Clinic clinic = new Clinic();
        clinic.setId(doctorClinicSpecialtyDto.getClinic().getId());
        doctorClinicSpecialty.setClinic(clinic);
        Specialty specialty = new Specialty();
        specialty.setId(doctorClinicSpecialtyDto.getSpecialty().getId());
        doctorClinicSpecialty.setSpecialty(specialty);
        doctorClinicSpecialty.setCreatedAt(LocalDateTime.now());
    }
}
