package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.oceantech.l3pre.entity.Booking;

import java.time.LocalDate;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
    Booking getById(int id);

    @Query(value = "SELECT b FROM Booking as b WHERE b.verifyBooking = :token")
    Booking getByToken(String token);

    @Query(value = "SELECT count(1)>0 FROM bookings as b WHERE b.verify_booking = :token AND b.doctor_id = :doctorId AND b.status_id = 'S1' ", nativeQuery = true)
    int existsByDoctorIdAndToken(String token, Integer doctorId);

    @Query(value = "SELECT count(1)>0 FROM bookings as b WHERE b.doctor_id = :doctorId " +
            "and b.date= :date " +
            "and b.patient_id = :patientId " +
            "and b.time_type = :timeType", nativeQuery = true)
    int checkDuplicateBooking(Integer doctorId, LocalDate date, Integer patientId, String timeType);
}
