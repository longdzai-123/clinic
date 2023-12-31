package vn.oceantech.l3pre.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorInformationDto {
    private Integer id;
    private UserProDto user;
    private SpecialtyDto specialty;
    private ClinicDto clinic;
    private String priceId;
    private String provinceId;
    private String paymentId;
    private String addressClinic;
    private String nameClinic;
    private String note;
    private Integer count;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
