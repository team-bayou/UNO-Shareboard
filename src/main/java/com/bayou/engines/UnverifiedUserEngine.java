package com.bayou.engines;

import com.bayou.loggers.Loggable;
import com.sendgrid.*;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * Created by joshuaeaton on 2/23/17.
 */
@Component
public class UnverifiedUserEngine {
    @Loggable
    public void sendVerificationCode(String verCode, String recipient) throws IOException {
        Mail mail = new Mail(
                new Email("unoshareboard@uno.edu"), //set who the email is from
                "UNO Shareboard Verification Code", //set the subject of the email
                new Email(recipient), //set who the email is to be sent to
                new Content("text/html", "Verification Code: " + verCode + "\nVerification Link: "
                        + System.getenv("HEROKU_URL") + "/verify?email=" + recipient) //set the content of the email
        );
        mail.setTemplateId("ff14104f-0a13-4b81-8ccd-d470e6ab5d4d");
        Request request = new Request();
        request.method = Method.POST;
        request.endpoint = "mail/send"; //hits the endpoint on Sendgrid
        request.body = mail.build();    //builds the request body

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY")); //gets the api key for auth from env var
        Response response = sg.api(request); //makes the request
        System.out.println(response.statusCode);
        System.out.println(response.body);
        System.out.println(response.headers);
    }
}
