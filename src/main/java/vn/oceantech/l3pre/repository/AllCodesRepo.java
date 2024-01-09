package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.oceantech.l3pre.entity.AllCodes;

import java.util.List;

public interface AllCodesRepo extends JpaRepository<AllCodes, Integer> {
    @Query("SELECT a FROM AllCodes a WHERE a.type=:type")
    List<AllCodes> getAllCodeByType(String type);

    @Query("SELECT a FROM AllCodes a WHERE a.keyMap=:keyMap")
    AllCodes getByKeyMap(String keyMap);
}
