package vn.oceantech.l3pre.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorExtraInformationDto {
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

    public DoctorExtraInformationDto(String priceId, String provinceId, String paymentId, String addressClinic, String nameClinic, String note, Integer count, String priceEn, String priceVi, String provinceEn, String provinceVi, String paymentEn, String paymentVi) {
        this.priceId = priceId;
        this.provinceId = provinceId;
        this.paymentId = paymentId;
        this.addressClinic = addressClinic;
        this.nameClinic = nameClinic;
        this.note = note;
        this.count = count;
        this.priceEn = priceEn;
        this.priceVi = priceVi;
        this.provinceEn = provinceEn;
        this.provinceVi = provinceVi;
        this.paymentEn = paymentEn;
        this.paymentVi = paymentVi;
    }
}
