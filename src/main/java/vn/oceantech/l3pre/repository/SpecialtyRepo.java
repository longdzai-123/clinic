package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.oceantech.l3pre.entity.Specialty;

import java.util.List;

public interface SpecialtyRepo extends JpaRepository<Specialty, Integer> {
    @Query(value = "SELECT * FROM specialties  limit :limit", nativeQuery = true)
    List<Specialty> getAllByLimit(@Param("limit") int limit);

    @Query("SELECT s FROM Specialty s")
    List<Specialty> getAllSpecialty();

    @Query(value = "SELECT * FROM specialties as s WHERE s.name LIKE CONCAT('%',:keyWord,'%')", nativeQuery = true)
    List<Specialty> search(String keyWord);
}
