package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.repository.ClinicRepo;
import vn.oceantech.l3pre.dto.ClinicDto;
import vn.oceantech.l3pre.entity.Clinic;
import vn.oceantech.l3pre.service.ClinicService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClinicServiceImpl implements ClinicService {
    private final ClinicRepo clinicRepo;
    @Override
    public List<ClinicDto> getAll() {
        List<Clinic> clinics =  clinicRepo.getAll();
        List<ClinicDto> clinicDtos = new ArrayList<>();
        for (Clinic clinic : clinics) {
            ClinicDto clinicDto = new ModelMapper().map(clinic, ClinicDto.class);
            clinicDtos.add(clinicDto);
        }
        return clinicDtos;
    }
}
