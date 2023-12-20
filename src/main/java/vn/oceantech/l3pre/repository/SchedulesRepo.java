package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.oceantech.l3pre.entity.Schedules;
import vn.oceantech.l3pre.projection.SchedulesPro;

import java.util.Date;
import java.util.List;

@Repository
public interface SchedulesRepo extends JpaRepository<Schedules, Integer> {
    @Query(value = "SELECT * FROM schedules WHERE time_type = :timeType AND doctor_id = :doctorId AND date = :date ", nativeQuery = true)
    Schedules getByTimeTypeAndDoctorId(@Param("timeType") String timeType, @Param("doctorId") Integer doctorId, @Param("date") Date date);

    @Query(value = "SELECT COUNT(*) > 0 FROM schedules WHERE time_type = :timeType AND doctor_id = :doctorId AND date = :date ", nativeQuery = true)
    int existsSchedule(@Param("timeType") String timeType, @Param("doctorId") Integer doctorId, @Param("date") Date date);

    @Query(value = "SELECT " +
            "s.id as id, " +
            "s.current_number as currentNumber," +
            "s.max_number as maxNumber," +
            "s.date as date," +
            "s.time_type as timeType," +
            "s.doctor_id as doctorId," +
            "s.created_at as createdAt, " +
            "a.value_en as valueEn," +
            "a.value_vi as valueVi,a.value as value " +
            "FROM schedules s " +
            "LEFT JOIN allcodes a ON s.time_type = a.key_map " +
            "WHERE doctor_id = :doctorId AND date = :date", nativeQuery = true)
    List<SchedulesPro> getSchedulesDoctorByDate(@Param("doctorId") Integer doctorId, @Param("date") Date date);

}
