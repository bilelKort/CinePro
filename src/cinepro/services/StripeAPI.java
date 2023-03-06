/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinepro.services;

import cinepro.entities.*;
import cinepro.services.*;
import cinepro.utils.cineproConnexion;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.stripe.Stripe; 
import com.stripe.exception.StripeException; 
import com.stripe.model.*; 
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kortb
 */
public class StripeAPI {
    private static final String SECRET_KEY = "sk_test_51Mg6AtJaSn9cQDhSkftP7y0qpADPslDLX4zxLhBAV9JB1EEgpBEQNyiSUygxgtjfnP7yrbYCkbpxnKAHpY7nLZjU00gBZRaSlh";

    static {
        Stripe.apiKey = SECRET_KEY;
    }
    
 public static Customer createCustomerWithCard(String email, String name, String cardNumber, int expMonth, int expYear, String cvc) throws StripeException {
    // Create card object
    Map<String, Object> cardParams = new HashMap<>();
    cardParams.put("number", cardNumber);
    cardParams.put("exp_month", expMonth);
    cardParams.put("exp_year", expYear);
    cardParams.put("cvc", cvc);
    Card card = Card.create(cardParams);

    // Create token object using the card
    Map<String, Object> tokenParams = new HashMap<>();
    tokenParams.put("card", card.getId());
    Token token = Token.create(tokenParams);

    // Create customer object using the token
    Map<String, Object> customerParams = new HashMap<>();
    customerParams.put("email", email);
    customerParams.put("name", name);
    customerParams.put("source", token.getId());
    return Customer.create(customerParams);
}
 
 
       public static Charge createCharge(String token, int amount, String currency, String customerEmail) throws StripeException {
        Customer customer = Customer.list(new HashMap<String, Object>() {{
            put("email", customerEmail);
        }}).getData().get(0);

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", amount);
        chargeParams.put("currency", currency);
        chargeParams.put("source", token);
        chargeParams.put("customer", customer.getId());
        return Charge.create(chargeParams);
    }
}
