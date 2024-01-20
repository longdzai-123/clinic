package vn.oceantech.l3pre.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DrugDto {
    private Integer id;
    private String name;
    private UnitDrugDto unit;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
