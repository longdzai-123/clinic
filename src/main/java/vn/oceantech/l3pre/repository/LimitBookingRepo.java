package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import vn.oceantech.l3pre.entity.LimitBooking;

public interface LimitBookingRepo extends JpaRepository<LimitBooking, Integer> {

    //int getNumberLimit(@Param("doctorId") Integer doctorId, @Param("timeType") String timeType);
}
