package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.oceantech.l3pre.entity.Drug;

public interface DrugRepo extends JpaRepository<Drug, Integer> {
    Drug getById(int id);
}
