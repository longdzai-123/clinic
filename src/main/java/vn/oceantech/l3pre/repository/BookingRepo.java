package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.oceantech.l3pre.entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
    Booking getById(int id);
}
