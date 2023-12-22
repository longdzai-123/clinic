package vn.oceantech.l3pre.service;

import vn.oceantech.l3pre.dto.SpecialtyDto;

import java.util.List;

public interface SpecialtyService {

    SpecialtyDto create(SpecialtyDto specialtyDto);
    SpecialtyDto update(SpecialtyDto specialtyDto);
    List<SpecialtyDto> getAll(Integer limit);
    List<SpecialtyDto> getAllSpecialty();
}
