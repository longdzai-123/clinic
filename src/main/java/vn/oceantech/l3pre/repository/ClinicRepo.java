package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.oceantech.l3pre.entity.Clinic;

import java.util.List;

public interface ClinicRepo extends JpaRepository<Clinic, Integer> {
    @Query(value = "SELECT * FROM clinics", nativeQuery = true)
    List<Clinic> getAll();

    Clinic getById(int id);
}
