package kz.muit.oynaap.controllers;

import com.google.gson.annotations.SerializedName;
import kz.muit.oynaap.service.CartService;
import kz.muit.oynaap.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentRestController {

    @Autowired
    private OrderService orderSvc;

    @Autowired
    private CartService cartSvc;

    static class CreatePayment {
        @SerializedName("items")
        Object[] items;

        public Object[] getItems() {
            return items;
        }
    }

    static class CreatePaymentResponse {
        private final String clientSecret;

        public CreatePaymentResponse(String clientSecret) {
            this.clientSecret = clientSecret;
        }
    }


}
