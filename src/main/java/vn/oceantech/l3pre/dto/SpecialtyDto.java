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
public class SpecialtyDto {
    private Integer id;
    private String descriptionHTML;
    private String descriptionMarkdown;
    private String image;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
