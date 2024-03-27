package com.trainer.ticketbooking.controller;

import com.stripe.exception.StripeException;
import com.trainer.ticketbooking.service.PaymentService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @RequestMapping("/checkout")
    public void checkout(@RequestBody String paymentBody) throws StripeException {
        paymentService.checkout("");
    }
}
