package vn.oceantech.l3pre.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SchedulesDto {
    private Integer id;
    private Integer currentNumber;
    private Integer maxNumber;
    private Date date;
    private String timeType;
    private Integer doctorId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date createdAt;
    private String valueEn;
    private String valueVi;
    private Integer value;
}
