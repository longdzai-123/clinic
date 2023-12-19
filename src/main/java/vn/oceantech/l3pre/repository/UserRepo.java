package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.oceantech.l3pre.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    User getById(int id);
}
