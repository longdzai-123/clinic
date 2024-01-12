package vn.oceantech.l3pre.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    SUCCESS(200, "Success"),
    BAD_REQUEST(400, "Bad request"),
    INVALID_VALUE(400_001, "Invalid value"),
    SAVE_DATABASE_ERROR(400_002, "Save database error"),
    FORBIDDEN(403, "Forbidden"),
    FORBIDDEN_ACTIVE(403_001, "Tài khoản chưa active"),
    NOT_FOUND(404, "Resource not found"),
    NOT_FOUND_USER(404_001, "The user isn't exist"),
    NOT_FOUND_SPECIALTY(404_002, "The specialty isn't exist"),
    NOT_FOUND_CLINIC(404_003, "The clinic isn't exist"),
    NOT_FOUND_DOCTOR_INFORMATION(404_004, "The doctor information isn't exist"),
    NOT_FOUND_REMEDY(404_005, "The remedy isn't exist"),
    NOT_FOUND_INVOICE(404_006, "The invoice isn't exist"),
    DUPLICATE_DATA(405, "Data duplicate"),
    DUPLICATE_EMAIL(405_001, "Your email is already in used, Please try another email ! "),
    DUPLICATE_SCHEDULES(405_002, "Schedule already exists, please make another appointment! "),
    DUPLICATE_DOCTOR_INFORMATION(405_003, "Doctor Information already exists, please make another appointment! "),
    DUPLICATE_BOOKING(405_004, "You have scheduled an appointment for this time period"),
    FILE_UPLOAD_ERROR(406, "File upload error"),
    NOT_ALLOW(407, "Not allow"),
    CONFIRM_BOOKING_ERROR(408,"Email confirmation of failed medical appointment booking")
    ;
    private final int code;
    private final String message;
}
