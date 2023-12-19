package vn.oceantech.l3pre.projection;

import java.util.Date;

public interface SchedulesPro {
    Integer getId();

    Integer getCurrentNumber();

    Integer getMaxNumber();

    Date getDate();

    String getTimeType();

    Integer getDoctorId();

    Date getCreatedAt();

    String getValueEn();

    String getValueVi();

    Integer getValue();
}
