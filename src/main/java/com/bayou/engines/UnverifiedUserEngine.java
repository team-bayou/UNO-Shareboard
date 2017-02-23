package com.bayou.engines;
import com.sen
import org.springframework.stereotype.Component;



/**
 * Created by joshuaeaton on 2/23/17.
 */
@Component
public class UnverifiedUserEngine {


    public void sendVerificationCode(String verCode) {
        SendGrid sendgrid = new SendGrid("SENDGRID_APIKEY");

        SendGrid.Email email = new SendGrid.Email();

        email.addTo("test@sendgrid.com");
        email.setFrom("you@youremail.com");
        email.setSubject("Sending with SendGrid is Fun");
        email.setHtml("and easy to do anywhere, even with Java");

        SendGrid.Response response = sendgrid.send(email);
    }

}
