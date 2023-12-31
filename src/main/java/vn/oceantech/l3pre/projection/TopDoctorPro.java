package vn.oceantech.l3pre.projection;

import java.time.LocalDateTime;

public interface TopDoctorPro {
    Integer getId();

    String getEmail();

    String getFirstName();

    String getLastName();

    String getAddress();

    String getGender();

    String getRoleId();

    String getPhoneNumber();

    String getPositionId();

    String getImage();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    Integer getTotalCost();

    Integer getTotalRevenue();

    String getValueEn();

    String getValueVi();

    String getNameSpecialty();
}
