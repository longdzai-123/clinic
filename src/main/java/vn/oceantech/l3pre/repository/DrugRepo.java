package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.oceantech.l3pre.entity.Drug;

import java.util.List;

public interface DrugRepo extends JpaRepository<Drug, Integer> {
    @Query("SELECT d FROM Drug d WHERE d.id = :id")
    Drug getById(int id);

    @Query("SELECT d FROM Drug d")
    List<Drug> getAll();

    @Query(value = "SELECT * FROM drugs d WHERE d.name LIKE CONCAT('%',:keyWord,'%')", nativeQuery = true)
    List<Drug> search(String keyWord);
}
