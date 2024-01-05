package vn.oceantech.l3pre.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import vn.oceantech.l3pre.dto.BookingDto;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailSender {
    private final JavaMailSender javaMailSender;

    public void sendEmailBooking(BookingDto bookingDto, String doctorName) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());
            helper.setTo(bookingDto.getEmail());
            helper.setFrom("longdybala12345@gmail.com");
            helper.setSubject("Thông tin đặt lịch khám bệnh");
            helper.setText(
                    "<h3> Xin chào " + bookingDto.getPatientName() + "</h3>" +
                            "<p>Bạn nhận được email này vì đã đặt lịch khám bệnh online trên booking care</p>" +
                            "<p>Thông tin đặt lịch khám bệnh:</p>" +
                            "<div><b>Thời gian:" + bookingDto.getTimeBooking() + "</b></div>" +
                            "<div><b>Bác sỹ:" + doctorName + "</b></div>" +
                            "<p>Nếu các thông tin trên là đúng sự thật, vui lòng click vào đường link bên dưới để xác nhận</p>" +
                            "<a href=" + UrlConfirmBooking(bookingDto.getVerifyBooking(), bookingDto.getDoctor().getId()) + ">Xác nhận thông tin ở đây</a>" +
                            "<div>Xin chân thành cảm ơn</div>"
                    , true);
            javaMailSender.send(message);
            log.info("Send email confirm schedule a medical examination {} ", bookingDto.getEmail());
        } catch (MessagingException e) {
            log.error("Email sent with error: " + e.getMessage());
        }
    }

    private String UrlConfirmBooking(String token, int doctorId) {
        return "http://localhost:3000/verify-booking?token=" + token + "&doctorId=" + doctorId;
    }
}
