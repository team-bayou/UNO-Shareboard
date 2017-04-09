package com.bayou.engines;

import com.sendgrid.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Rachel on 4/6/2017.
 */
@Component
public class UserEngine {
    public void sendVerificationCode(String verCode, String recipient) throws IOException {
        Mail mail = new Mail(
                new Email("unoshareboard@uno.edu"), //set who the email is from
                "UNO Shareboard Verification Code", //set the subject of the email
                new Email(recipient), //set who the email is to be sent to
                new Content("text/plain", "Verification Code: " + verCode + "\nVerification Link: "
                        + System.getenv("HEROKU_URL") + "/resetpassword?email=" + recipient) //set the content of the email
        );

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
