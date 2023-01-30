package com.melita.weborder.service.notification;

import com.melita.weborder.model.EmailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    @Value("${spring.mail.username}") private String sender;

    private final JavaMailSender javaMailSender;

    @Override
    public boolean sendBasicMail(EmailDto emailDto) {
        try {

            SimpleMailMessage mailMessage
                            = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(emailDto.getRecipient());
            mailMessage.setText(emailDto.getMsgBody());
            mailMessage.setSubject(emailDto.getSubject());

            javaMailSender.send(mailMessage);
            log.info("Mail sent to agent with name {}", sender);
            return true;
        }

        // Sending e-mail probably will fail with the cause of fake credentials.
        catch (Exception e) {
            log.error("If valid email and SMTP server credentials are provided, the email sending process will be successful");
            return false;
        }
    }
}
