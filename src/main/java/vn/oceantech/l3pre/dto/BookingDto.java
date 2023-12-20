package vn.oceantech.l3pre.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private Integer id;
    private String statusId;
    private Integer doctorId;
    private Integer patientId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String timeType;
    private String timeBooking;
    private LocalDateTime createdAt;
    private String imageRemedy;
    private String patientName;
    private String patientPhoneNumber;
    private String patientAddress;
    private String patientReason;
    private String patientGender;
    private LocalDate patientBirthday;
    private String email;
    private String verifyBooking;
}