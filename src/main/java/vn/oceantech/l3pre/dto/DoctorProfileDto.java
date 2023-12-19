package vn.oceantech.l3pre.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Lob;
import java.time.LocalDateTime;
import java.util.Base64;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorProfileDto extends DoctorExtraInformationDto {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private String roleId;
    private String phoneNumber;
    private String positionId;
    private String positionEn;
    private String positionVi;
    @Lob
    private String image;
    private Integer totalCost;
    private Integer totalRevenue;
    private String description;

    public DoctorProfileDto(DoctorExtraInformationDto doctorExtraDto, DoctorProfileDto doctorProfileDto) {
        super(doctorExtraDto.getPriceId(), doctorExtraDto.getProvinceId(), doctorExtraDto.getPaymentId(),
                doctorExtraDto.getAddressClinic(), doctorExtraDto.getNameClinic(), doctorExtraDto.getNote(), doctorExtraDto.getCount(), doctorExtraDto.getPriceEn(), doctorExtraDto.getPriceVi(), doctorExtraDto.getProvinceEn(), doctorExtraDto.getProvinceVi(), doctorExtraDto.getPaymentEn(), doctorExtraDto.getPaymentVi());
        this.id = doctorProfileDto.getId();
        this.email = doctorProfileDto.getEmail();
        this.firstName = doctorProfileDto.getFirstName();
        this.lastName = doctorProfileDto.getLastName();
        this.address = doctorProfileDto.getAddress();
        this.gender = doctorProfileDto.getGender();
        this.roleId = doctorProfileDto.getRoleId();
        this.phoneNumber = doctorProfileDto.getPhoneNumber();
        this.positionId = doctorProfileDto.getPositionId();
        this.positionEn = doctorProfileDto.getPositionEn();
        this.positionVi = doctorProfileDto.getPositionVi();
        this.image = doctorProfileDto.getImage();
        this.totalCost = doctorProfileDto.getTotalCost();
        this.totalRevenue = doctorProfileDto.getTotalRevenue();
        this.description = doctorProfileDto.getDescription();
    }
}
