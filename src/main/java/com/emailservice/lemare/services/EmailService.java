package com.emailservice.lemare.services;

import com.emailservice.lemare.enums.StatusEmail;
import com.emailservice.lemare.models.Email;
import com.emailservice.lemare.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class EmailService {

   private final EmailRepository repository;

    public EmailService(EmailRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private JavaMailSender emailSender;

    @Transactional
    public Email sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e){
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return repository.save(email);
        }
    }
}
