package vn.oceantech.l3pre.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private Integer id;
    private String statusId;
    private UserDto doctor;
    private UserDto patient;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private AllCodesDto timeType;
    private String timeBooking;
    private LocalDateTime createdAt;
    private String imageRemedy;
    private Boolean isRemedy;
    @NotBlank
    private String patientName;
    @NotBlank
    private String patientPhoneNumber;
    @NotBlank
    private String patientAddress;
    @NotBlank
    private String patientReason;
    private AllCodesDto patientGender;
    private LocalDate patientBirthday;
    private String email;
    private String verifyBooking;
}
