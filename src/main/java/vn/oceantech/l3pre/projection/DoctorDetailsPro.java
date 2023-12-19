package vn.oceantech.l3pre.projection;

import java.time.LocalDateTime;

public interface DoctorDetailsPro {
    Integer getId();
    Integer getDoctorId();
    Integer getSpecialtyId();
    Integer getClinicId();
    String getPriceId();
    String getProvinceId();
    String getPaymentId();
    String getAddressClinic();
    String getNameClinic();
    String getNote();
    Integer getCount();
    LocalDateTime getCreatedAt();
    String getPriceEn();
    String getPriceVi();
    String getProvinceEn();
    String getProvinceVi();
    String getPaymentEn();
    String getPaymentVi();

}
