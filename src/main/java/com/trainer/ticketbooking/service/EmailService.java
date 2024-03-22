package com.trainer.ticketbooking.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@Component
public class EmailService {
    public EmailService(){}
    private final String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    public boolean emailIsValid(String email) {
        return Pattern.compile(regexPattern).matcher(email).matches();
    }
}
