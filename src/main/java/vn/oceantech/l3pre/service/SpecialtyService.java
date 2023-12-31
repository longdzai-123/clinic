package vn.oceantech.l3pre.service;

import vn.oceantech.l3pre.dto.SpecialtyDto;

import java.util.List;

public interface SpecialtyService {
    SpecialtyDto create(SpecialtyDto specialtyDto);

    SpecialtyDto update(SpecialtyDto specialtyDto);

    Boolean deleteById(int id);

    List<SpecialtyDto> getAllByLimit(Integer limit);

    List<SpecialtyDto> getAllSpecialty();

    SpecialtyDto getById(int id);

    List<SpecialtyDto> search(String keyWord);

}
