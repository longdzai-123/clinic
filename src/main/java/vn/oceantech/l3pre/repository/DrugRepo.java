package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.oceantech.l3pre.entity.Drug;

import java.util.List;

public interface DrugRepo extends JpaRepository<Drug, Integer> {
    Drug getById(int id);

    @Query(value = "SELECT * FROM drugs d WHERE d.name LIKE CONCAT('%',:keyWord,'%')", nativeQuery = true)
    List<Drug> search(String keyWord);
}
