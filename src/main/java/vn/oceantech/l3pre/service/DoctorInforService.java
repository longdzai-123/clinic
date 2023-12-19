package vn.oceantech.l3pre.service;

import vn.oceantech.l3pre.dto.DoctorExtraInformationDto;
import vn.oceantech.l3pre.dto.DoctorInformationDetailDto;
import vn.oceantech.l3pre.dto.DoctorInformationDto;
import vn.oceantech.l3pre.dto.DoctorProfileDto;
import vn.oceantech.l3pre.entity.DoctorInformation;

public interface DoctorInforService {
    DoctorInformation create(DoctorInformationDto doctorInformationDto);

    DoctorInformation update(DoctorInformationDto doctorInformationDto);

    DoctorInformation getByDoctorId(int id);

    DoctorInformationDetailDto getDoctorInforDetailByDoctorId(int id);

    DoctorExtraInformationDto getDoctorExtraInforByDoctorId(int id);

    DoctorProfileDto getDoctorProfileByDoctorId(int id);
}
