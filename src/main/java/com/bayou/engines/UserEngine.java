package com.bayou.engines;

import com.bayou.domains.User;
import com.bayou.loggers.Loggable;
import com.bayou.views.EmailView;
import com.bayou.views.UserView;
import com.sendgrid.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Created by Rachel on 4/6/2017.
 */
@Component
public class UserEngine {
    @Loggable
    public void sendVerificationCode(String verCode, String recipient) throws IOException {
        Mail mail = new Mail(
                new Email("unoshareboard@uno.edu"), //set who the email is from
                "UNO Shareboard Verification Code", //set the subject of the email
                new Email(recipient), //set who the email is to be sent to
                new Content("text/plain", "Verification Code: " + verCode + "\nVerification Link: "
                        + System.getenv("HEROKU_URL") + "/resetpassword?email=" + recipient) //set the content of the email
        );

        send(mail);
    }


    //see for template format: http://stackoverflow.com/questions/37327375/send-mail-using-sendgrid-templates-from-java
    @Loggable
    public void emailUsers(List<UserView> users, EmailView view) throws IOException {

        for (UserView u: users) {
            Mail mail = prepareMessage(view.getSender(), view.getSubject(), u.getEmail(), new Content("text/html", view.getContent()));
            mail.setTemplateId("ff14104f-0a13-4b81-8ccd-d470e6ab5d4d");
            send(mail);
        }
    }

    @Loggable
    private Mail prepareMessage( String sender, String subject, String recipient , Content content ) {
        return new Mail(
                new Email(sender), //set who the email is from
                subject, //set the subject of the email
                new Email(recipient), //set who the email is to be sent to
                content //set the content of the email
        );
    }

    @Loggable
    private void send(Mail mail) throws IOException {
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
