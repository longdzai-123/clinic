package vn.oceantech.l3pre.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class DoctorInformationDetailDto {
    private Integer id;
    private Integer doctorId;
    private Integer specialtyId;
    private Integer clinicId;
    private String priceId;
    private String provinceId;
    private String paymentId;
    private String addressClinic;
    private String nameClinic;
    private String note;
    private Integer count;
    private LocalDateTime createdAt;
    private String priceEn;
    private String priceVi;
    private String provinceEn;
    private String provinceVi;
    private String paymentEn;
    private String paymentVi;
}
