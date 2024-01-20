package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import vn.oceantech.l3pre.entity.RemedyDetails;

import java.util.List;

public interface RemedyDetailsRepo extends JpaRepository<RemedyDetails, Integer> {
    @Query(value = "SELECT * FROM remedy_details as rd WHERE rd.remedy_id=:remedyId", nativeQuery = true)
    List<RemedyDetails> getByRemedyId(Integer remedyId);

    @Modifying
    @Query(value = "DELETE FROM remedy_details as rd WHERE rd.remedy_id=:remedyId", nativeQuery = true)
    int deleteByRemedyId(Integer remedyId);
}
