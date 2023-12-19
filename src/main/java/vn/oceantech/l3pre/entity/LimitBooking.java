package vn.oceantech.l3pre.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "limit_bookings")
public class LimitBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "doctor_id")
    private Integer doctorId;
    @Column(name = "time_type")
    private String timeType;
    @Column(name = "limit")
    private Integer limit;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
