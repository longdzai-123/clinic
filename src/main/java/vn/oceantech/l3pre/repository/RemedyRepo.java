package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.oceantech.l3pre.entity.Remedy;

public interface RemedyRepo extends JpaRepository<Remedy, Integer> {
}
