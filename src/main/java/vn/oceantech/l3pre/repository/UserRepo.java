package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.oceantech.l3pre.entity.User;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {
    User getById(int id);

    boolean existsByEmail(String email);

    boolean existsById(int id);

    @Query("SELECT u FROM User as u WHERE u.email = :email")
    User getPatientIdByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.roleId = 'R2' ")
    List<User> getAllDoctor();


}
