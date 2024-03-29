package vn.oceantech.l3pre.service;

import vn.oceantech.l3pre.dto.ClinicDto;
import vn.oceantech.l3pre.dto.SpecialtyDto;

import java.util.List;

public interface ClinicService {
    ClinicDto create(ClinicDto clinicDto);

    ClinicDto update(ClinicDto clinicDto);

    Boolean deleteById(int id);

    ClinicDto getById(int id);

    List<ClinicDto> getAll();

    List<ClinicDto> getAllByLimit(Integer limit);

    List<ClinicDto> search(String nameClinic, String nameAddress);
}
