package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.dto.SpecialtyDto;
import vn.oceantech.l3pre.entity.Specialty;
import vn.oceantech.l3pre.exception.ErrorMessage;
import vn.oceantech.l3pre.exception.NotFoundException;
import vn.oceantech.l3pre.exception.ProException;
import vn.oceantech.l3pre.repository.SpecialtyRepo;
import vn.oceantech.l3pre.service.SpecialtyService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {
    private final SpecialtyRepo specialtiesRepo;

    @Override
    public SpecialtyDto create(SpecialtyDto specialtyDto) {
        Specialty specialty = new Specialty();
        this.mapDtoToEntity(specialtyDto, specialty);
        specialty.setCreatedAt(LocalDateTime.now());
        specialtiesRepo.save(specialty);
        return new ModelMapper().map(specialty, SpecialtyDto.class);
    }

    @Override
    public SpecialtyDto update(SpecialtyDto specialtyDto) {
        Specialty specialty = specialtiesRepo.findById(specialtyDto.getId()).
                orElseThrow(() -> new ProException(ErrorMessage.NOT_FOUND_SPECIALTY));
        this.mapDtoToEntity(specialtyDto, specialty);
        specialty.setUpdatedAt(LocalDateTime.now());
        specialtiesRepo.save(specialty);
        return new ModelMapper().map(specialty, SpecialtyDto.class);
    }

    @Override
    public Boolean deleteById(int id) {
        Specialty specialty = specialtiesRepo.findById(id).
                orElseThrow(() -> new ProException(ErrorMessage.NOT_FOUND_SPECIALTY));
        if (!Objects.isNull(specialty)) {
            specialtiesRepo.deleteById(id);
        }
        return true;
    }

    @Override
    public List<SpecialtyDto> getAllByLimit(Integer limit) {
        if (Objects.isNull(limit)) {
            limit = 10;
        }
        List<Specialty> specialties = specialtiesRepo.getAllByLimit(limit);
        List<SpecialtyDto> specialtyDtos = new ArrayList<>();
        for (Specialty specialty : specialties) {
            SpecialtyDto specialtyDto = new ModelMapper().map(specialty, SpecialtyDto.class);
            specialtyDtos.add(specialtyDto);
        }
        return specialtyDtos;
    }

    @Override
    public List<SpecialtyDto> getAllSpecialty() {
        List<Specialty> specialties = specialtiesRepo.getAllSpecialty();
        return specialties.stream().map(p -> new ModelMapper().map(p, SpecialtyDto.class)).collect(Collectors.toList());
    }

    @Override
    public SpecialtyDto getById(int id) {
        Specialty specialty = specialtiesRepo.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_SPECIALTY));
        return new ModelMapper().map(specialty, SpecialtyDto.class);
    }

    @Override
    public List<SpecialtyDto> search(String keyWord) {
        List<Specialty> specialties = specialtiesRepo.search(keyWord);
        return specialties.stream().map(specialty -> new ModelMapper()
                .map(specialty, SpecialtyDto.class)).collect(Collectors.toList());
    }

    private void mapDtoToEntity(SpecialtyDto specialtyDto, Specialty specialty) {
        if (!Objects.isNull(specialtyDto.getDescriptionHTML())) {
            specialty.setDescriptionHTML(specialtyDto.getDescriptionHTML());
        }
        if (!Objects.isNull(specialtyDto.getDescriptionMarkdown())) {
            specialty.setDescriptionMarkdown(specialtyDto.getDescriptionMarkdown());
        }
        if (!Objects.isNull(specialtyDto.getName())) {
            specialty.setName(specialtyDto.getName());
        }
        if (!Objects.isNull(specialtyDto.getImage())) {
            specialty.setImage(specialtyDto.getImage());
        }
    }
}
