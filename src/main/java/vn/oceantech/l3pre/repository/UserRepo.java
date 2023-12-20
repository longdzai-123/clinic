package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.oceantech.l3pre.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    User getById(int id);

    boolean existsByEmail(String email);
    boolean existsById(int id);
    @Query("SELECT u.id FROM User as u WHERE u.email = :email")
    int getPatientIdByEmail(String email);
}
