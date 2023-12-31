package vn.oceantech.l3pre.service;

import vn.oceantech.l3pre.dto.ClinicDto;
import vn.oceantech.l3pre.entity.Clinic;

import java.util.List;

public interface ClinicService {
    List<ClinicDto> getAll();

    ClinicDto create(ClinicDto clinicDto);

    ClinicDto update(ClinicDto clinicDto);

    ClinicDto getById(int id)
;}
