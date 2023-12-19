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
public class HistoriesDto {
    private Integer id;
    private Integer patientId;
    private Integer doctorId;
    private String description;
    private String drugs;
    private String filePath;
    private String reason;
    private LocalDateTime createdAt;
}
