/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinerpo.tests;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.exception.StripeException;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Home
 */
public class NewClass {
    public static void main(String[] args) throws StripeException {

Stripe.apiKey = "sk_test_51Mi3N8H8VCrCI9bwP0OoITbSCXXTGSWX5G2jdBSwvfgUhWreuKQOIS95XXJXKBvOeKPWOP0IFotCVc3dNak12t3Z00ZA5WEzTB";
        Map<String,Object> customerParameter =new HashMap<String,Object>();
        customerParameter.put("email","b@gmail.com");
        Customer newCustomer= Customer.create(customerParameter);
        System.out.println(newCustomer.getId());
        



    }
    
}
