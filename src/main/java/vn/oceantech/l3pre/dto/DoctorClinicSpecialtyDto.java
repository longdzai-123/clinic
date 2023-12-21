package vn.oceantech.l3pre.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.oceantech.l3pre.entity.Clinic;
import vn.oceantech.l3pre.entity.Specialty;
import vn.oceantech.l3pre.entity.User;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorClinicSpecialtyDto {
    private Integer id;
    private User user;
    private Clinic clinic;
    private Specialty specialty;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
