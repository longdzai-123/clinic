package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.oceantech.l3pre.entity.Clinic;

import java.util.List;

public interface ClinicRepo extends JpaRepository<Clinic, Integer> {
    @Query(value = "SELECT * FROM clinics", nativeQuery = true)
    List<Clinic> getAll();

    @Query(value = "SELECT * FROM clinics limit :limit", nativeQuery = true)
    List<Clinic> getAllByLimit(@Param("limit") int limit);

    Clinic getById(int id);

    @Query(value = "SELECT * FROM clinics c WHERE c.name LIKE CONCAT('%',:nameClinic,'%') " +
            "AND c.address LIKE CONCAT('%',:nameAddress,'%')", nativeQuery = true)
    List<Clinic> searchByNameAndAddress(String nameClinic, String nameAddress);
}
