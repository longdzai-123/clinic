package vn.oceantech.l3pre.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import vn.oceantech.l3pre.entity.AllCodes;
import vn.oceantech.l3pre.entity.Remedy;
import vn.oceantech.l3pre.entity.RemedyDetails;
import vn.oceantech.l3pre.repository.AllCodesRepo;
import vn.oceantech.l3pre.repository.RemedyDetailsRepo;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SendRemedy {
    private final JavaMailSender javaMailSender;
    private final AllCodesRepo allCodesRepo;
    private final RemedyDetailsRepo remedyDetailsRepo;

    public void sendRemedyImage(Remedy remedy) {
        AllCodes allCodes = allCodesRepo.getByKeyMap(remedy.getTimeType());
        String time = allCodes.getValueEn();
        String[] parts = remedy.getImage().split(",");
        String base64Data = parts.length > 1 ? parts[1] : parts[0];
        byte[] image = Base64.getDecoder().decode(base64Data);
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
            helper.setTo(remedy.getEmail());
            helper.setFrom("longdybala12345@gmail.com");
            helper.setSubject("Thông tin đơn thuốc");
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
                            "        h2 {" +
                            "            color: #333;" +
                            "        }" +
                            "        div {" +
                            "            margin-bottom: 10px;" +
                            "        }" +
                            "        b {" +
                            "            color: #007bff;" +
                            "        }" +
                            "        p {" +
                            "            margin-top: 10px;" +
                            "        }" +
                            "    </style>" +
                            "</head>" +
                            "<body>" +
                            "    <h2>Thông tin đơn thuốc</h2>" +
                            "    <div><b>Họ tên:</b> " + remedy.getBooking().getPatientName() + "</div>" +
                            "    <div><b>Địa chỉ:</b> " + remedy.getBooking().getPatientAddress() + "</div>" +
                            "    <div><b>Số điện thoại:</b> " + remedy.getPhoneNumber() + "</div>" +
                            "    <div><b>Ngày khám:</b> " + remedy.getDate() + "</div>" +
                            "    <div><b>Thời gian khám:</b> " + time + "</div>" +
                            "    <div><b>Bác sỹ:</b> " + remedy.getDoctor().getLastName() + " " + remedy.getDoctor().getFirstName() + "</div>" +
                            "    <p>Chuẩn đoán: " + remedy.getDescription() + "</p>" +
                            "    <p>Ghi chú: " + remedy.getNote() + "</p>" +
                            "    <p>Đơn thuốc nằm trong file đính kèm, vui lòng kiểm tra.</p>" +
                            "    <div>Xin chân thành cảm ơn</div>" +
                            "</body>" +
                            "</html>"
                    , true);
            helper.addAttachment("don_thuoc.jpg", new ByteArrayResource(image));
            javaMailSender.send(message);
            log.info("Send email remedy information {} ", remedy.getEmail());


        } catch (MessagingException e) {
            log.error("Email sent with error: " + e.getMessage());
        }
    }

    public void sendRemedy(Remedy remedy) {
        AllCodes allCodes = allCodesRepo.getByKeyMap(remedy.getTimeType());
        String time = allCodes.getValueEn();
        List<RemedyDetails> remedyDetails = remedyDetailsRepo.getByRemedyId(remedy.getId());
        StringBuilder remedyDetailsHtml = new StringBuilder("<ul>");
        for (RemedyDetails detail : remedyDetails) {
            remedyDetailsHtml.append("<li>")
                    .append(detail.getDrug().getName())
                    .append(": ")
                    .append(detail.getAmount())
                    .append("</li>").append("<p>").append(detail.getDescription()).append("</p>");
        }
        remedyDetailsHtml.append("</ul>");

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
            helper.setTo(remedy.getEmail());
            helper.setFrom("longdybala12345@gmail.com");
            helper.setSubject("Thông tin đơn thuốc");
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
                            "        h2 {" +
                            "            color: #333;" +
                            "        }" +
                            "        div {" +
                            "            margin-bottom: 10px;" +
                            "        }" +
                            "        b {" +
                            "            color: #007bff;" +
                            "        }" +
                            "        p {" +
                            "            margin-top: 10px;" +
                            "        }" +
                            "    </style>" +
                            "</head>" +
                            "<body>" +
                            "    <h2>Thông tin đơn thuốc</h2>" +
                            "    <div><b>Họ tên:</b> " + remedy.getBooking().getPatientName() + "</div>" +
                            "    <div><b>Địa chỉ:</b> " + remedy.getBooking().getPatientAddress() + "</div>" +
                            "    <div><b>Số điện thoại:</b> " + remedy.getPhoneNumber() + "</div>" +
                            "    <div><b>Ngày khám:</b> " + remedy.getDate() + "</div>" +
                            "    <div><b>Thời gian khám:</b> " + time + "</div>" +
                            "    <div><b>Bác sỹ:</b> " + remedy.getDoctor().getLastName() + " " + remedy.getDoctor().getFirstName() + "</div>" +
                            "    <p>Chuẩn đoán: " + remedy.getDescription() + "</p>" +
                            "    <p>Ghi chú: " + remedy.getNote() + "</p>" +
                            "    <p>Danh sách đơn thuốc:" + remedyDetailsHtml + "</p>" +
                            "    <div>Xin chân thành cảm ơn</div>" +
                            "</body>" +
                            "</html>"
                    , true);
            javaMailSender.send(message);
            log.info("Send email remedy information {} ", remedy.getEmail());
        } catch (MessagingException e) {
            log.error("Email sent with error: " + e.getMessage());
        }
    }
}
