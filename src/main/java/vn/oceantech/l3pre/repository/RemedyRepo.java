package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.oceantech.l3pre.entity.Remedy;

public interface RemedyRepo extends JpaRepository<Remedy, Integer> {
    @Query(value = "SELECT * FROM remedy r WHERE r.booking_id = :bookingId", nativeQuery = true)
    Remedy getByBookingId(Integer bookingId);
}
