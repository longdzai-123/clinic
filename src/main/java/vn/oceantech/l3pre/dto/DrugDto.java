package vn.oceantech.l3pre.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.oceantech.l3pre.entity.UnitDrug;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DrugDto {
    private Integer id;
    private String name;
    private UnitDrugDto unit;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
