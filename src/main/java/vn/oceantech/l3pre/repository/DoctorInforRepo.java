package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.oceantech.l3pre.entity.DoctorInformation;
import vn.oceantech.l3pre.projection.DoctorDetailsPro;
import vn.oceantech.l3pre.projection.DoctorProfilePro;
import vn.oceantech.l3pre.projection.TopDoctorPro;

import java.util.List;

public interface DoctorInforRepo extends JpaRepository<DoctorInformation, Integer> {
    DoctorInformation getById(int id);

    @Query(value = "SELECT * FROM doctor_information WHERE doctor_id = :id", nativeQuery = true)
    DoctorInformation getByDoctorId(int id);

    @Query(value = "SELECT * FROM doctor_information d WHERE d.specialty_id = :specialtyId " +
            "AND (:provinceId is null or d.province_id = :provinceId) ", nativeQuery = true)
    List<DoctorInformation> getBySpecialtyAndProvince(@Param("specialtyId") Integer specialtyId, @Param("provinceId") String provinceId);

    @Query(value = "SELECT " +
            "dif.id," +
            "dif.doctor_id as doctorId," +
            "dif.specialty_id as specialtyId," +
            "dif.clinic_id as clinicId," +
            "dif.price_id as priceId," +
            "dif.province_id as provinceId," +
            "dif.payment_id as paymentId," +
            "dif.address_clinic as addressClinic," +
            "dif.name_clinic as nameClinic," +
            "dif.note as note," +
            "dif.count as count," +
            "dif.created_at as createdAt," +
            "a1.value_en as priceEn," +
            "a1.value_vi as priceVi," +
            "a2.value_en as provinceEn," +
            "a2.value_vi as provinceVi," +
            "a3.value_en as paymentEn," +
            "a3.value_vi as paymentVi " +
            "FROM doctor_information dif " +
            "LEFT JOIN allcodes a1 ON dif.price_id = a1.key_map " +
            "LEFT JOIN allcodes a2 ON dif.province_id = a2.key_map " +
            "LEFT JOIN allcodes a3 ON dif.payment_id = a3.key_map " +
            "WHERE doctor_id = :doctorId", nativeQuery = true)
    DoctorDetailsPro getDoctorDetailByDoctorId(@Param("doctorId") Integer doctorId);

    @Query(value = "SELECT " +
            "u.id, " +
            "u.email," +
            "u.first_name as firstName," +
            "u.last_name as lastName," +
            "u.address," +
            "u.gender," +
            "u.role_id as roleId," +
            "u.phone_number as phoneNumber," +
            "u.position_id as positionId," +
            "u.image," +
            "u.total_cost as totalCost," +
            "u.total_revenue as totalRevenue," +
            "a4.value_en as positionEn," +
            "a4.value_vi as positionVi," +
            "dif.doctor_id as doctorId," +
            "dif.specialty_id as specialtyId," +
            "dif.clinic_id as clinicId," +
            "dif.price_id as priceId," +
            "dif.province_id as provinceId," +
            "dif.payment_id as paymentId," +
            "dif.address_clinic as addressClinic," +
            "dif.name_clinic as nameClinic," +
            "dif.note as note," +
            "dif.count as count," +
            "a1.value_en as priceEn," +
            "a1.value_vi as priceVi," +
            "a2.value_en as provinceEn," +
            "a2.value_vi as provinceVi," +
            "a3.value_en as paymentEn," +
            "a3.value_vi as paymentVi, " +
            "m.description " +
            "FROM doctor_information dif " +
            "LEFT JOIN allcodes a1 ON dif.price_id = a1.key_map " +
            "LEFT JOIN allcodes a2 ON dif.province_id = a2.key_map " +
            "LEFT JOIN allcodes a3 ON dif.payment_id = a3.key_map " +
            "LEFT JOIN users u ON u.id = dif.doctor_id " +
            "LEFT JOIN allcodes a4 ON u.position_id = a4.key_map " +
            "LEFT JOIN markdowns m ON dif.doctor_id = m.doctor_id " +
            "WHERE dif.doctor_id = ?1 ", nativeQuery = true)
    DoctorProfilePro getDoctorProfileByDoctorId(@Param("doctorId") Integer doctorId);

    @Query(value = "SELECT " +
            "u.id," +
            "u.email," +
            "u.first_name as firstName," +
            "u.last_name as lastName," +
            "u.address," +
            "u.gender," +
            "u.role_id as roleId," +
            "u.phone_number as phoneNumber," +
            "u.position_id as positionId," +
            "u.image," +
            "u.created_at as createdAt," +
            "u.updated_at as updatedAt," +
            "u.total_cost as totalCost," +
            "u.total_revenue as totalRevenue," +
            "a.value_en as valueEn," +
            "a.value_vi as valueVi," +
            "s.name as nameSpecialty " +
            "FROM " +
            "users u " +
            "LEFT JOIN allcodes as a ON a.key_map = u.position_id " +
            "LEFT JOIN doctor_information as d ON d.doctor_id = u.id " +
            "LEFT JOIN specialties as s ON s.id = d.specialty_id " +
            "WHERE " +
            "u.role_id = 'R2' " +
            "AND u.is_active= true " +
            "ORDER BY u.created_at DESC " +
            "LIMIT :limit", nativeQuery = true)
    List<TopDoctorPro> getTopDoctor(@Param("limit") Integer limit);

    @Query(value = "SELECT " +
            "u.id," +
            "u.email," +
            "u.first_name as firstName," +
            "u.last_name as lastName," +
            "u.address," +
            "u.gender," +
            "u.role_id as roleId," +
            "u.phone_number as phoneNumber," +
            "u.position_id as positionId," +
            "u.image," +
            "u.created_at as createdAt," +
            "u.updated_at as updatedAt," +
            "u.total_cost as totalCost," +
            "u.total_revenue as totalRevenue," +
            "a.value_en as valueEn," +
            "a.value_vi as valueVi," +
            "s.name as nameSpecialty " +
            "FROM " +
            "users u " +
            "LEFT JOIN allcodes as a ON a.key_map = u.position_id " +
            "LEFT JOIN doctor_information as d ON d.doctor_id = u.id " +
            "LEFT JOIN specialties as s ON s.id = d.specialty_id " +
            "WHERE " +
            "u.role_id = 'R2' " +
            "AND u.is_active= true " +
            "ORDER BY u.created_at DESC ", nativeQuery = true)
    List<TopDoctorPro> getAllDoctor();

}
