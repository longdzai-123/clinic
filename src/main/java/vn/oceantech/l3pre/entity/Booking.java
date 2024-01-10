package vn.oceantech.l3pre.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status_id")
    private String statusId;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private User patient;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time_type")
    private String timeType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "image_remedy")
    private String imageRemedy;

    @Column(name = "is_remedy")
    private Boolean isRemedy;

    @Column(name = "patient_name")
    private String patientName;

    @Column(name = "patient_phoneNumber")
    private String patientPhoneNumber;

    @Column(name = "patient_address")
    private String patientAddress;

    @Column(name = "patient_reason")
    private String patientReason;

    @Column(name = "patient_gender")
    private String patientGender;

    @Column(name = "patient_birthday")
    private LocalDate patientBirthday;

    @Column(name = "verify_booking")
    private String verifyBooking;
}
