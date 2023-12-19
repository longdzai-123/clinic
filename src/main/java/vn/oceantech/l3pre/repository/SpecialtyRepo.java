package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.oceantech.l3pre.entity.Specialty;

import java.util.List;

public interface SpecialtyRepo extends JpaRepository<Specialty, Integer> {
    @Query(value = "SELECT * FROM specialties", nativeQuery = true)
    List<Specialty> getAll();
}
