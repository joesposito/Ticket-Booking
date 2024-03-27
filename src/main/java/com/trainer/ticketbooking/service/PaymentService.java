package com.trainer.ticketbooking.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class PaymentService {
    @Value("${STRIPE_SECRET_KEY}")
    private String stripePrivateKey;

    //took way too long to realize - @values are not injected until AFTER the bean is created
    @PostConstruct
    public void init(){
        Stripe.apiKey = stripePrivateKey;
    }

    public void checkout(String payment) throws StripeException {
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(15 * 100L)
                        .setCurrency("usd")
                        .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);
        System.out.println(paymentIntent);
    }
}
