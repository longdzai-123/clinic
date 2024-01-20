package vn.oceantech.l3pre.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RemedyDto {
    private Integer id;

    private String email;

    private String phoneNumber;

    private UserDto patient;

    private UserDto doctor;

    private BookingDto booking;

    private List<RemedyDetailsDto> remedyDetails;

    private String description;

    private String note;

    private String timeType;

    private LocalDate date;

    private String image;

    private Integer specialtyId;

    private Integer totalCost;
}
