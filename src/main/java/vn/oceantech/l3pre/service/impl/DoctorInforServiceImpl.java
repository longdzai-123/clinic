package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.repository.DoctorInforRepo;
import vn.oceantech.l3pre.repository.UserRepo;
import vn.oceantech.l3pre.dto.DoctorExtraInformationDto;
import vn.oceantech.l3pre.dto.DoctorInformationDetailDto;
import vn.oceantech.l3pre.dto.DoctorInformationDto;
import vn.oceantech.l3pre.dto.DoctorProfileDto;
import vn.oceantech.l3pre.entity.DoctorInformation;
import vn.oceantech.l3pre.entity.User;
import vn.oceantech.l3pre.projection.DoctorDetailsPro;
import vn.oceantech.l3pre.projection.DoctorProfilePro;
import vn.oceantech.l3pre.service.DoctorInforService;
import vn.oceantech.l3pre.validation.DoctorInforValidator;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DoctorInforServiceImpl implements DoctorInforService {
    private final DoctorInforRepo doctorInforRepo;
    private final DoctorInforValidator doctorInforValidator;
    private final UserRepo userRepo;

    @Override
    public DoctorInformation create(DoctorInformationDto doctorInformationDto) {
        doctorInforValidator.existDoctorInfor(doctorInformationDto.getDoctorId());
        doctorInformationDto.setCount(0);
        DoctorInformation doctorInformation = new ModelMapper().map(doctorInformationDto, DoctorInformation.class);
        doctorInforRepo.save(doctorInformation);
        return doctorInforRepo.getById(doctorInformation.getId());
    }

    @Override
    public DoctorInformation update(DoctorInformationDto doctorInformationDto) {
        DoctorInformation doctorInformation = doctorInforRepo.getByDoctorId(doctorInformationDto.getDoctorId());
        doctorInformation.setSpecialtyId(doctorInformationDto.getSpecialtyId());
        doctorInformation.setClinicId(doctorInformationDto.getClinicId());
        doctorInformation.setPriceId(doctorInformationDto.getPriceId());
        doctorInformation.setPriceId(doctorInformationDto.getPriceId());
        doctorInformation.setProvinceId(doctorInformationDto.getProvinceId());
        doctorInformation.setPaymentId(doctorInformationDto.getPaymentId());
        doctorInformation.setAddressClinic(doctorInformationDto.getAddressClinic());
        doctorInformation.setNameClinic(doctorInformationDto.getNameClinic());
        doctorInformation.setNote(doctorInformationDto.getNote());
        doctorInformation.setUpdatedAt(LocalDateTime.now());
        doctorInforRepo.save(doctorInformation);
        return doctorInforRepo.getById(doctorInformation.getId());
    }

    @Override
    public DoctorInformation getByDoctorId(int id) {
        return doctorInforRepo.getByDoctorId(id);
    }

    @Override
    public DoctorInformationDetailDto getDoctorInforDetailByDoctorId(int id) {
        DoctorDetailsPro doctorDetailsPro = doctorInforRepo.getDoctorDetailByDoctorId(id);
        if (!Objects.isNull(doctorDetailsPro)) {
            return DoctorInformationDetailDto.builder()
                    .id(doctorDetailsPro.getId())
                    .doctorId(doctorDetailsPro.getDoctorId())
                    .specialtyId(doctorDetailsPro.getSpecialtyId())
                    .clinicId(doctorDetailsPro.getClinicId())
                    .priceId(doctorDetailsPro.getPriceId())
                    .provinceId(doctorDetailsPro.getProvinceId())
                    .paymentId(doctorDetailsPro.getPaymentId())
                    .addressClinic(doctorDetailsPro.getAddressClinic())
                    .nameClinic(doctorDetailsPro.getNameClinic())
                    .note(doctorDetailsPro.getNote())
                    .count(doctorDetailsPro.getCount())
                    .createdAt(doctorDetailsPro.getCreatedAt())
                    .priceEn(doctorDetailsPro.getPriceEn())
                    .priceVi(doctorDetailsPro.getPriceVi())
                    .provinceEn(doctorDetailsPro.getProvinceEn())
                    .provinceVi(doctorDetailsPro.getProvinceVi())
                    .paymentEn(doctorDetailsPro.getPaymentEn())
                    .paymentVi(doctorDetailsPro.getPaymentVi())
                    .build();
        }
        return null;
    }

    @Override
    public DoctorExtraInformationDto getDoctorExtraInforByDoctorId(int id) {
        DoctorDetailsPro doctorDetailsPro = doctorInforRepo.getDoctorDetailByDoctorId(id);
        if (!Objects.isNull(doctorDetailsPro)) {
            return DoctorExtraInformationDto.builder()
                    .priceId(doctorDetailsPro.getPriceId())
                    .provinceId(doctorDetailsPro.getProvinceId())
                    .paymentId(doctorDetailsPro.getPaymentId())
                    .addressClinic(doctorDetailsPro.getAddressClinic())
                    .nameClinic(doctorDetailsPro.getNameClinic())
                    .note(doctorDetailsPro.getNote())
                    .count(doctorDetailsPro.getCount())
                    .createdAt(doctorDetailsPro.getCreatedAt())
                    .priceEn(doctorDetailsPro.getPriceEn())
                    .priceVi(doctorDetailsPro.getPriceVi())
                    .provinceEn(doctorDetailsPro.getProvinceEn())
                    .provinceVi(doctorDetailsPro.getProvinceVi())
                    .paymentEn(doctorDetailsPro.getPaymentEn())
                    .paymentVi(doctorDetailsPro.getPaymentVi())
                    .build();
        }
        return null;
    }

    @Override
    public DoctorProfileDto getDoctorProfileByDoctorId(int id) {
        DoctorProfilePro doctorProfilePro = doctorInforRepo.getDoctorProfileByDoctorId(id);
        if (!Objects.isNull(doctorProfilePro)) {
            return new DoctorProfileDto(this.doctorExtraInforDto(doctorProfilePro), this.doctorProfileDto(doctorProfilePro));
        }
        return null;

    }

    private DoctorProfileDto doctorProfileDto(DoctorProfilePro doctorProfilePro) {
        User user = userRepo.getById(doctorProfilePro.getDoctorId());
        DoctorProfileDto doctorProfileDto = new DoctorProfileDto();
        doctorProfileDto.setId(doctorProfilePro.getId());
        doctorProfileDto.setEmail(doctorProfilePro.getEmail());
        doctorProfileDto.setFirstName(doctorProfilePro.getFirstName());
        doctorProfileDto.setLastName(doctorProfilePro.getLastName());
        doctorProfileDto.setAddress(doctorProfilePro.getAddress());
        doctorProfileDto.setGender(doctorProfilePro.getGender());
        doctorProfileDto.setRoleId(doctorProfilePro.getRoleId());
        doctorProfileDto.setPhoneNumber(doctorProfilePro.getPhoneNumber());
        doctorProfileDto.setPositionId(doctorProfilePro.getPositionId());
        doctorProfileDto.setImage(user.getImage());
        doctorProfileDto.setTotalCost(doctorProfilePro.getTotalCost());
        doctorProfileDto.setTotalRevenue(doctorProfilePro.getTotalRevenue());
        doctorProfileDto.setPositionId(doctorProfilePro.getPositionId());
        doctorProfileDto.setPositionEn(doctorProfilePro.getPositionEn());
        doctorProfileDto.setPositionVi(doctorProfilePro.getPositionVi());
        doctorProfileDto.setDescription(doctorProfilePro.getDescription());
        return doctorProfileDto;
    }

    private DoctorExtraInformationDto doctorExtraInforDto(DoctorProfilePro doctorProfilePro) {
        DoctorExtraInformationDto doctorExtraInformationDto = new DoctorExtraInformationDto();
        doctorExtraInformationDto.setPriceId(doctorProfilePro.getPriceId());
        doctorExtraInformationDto.setProvinceId(doctorProfilePro.getProvinceId());
        doctorExtraInformationDto.setPaymentId(doctorProfilePro.getPaymentId());
        doctorExtraInformationDto.setAddressClinic(doctorProfilePro.getAddressClinic());
        doctorExtraInformationDto.setNameClinic(doctorProfilePro.getNameClinic());
        doctorExtraInformationDto.setNote(doctorProfilePro.getNote());
        doctorExtraInformationDto.setCount(doctorProfilePro.getCount());
        doctorExtraInformationDto.setPriceEn(doctorProfilePro.getPriceEn());
        doctorExtraInformationDto.setPriceVi(doctorProfilePro.getPriceVi());
        doctorExtraInformationDto.setProvinceEn(doctorProfilePro.getProvinceEn());
        doctorExtraInformationDto.setProvinceVi(doctorProfilePro.getProvinceVi());
        doctorExtraInformationDto.setPaymentEn(doctorProfilePro.getPaymentEn());
        doctorExtraInformationDto.setProvinceVi(doctorProfilePro.getPaymentVi());
        return doctorExtraInformationDto;
    }

    public String encodeToBase64Image(byte[] image) {
        String base64String = Base64.getEncoder().encodeToString(image);
        return "data:image/jpeg;base64," + base64String;
    }

}
