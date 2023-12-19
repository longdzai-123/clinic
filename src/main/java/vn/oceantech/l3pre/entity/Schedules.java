package vn.oceantech.l3pre.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schedules")
public class Schedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "current_number")
    private Integer currentNumber;
    @Column(name = "max_number")
    private Integer maxNumber;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private Date date;
    @Column(name = "time_type")
    private String timeType;
    @Column(name = "doctor_id")
    private Integer doctorId;
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
}
