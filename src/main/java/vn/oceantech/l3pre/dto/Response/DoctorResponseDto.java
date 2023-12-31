package vn.oceantech.l3pre.dto.Response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class DoctorResponseDto {
    private Integer id;

    private String email;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;

    private String roleId;

    private String phoneNumber;

    private String positionId;

    private String image;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Integer totalCost;

    private Integer totalRevenue;

    private String valueEn;

    private String valueVi;

    private String nameSpecialty;


}
