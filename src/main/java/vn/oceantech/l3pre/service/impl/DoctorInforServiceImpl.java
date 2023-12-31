package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.dto.*;
import vn.oceantech.l3pre.dto.Response.DoctorResponseDto;
import vn.oceantech.l3pre.entity.Clinic;
import vn.oceantech.l3pre.entity.DoctorInformation;
import vn.oceantech.l3pre.entity.Specialty;
import vn.oceantech.l3pre.entity.User;
import vn.oceantech.l3pre.exception.ErrorMessage;
import vn.oceantech.l3pre.exception.ProException;
import vn.oceantech.l3pre.projection.DoctorDetailsPro;
import vn.oceantech.l3pre.projection.DoctorProfilePro;
import vn.oceantech.l3pre.projection.TopDoctorPro;
import vn.oceantech.l3pre.repository.DoctorInforRepo;
import vn.oceantech.l3pre.repository.UserRepo;
import vn.oceantech.l3pre.service.DoctorInforService;
import vn.oceantech.l3pre.validation.DoctorInforValidator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorInforServiceImpl implements DoctorInforService {
    private final DoctorInforRepo doctorInforRepo;
    private final DoctorInforValidator doctorInforValidator;
    private final UserRepo userRepo;

    @Override
    public DoctorInformationDto create(DoctorInformationDto doctorInformationDto) {
        doctorInforValidator.existDoctorInfor(doctorInformationDto.getUser().getId());
        doctorInformationDto.setCount(0);
        DoctorInformation doctorInformation = new ModelMapper().map(doctorInformationDto, DoctorInformation.class);
        doctorInforRepo.save(doctorInformation);
        return new ModelMapper().map(doctorInformation, DoctorInformationDto.class);
    }

    @Override
    public DoctorInformationDto update(DoctorInformationDto doctorInformationDto) {
        DoctorInformation doctorInformation;
        if (doctorInforRepo.getByDoctorId(doctorInformationDto.getUser().getId()) != null) {
            doctorInformation = doctorInforRepo.getByDoctorId(doctorInformationDto.getUser().getId());
        } else {
            throw new ProException(ErrorMessage.DUPLICATE_DOCTOR_INFORMATION);
        }
        if (!Objects.isNull(doctorInformationDto.getSpecialty())) {
            Specialty specialty = new Specialty();
            specialty.setId(doctorInformationDto.getSpecialty().getId());
            doctorInformation.setSpecialty(specialty);
        }
        if (!Objects.isNull(doctorInformationDto.getClinic())) {
            Clinic clinic = new Clinic();
            clinic.setId(doctorInformationDto.getClinic().getId());
            doctorInformation.setClinic(clinic);
        }
        doctorInformation.setPriceId(doctorInformationDto.getPriceId());
        doctorInformation.setPriceId(doctorInformationDto.getPriceId());
        doctorInformation.setProvinceId(doctorInformationDto.getProvinceId());
        doctorInformation.setPaymentId(doctorInformationDto.getPaymentId());
        doctorInformation.setAddressClinic(doctorInformationDto.getAddressClinic());
        doctorInformation.setNameClinic(doctorInformationDto.getNameClinic());
        doctorInformation.setNote(doctorInformationDto.getNote());
        doctorInformation.setUpdatedAt(LocalDateTime.now());
        doctorInforRepo.save(doctorInformation);
        return new ModelMapper().map(doctorInformation, DoctorInformationDto.class);
    }

    @Override
    public List<DoctorInformationDto> getBySpecialtyAndProvince(int specialtyId, String provinceId) {
        if (provinceId.isEmpty()) {
            provinceId = null;
        }
        List<DoctorInformation> doctorInformations = doctorInforRepo.getBySpecialtyAndProvince(specialtyId, provinceId);
        List<DoctorInformationDto> doctorInformationDtos = doctorInformations.stream()
                .map(p -> new ModelMapper().map(p, DoctorInformationDto.class)).collect(Collectors.toList());
        return doctorInformationDtos;
    }


    @Override
    public DoctorInformationDto getByDoctorId(int id) {
        DoctorInformation doctorInformation = doctorInforRepo.getByDoctorId(id);
        return new ModelMapper().map(doctorInformation, DoctorInformationDto.class);
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

    @Override
    public List<DoctorResponseDto> getTopDoctorHome(int size) {
        List<TopDoctorPro> topDoctorPros = doctorInforRepo.getTopDoctor(size);
        List<DoctorResponseDto> topDoctorResponseDtos = new ArrayList<>();
        for (TopDoctorPro topDoctorPro : topDoctorPros) {
            User user = userRepo.getById(topDoctorPro.getId());
            DoctorResponseDto topDoctorResponseDto = new DoctorResponseDto();
            topDoctorResponseDto.setId(topDoctorPro.getId());
            topDoctorResponseDto.setEmail(topDoctorPro.getEmail());
            topDoctorResponseDto.setFirstName(topDoctorPro.getFirstName());
            topDoctorResponseDto.setLastName(topDoctorPro.getLastName());
            topDoctorResponseDto.setAddress(topDoctorPro.getAddress());
            topDoctorResponseDto.setGender(topDoctorPro.getGender());
            topDoctorResponseDto.setRoleId(topDoctorPro.getRoleId());
            topDoctorResponseDto.setPhoneNumber(topDoctorPro.getPhoneNumber());
            topDoctorResponseDto.setPositionId(topDoctorPro.getPositionId());
            topDoctorResponseDto.setImage(user.getImage());
            topDoctorResponseDto.setCreatedAt(topDoctorPro.getCreatedAt());
            topDoctorResponseDto.setUpdatedAt(topDoctorPro.getUpdatedAt());
            topDoctorResponseDto.setTotalCost(topDoctorPro.getTotalCost());
            topDoctorResponseDto.setTotalRevenue(topDoctorPro.getTotalRevenue());
            topDoctorResponseDto.setValueEn(topDoctorPro.getValueEn());
            topDoctorResponseDto.setValueVi(topDoctorPro.getValueVi());
            topDoctorResponseDto.setNameSpecialty(topDoctorPro.getNameSpecialty());
            topDoctorResponseDtos.add(topDoctorResponseDto);
        }
        return topDoctorResponseDtos;
    }

    @Override
    public List<DoctorResponseDto> getAllDoctor() {
        List<TopDoctorPro> topDoctorPros = doctorInforRepo.getAllDoctor();
        List<DoctorResponseDto> doctorResponseDtos = new ArrayList<>();
        for (TopDoctorPro topDoctorPro : topDoctorPros) {
            User user = userRepo.getById(topDoctorPro.getId());
            DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
            doctorResponseDto.setId(topDoctorPro.getId());
            doctorResponseDto.setEmail(topDoctorPro.getEmail());
            doctorResponseDto.setFirstName(topDoctorPro.getFirstName());
            doctorResponseDto.setLastName(topDoctorPro.getLastName());
            doctorResponseDto.setAddress(topDoctorPro.getAddress());
            doctorResponseDto.setGender(topDoctorPro.getGender());
            doctorResponseDto.setRoleId(topDoctorPro.getRoleId());
            doctorResponseDto.setPhoneNumber(topDoctorPro.getPhoneNumber());
            doctorResponseDto.setPositionId(topDoctorPro.getPositionId());
            doctorResponseDto.setImage(user.getImage());
            doctorResponseDto.setCreatedAt(topDoctorPro.getCreatedAt());
            doctorResponseDto.setUpdatedAt(topDoctorPro.getUpdatedAt());
            doctorResponseDto.setTotalCost(topDoctorPro.getTotalCost());
            doctorResponseDto.setTotalRevenue(topDoctorPro.getTotalRevenue());
            doctorResponseDto.setValueEn(topDoctorPro.getValueEn());
            doctorResponseDto.setValueVi(topDoctorPro.getValueVi());
            doctorResponseDto.setNameSpecialty(topDoctorPro.getNameSpecialty());
            doctorResponseDtos.add(doctorResponseDto);
        }
        return doctorResponseDtos;
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
