package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.oceantech.l3pre.entity.LimitBooking;

public interface LimitBookingRepo extends JpaRepository<LimitBooking, Integer> {
}
