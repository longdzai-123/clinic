package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.oceantech.l3pre.entity.Drug;
import vn.oceantech.l3pre.entity.UnitDrug;

import java.util.List;

public interface UnitDrugRepo extends JpaRepository<UnitDrug, Integer> {
    @Query("SELECT u FROM UnitDrug u")
    List<UnitDrug> getAll();
}
