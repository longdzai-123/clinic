package vn.oceantech.l3pre.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctor_clinic_specialty")
public class DoctorClinicSpecialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "clinic_id", referencedColumnName = "id")
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "specialty_id", referencedColumnName = "id")
    private Specialty specialty;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
