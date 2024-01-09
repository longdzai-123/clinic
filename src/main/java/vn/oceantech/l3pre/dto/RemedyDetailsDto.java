package vn.oceantech.l3pre.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.oceantech.l3pre.entity.Drug;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RemedyDetailsDto {
    private Integer id;

    private RemedyDto remedy;

    private DrugDto drug;

    private Integer amount;

    private String description;
}
