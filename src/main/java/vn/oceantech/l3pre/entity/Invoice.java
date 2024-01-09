package vn.oceantech.l3pre.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "doctor_id",referencedColumnName = "id")
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id",referencedColumnName = "id")
    private User patient;

    @OneToOne
    @JoinColumn(name = "remedy_id",referencedColumnName = "id")
    private Remedy remedy;

    @ManyToOne
    @JoinColumn(name = "specialty_id",referencedColumnName = "id")
    private Specialty specialty;

    @Column(name = "total_cost")
    private Integer totalCost;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
