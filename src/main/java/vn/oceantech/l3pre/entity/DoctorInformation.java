package vn.oceantech.l3pre.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctor_information")
public class DoctorInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "doctor_id")
    private Integer doctorId;
    @Column(name = "specialty_id")
    private Integer specialtyId;
    @Column(name = "clinic_id")
    private Integer clinicId;
    @Column(name = "price_id")
    private String priceId;
    @Column(name = "province_id")
    private String provinceId;
    @Column(name = "payment_id")
    private String paymentId;
    @Column(name = "address_clinic")
    private String addressClinic;
    @Column(name = "name_clinic")
    private String nameClinic;
    @Column(name = "note")
    private String note;
    @Column(name = "count")
    private Integer count;
    @Column(name = "created_at")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
