package vn.oceantech.l3pre.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MarkdownsDto {
    private Integer id;
    private String contentHTML;
    private String contentMarkdown;
    private String description;
    private Integer doctorId;
    private Integer specialtyId;
    private Integer clinicId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MarkdownsDto(String contentHTML, String contentMarkdown, String description, Integer doctorId, Integer specialtyId, Integer clinicId) {
        this.contentHTML = contentHTML;
        this.contentMarkdown = contentMarkdown;
        this.description = description;
        this.doctorId = doctorId;
        this.specialtyId = specialtyId;
        this.clinicId = clinicId;
    }
}
