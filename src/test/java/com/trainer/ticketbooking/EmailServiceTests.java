package com.trainer.ticketbooking;

import com.trainer.ticketbooking.service.EmailService;
import org.junit.jupiter.api.Test;

//do not add @SpringBootTest here, causes entitymanager error
//used to test email service
public class EmailServiceTests {
    private EmailService emailService;

    //check if emails are valid using our regex
    @Test
    public void emailIsValidTestSuccess(){
        emailService = new EmailService();
        assert(emailService.emailIsValid("wowowow@yahoo.com"));
    }

    //emails should not be valid here
    @Test
    public void emailIsValidTestFail(){
        emailService = new EmailService();
        assert(!emailService.emailIsValid("wowow3RQR3Qowq3r@3qrqry2353qahoo.q3rq3com"));
    }
}
