package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.oceantech.l3pre.entity.Booking;

import java.time.LocalDate;
import java.util.Date;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
    Booking getById(int id);

    @Query(value = "SELECT b FROM Booking as b WHERE b.verifyBooking = :token")
    Booking getByToken(String token);

    @Query(value = "SELECT count(b)>0 FROM Booking as b WHERE b.verifyBooking = :token AND b.doctorId = :doctorId AND b.statusId = 'S1' ")
    boolean existsByDoctorIdAndToken(String token, Integer doctorId);

    @Query("SELECT count(b)>0 FROM Booking as b WHERE b.doctorId = :doctorId " +
            "and b.date= :date " +
            "and b.patientId = :patientId " +
            "and b.timeType = :timeType")
    boolean checkDuplicateBooking(Integer doctorId, LocalDate date, Integer patientId, String timeType);
}
