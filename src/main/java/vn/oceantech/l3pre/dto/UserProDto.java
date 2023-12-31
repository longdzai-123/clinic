package vn.oceantech.l3pre.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProDto {
    private Integer id;
    private String email;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private String roleId;
    private String phoneNumber;
    private String positionId;
    @Lob
    private String image;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    private Integer totalCost;
    private Integer totalRevenue;
    private AllCodesDto allCodesDto;
    private MarkdownsDto markdownsDto;

    public UserProDto(Integer id, String email, String firstName, String lastName, String address, String gender, String roleId, String phoneNumber, String positionId, String image, LocalDateTime createdAt, LocalDateTime updatedAt, Integer totalCost, Integer totalRevenue) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.roleId = roleId;
        this.phoneNumber = phoneNumber;
        this.positionId = positionId;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.totalCost = totalCost;
        this.totalRevenue = totalRevenue;
    }

    public UserProDto(Integer id, String email, String password, String firstName, String lastName, String address, String gender, String roleId, String phoneNumber, String positionId, String image, LocalDateTime createdAt, LocalDateTime updatedAt, Integer totalCost, Integer totalRevenue) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.roleId = roleId;
        this.phoneNumber = phoneNumber;
        this.positionId = positionId;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.totalCost = totalCost;
        this.totalRevenue = totalRevenue;
    }

    public UserProDto(UserProDto userDto, MarkdownsDto markdownsDto, AllCodesDto allCodesDto) {
        this.id = userDto.getId();
        this.email = userDto.getEmail();
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.address = userDto.getAddress();
        this.gender = userDto.getGender();
        this.roleId = userDto.getRoleId();
        this.phoneNumber = userDto.getPhoneNumber();
        this.positionId = userDto.getPositionId();
        this.image = userDto.getImage();
        this.createdAt = userDto.getCreatedAt();
        this.updatedAt = userDto.getUpdatedAt();
        this.totalCost = userDto.getTotalCost();
        this.totalRevenue = userDto.getTotalRevenue();
        this.markdownsDto = markdownsDto;
        this.allCodesDto = allCodesDto;
    }

    public UserProDto(UserProDto userDto, MarkdownsDto markdownsDto) {
        this.id = userDto.getId();
        this.email = userDto.getEmail();
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.address = userDto.getAddress();
        this.gender = userDto.getGender();
        this.roleId = userDto.getRoleId();
        this.phoneNumber = userDto.getPhoneNumber();
        this.positionId = userDto.getPositionId();
        this.image = userDto.getImage();
        this.createdAt = userDto.getCreatedAt();
        this.updatedAt = userDto.getUpdatedAt();
        this.totalCost = userDto.getTotalCost();
        this.totalRevenue = userDto.getTotalRevenue();
        this.markdownsDto = markdownsDto;
    }

    public UserProDto(UserProDto userDto, AllCodesDto allCodesDto) {
        this.id = userDto.getId();
        this.email = userDto.getEmail();
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.address = userDto.getAddress();
        this.gender = userDto.getGender();
        this.roleId = userDto.getRoleId();
        this.phoneNumber = userDto.getPhoneNumber();
        this.positionId = userDto.getPositionId();
        this.image = userDto.getImage();
        this.createdAt = userDto.getCreatedAt();
        this.updatedAt = userDto.getUpdatedAt();
        this.totalCost = userDto.getTotalCost();
        this.totalRevenue = userDto.getTotalRevenue();
        this.allCodesDto = allCodesDto;
    }

    public UserProDto(Integer id, String email, String firstName, String lastName, String address, String gender, String roleId, String phoneNumber, String positionId, LocalDateTime createdAt, LocalDateTime updatedAt, Integer totalCost, Integer totalRevenue) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.roleId = roleId;
        this.phoneNumber = phoneNumber;
        this.positionId = positionId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.totalCost = totalCost;
        this.totalRevenue = totalRevenue;
    }


}
