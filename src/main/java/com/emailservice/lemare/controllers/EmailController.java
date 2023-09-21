package com.emailservice.lemare.controllers;

import com.emailservice.lemare.dtos.EmailDto;
import com.emailservice.lemare.models.Email;
import com.emailservice.lemare.services.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EmailController {

    private final EmailService service;

    public EmailController(EmailService service) {
        this.service = service;
    }
    @PostMapping("/sending-email")
    public ResponseEntity<Email> sendEmail(@RequestBody @Valid EmailDto emailDto) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        service.sendEmail(email);
        return new ResponseEntity<>(email, HttpStatus.CREATED);

    }

}
