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
public class ClinicDto {
    private Integer id;
    private String name;
    private String address;
    private String descriptionMarkdown;
    private String descriptionHTML;
    private String image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
