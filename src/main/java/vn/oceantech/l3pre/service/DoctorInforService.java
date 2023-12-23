package vn.oceantech.l3pre.service;

import vn.oceantech.l3pre.dto.DoctorExtraInformationDto;
import vn.oceantech.l3pre.dto.DoctorInformationDetailDto;
import vn.oceantech.l3pre.dto.DoctorInformationDto;
import vn.oceantech.l3pre.dto.DoctorProfileDto;

import java.util.List;

public interface DoctorInforService {
    DoctorInformationDto create(DoctorInformationDto doctorInformationDto);

    DoctorInformationDto update(DoctorInformationDto doctorInformationDto);

    List<DoctorInformationDto> getBySpecialtyAndProvince(int specialtyId, String provinceId);

    DoctorInformationDto getByDoctorId(int id);

    DoctorInformationDetailDto getDoctorInforDetailByDoctorId(int id);

    DoctorExtraInformationDto getDoctorExtraInforByDoctorId(int id);

    DoctorProfileDto getDoctorProfileByDoctorId(int id);
}
