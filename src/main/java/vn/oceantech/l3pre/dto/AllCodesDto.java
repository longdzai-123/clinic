package vn.oceantech.l3pre.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AllCodesDto {
    private Integer id;
    private String keyMap;
    private String type;
    private String valueEn;
    private String valueVi;

    public AllCodesDto(String valueEn, String valueVi) {
        this.valueEn = valueEn;
        this.valueVi = valueVi;
    }
}
