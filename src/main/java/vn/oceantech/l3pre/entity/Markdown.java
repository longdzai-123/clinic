package vn.oceantech.l3pre.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "markdowns")
public class Markdown {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "content_html")
    private String contentHTML;
    @Column(name = "content_markdown")
    private String contentMarkdown;
    @Column(name = "description")
    private String description;
    @Column(name = "doctor_id")
    private Integer doctorId;
    @Column(name = "specialty_id")
    private Integer specialtyId;
    @Column(name = "clinic_id")
    private Integer clinicId;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
