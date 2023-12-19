package vn.oceantech.l3pre.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "histories")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "patient_id")
    private Integer patientId;
    @Column(name = "doctor_id")
    private Integer doctorId;
    @Column(name = "description")
    private String description;
    @Column(name = "drugs")
    private String drugs;
    @Column(name = "file_path")
    private String filePath;
    @Column(name = "reason")
    private String reason;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
