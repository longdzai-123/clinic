package vn.oceantech.l3pre.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialtyDto {
    private Integer id;
    private String descriptionHTML;
    private String descriptionMarkdown;
    @Lob
    private String image;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
