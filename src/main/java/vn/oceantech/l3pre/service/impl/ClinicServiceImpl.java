package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.dto.ClinicDto;
import vn.oceantech.l3pre.entity.Clinic;
import vn.oceantech.l3pre.exception.ErrorMessage;
import vn.oceantech.l3pre.exception.NotFoundException;
import vn.oceantech.l3pre.repository.ClinicRepo;
import vn.oceantech.l3pre.service.ClinicService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClinicServiceImpl implements ClinicService {
    private final ClinicRepo clinicRepo;

    @Override
    public ClinicDto create(ClinicDto clinicDto) {
        clinicDto.setCreatedAt(LocalDateTime.now());
        Clinic clinic = new ModelMapper().map(clinicDto, Clinic.class);
        clinicRepo.save(clinic);
        clinicDto.setId(clinic.getId());
        return clinicDto;
    }

    @Override
    public ClinicDto update(ClinicDto clinicDto) {
        Clinic clinic = clinicRepo.findById(clinicDto.getId()).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_CLINIC));
        clinicDto.setUpdatedAt(LocalDateTime.now());
        this.mapDtoToEntity(clinicDto, clinic);
        clinicRepo.save(clinic);
        return new ModelMapper().map(clinic, ClinicDto.class);
    }

    @Override
    public ClinicDto getById(int id) {
        Clinic clinic = clinicRepo.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_CLINIC));
        return new ModelMapper().map(clinic, ClinicDto.class);
    }

    @Override
    public List<ClinicDto> getAll() {
        List<Clinic> clinics = clinicRepo.getAll();
        List<ClinicDto> clinicDtos = new ArrayList<>();
        for (Clinic clinic : clinics) {
            ClinicDto clinicDto = new ModelMapper().map(clinic, ClinicDto.class);
            clinicDtos.add(clinicDto);
        }
        return clinicDtos;
    }

    private void mapDtoToEntity(ClinicDto clinicDto, Clinic clinic) {
        if (clinicDto.getName() != null) {
            clinic.setName(clinicDto.getName());
        }
        if (clinicDto.getAddress() != null) {
            clinic.setAddress(clinicDto.getAddress());
        }
        if (clinicDto.getDescriptionMarkdown() != null) {
            clinic.setDescriptionMarkdown(clinicDto.getDescriptionMarkdown());
        }
        if (clinicDto.getDescriptionHTML() != null) {
            clinic.setDescriptionHTML(clinicDto.getDescriptionHTML());
        }
        if (clinicDto.getImage() != null) {
            clinic.setImage(clinicDto.getImage());
        }
    }

}
