package vn.oceantech.l3pre.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceDto {
    private Integer id;
    private UserDto doctor;
    private UserDto patient;
    private RemedyDto remedy;
    private String time;
    private Boolean isPay;
    private SpecialtyDto specialty;
    private Integer totalCost;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
