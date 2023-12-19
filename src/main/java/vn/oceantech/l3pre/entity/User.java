package vn.oceantech.l3pre.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "address")
    private String address;
    @Column(name = "gender")
    private String gender;
    @Column(name = "role_id")
    private String roleId;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "position_id")
    private String positionId;
    @Lob
    @Column(name = "image")
    private String image;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "total_cost")
    private Integer totalCost;
    @Column(name = "total_revenue")
    private Integer totalRevenue;

}
