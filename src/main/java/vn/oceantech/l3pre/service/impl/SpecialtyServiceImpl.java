package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.repository.SpecialtyRepo;
import vn.oceantech.l3pre.dto.SpecialtyDto;
import vn.oceantech.l3pre.entity.Specialty;
import vn.oceantech.l3pre.service.SpecialtyService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {
    private final SpecialtyRepo specialtiesRepo;

    @Override
    public SpecialtyDto create(SpecialtyDto specialtyDto) {

        return null;
    }

    @Override
    public List<SpecialtyDto> getAll() {
        List<Specialty> specialties =  specialtiesRepo.getAll();
        List<SpecialtyDto> specialtyDtos = new ArrayList<>();
        for (Specialty specialty : specialties) {
            SpecialtyDto specialtyDto = new ModelMapper().map(specialty, SpecialtyDto.class);
            specialtyDtos.add(specialtyDto);
        }
        return specialtyDtos;
    }
}
