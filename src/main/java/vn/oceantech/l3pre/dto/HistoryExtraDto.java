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
public class HistoryExtraDto {
    private Integer id;
    private Integer historyId;
    private String drugs;
    private String descriptionUsage;
    private Integer unit;
    private Integer amount;
    private LocalDateTime createdAt;
}
