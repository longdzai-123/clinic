package vn.oceantech.l3pre.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "doctor_id")
    private Integer doctorId;
    @Column(name = "patient_id")
    private Integer patientId;
    @Column(name = "specialty_id")
    private Integer specialtyId;
    @Column(name = "total_cost")
    private Integer totalCost;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
