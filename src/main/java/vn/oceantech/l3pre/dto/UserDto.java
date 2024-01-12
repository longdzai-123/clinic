package vn.oceantech.l3pre.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;

    private String roleId;

    private String phoneNumber;

    private String positionId;

    private String image;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Integer totalCost;

    private Integer totalRevenue;

    private Boolean isActive;
}
