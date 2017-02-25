package com.bayou.engines;


import com.sendgrid.*;
import org.springframework.stereotype.Component;
import java.io.IOException;


/**
 * Created by joshuaeaton on 2/23/17.
 */
@Component
public class UnverifiedUserEngine {


    public void sendVerificationCode(String verCode , String recipient ) throws IOException {
        Email from = new Email("unoshareboard@uno.edu");   //set who the email is from
        String subject = "UNO Shareboard Verification Code"; //set the subject of the email
        Email to = new Email(recipient); //set who the email is to be sent to
        Content content = new Content("text/plain", "Verification Code: " +verCode+
                "\nVerification Page: https://uno-shareboard-prod.herokuapp.com/ ");   //set the content of the email
        Mail mail = new Mail(from, subject, to, content); //initialize a mail Object

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY")); //gets the api key for auth from env var
        Request request = new Request();
        try {
            request.method = Method.POST; //specifies the HTTP Verb
            request.endpoint = "mail/send"; //hits the endpoint on Sendgrid
            request.body = mail.build();    //builds the request body
            Response response = sg.api(request); //makes the request
            System.out.println(response.statusCode); //print out status code
            System.out.println(response.body);  //print out body
            System.out.println(response.headers); //print out headers
        } catch (IOException ex) {  //handles IO exceptions
            throw ex; //throw an exception if there was a IO exception generated
        }

    }
}
