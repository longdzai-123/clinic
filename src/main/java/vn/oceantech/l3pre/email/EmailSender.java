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
                    "<html>" +
                            "<head>" +
                            "    <style>" +
                            "        body {" +
                            "            font-family: Arial, sans-serif;" +
                            "            margin: 20px;" +
                            "            padding: 20px;" +
                            "            border: 1px solid #ccc;" +
                            "            border-radius: 10px;" +
                            "        }" +
                            "        h3 {" +
                            "            color: #007bff;" +
                            "        }" +
                            "        p {" +
                            "            margin-top: 10px;" +
                            "            line-height: 1.6;" +
                            "        }" +
                            "        div {" +
                            "            margin-bottom: 10px;" +
                            "        }" +
                            "        b {" +
                            "            color: #333;" +
                            "        }" +
                            "        a {" +
                            "            color: #007bff;" +
                            "            text-decoration: none;" +
                            "            font-weight: bold;" +
                            "        }" +
                            "        a:hover {" +
                            "            text-decoration: underline;" +
                            "        }" +
                            "    </style>" +
                            "</head>" +
                            "<body>" +
                            "    <h3>Xin chào " + bookingDto.getPatientName() + "</h3>" +
                            "    <p>Bạn nhận được email này vì đã đặt lịch khám bệnh online trên Booking Care</p>" +
                            "    <p><b>Thông tin đặt lịch khám bệnh:</b></p>" +
                            "    <div><b>Thời gian:</b> " + bookingDto.getTimeBooking() + "</div>" +
                            "    <div><b>Bác sỹ:</b> " + doctorName + "</div>" +
                            "    <p>Nếu các thông tin trên là đúng sự thật, vui lòng click vào đường link bên dưới để xác nhận:</p>" +
                            "    <p><a href=\"" + UrlConfirmBooking(bookingDto.getVerifyBooking(), bookingDto.getDoctor().getId()) + "\">Xác nhận thông tin ở đây</a></p>" +
                            "    <div>Xin chân thành cảm ơn</div>" +
                            "</body>" +
                            "</html>"
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

    public void sendEmailBookingCancel(BookingDto bookingDto, String doctorName) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());
            helper.setTo(bookingDto.getEmail());
            helper.setFrom("longdybala12345@gmail.com");
            helper.setSubject("Lịch hẹn đã bị hủy");
            helper.setText(
                    "<html><head><style>" +
                            "body { font-family: 'Arial', sans-serif; }" +
                            ".container { max-width: 600px; margin: 0 auto; padding: 20px; }" +
                            ".header { background-color: #3498db; color: #fff; text-align: center; padding: 10px; }" +
                            ".content { margin-top: 20px; }" +
                            ".footer { margin-top: 20px; text-align: center; }" +
                            "</style></head><body>" +
                            "<div class='container'>" +
                            "<div class='header'><h2>Lịch hẹn đã bị hủy</h2></div>" +
                            "<div class='content'>" +
                            "<p>Xin chào " + bookingDto.getPatientName() + ",</p>" +
                            "<p>Lịch hẹn của bạn với bác sỹ " + doctorName +
                            " vào ngày " + bookingDto.getDate() +
                            " lúc " + bookingDto.getTimeType().getValueVi() +
                            " đã bị hủy. Nguyên nhân có thể là do bác sỹ bận hoặc đã vượt quá số lượng người khám trong khung giờ này.</p>" +
                            "<p>Chúng tôi xin lỗi vì sự bất tiện này. Bạn vui lòng chọn một khung giờ khác hoặc liên hệ với chúng tôi để được hỗ trợ.</p>" +
                            "</div>" +
                            "<div class='footer'><p>Trân trọng,<br>Đội ngũ quản lý lịch hẹn</p></div>" +
                            "</div></body></html>", true);
            javaMailSender.send(message);
            log.info("Send email cancel schedule a medical examination {} ", bookingDto.getEmail());
        } catch (MessagingException e) {
            log.error("Email sent with error: " + e.getMessage());
        }
    }
}
