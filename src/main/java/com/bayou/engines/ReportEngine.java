package com.bayou.engines;

import com.bayou.views.ReportView;
import com.sendgrid.*;
import org.springframework.stereotype.Component;
import java.io.IOException;

/**
 * Created by joshuaeaton on 4/5/17.
 */
@Component
public class ReportEngine {

    public void emailBugReport(ReportView view) throws IOException {

        Mail mail = new Mail(
                new Email("shareboard_user"+"@shareboard.app"), //set who the email is from
                "User Submitted Bug Report", //set the subject of the email
                new Email("unoshareboard.dev@gmail.com"), //set who the email is to be sent to
                new Content("text/plain", "Reporting User ID: " + view.getReportingUserId()+ "\nComments: " + view.getComments()) //set the content of the email
        );

        send(mail);
    }

    public void emailAdReport(ReportView view) throws IOException {

        Mail mail = new Mail(
                new Email("shareboard_user"+"@shareboard.app"), //set who the email is from
                "User Submitted Ad Report", //set the subject of the email
                new Email("unoshareboard.dev@gmail.com"), //set who the email is to be sent to
                new Content("text/plain", "Reporting User ID: " + view.getReportingUserId()+"\nAd ID: "+view.getAdvertisementId()+
                        "\nReported User ID: "+ view.getOffendingUserId()+ "\nComments: " + view.getComments()//set the content of the email
                ));

        send(mail);
    }

    public void emailUserReport(ReportView view) throws IOException {

        Mail mail = new Mail(
                new Email("shareboard_user"+"@shareboard.app"), //set who the email is from
                "User Submitted User Report", //set the subject of the email
                new Email("unoshareboard.dev@gmail.com"), //set who the email is to be sent to
                new Content("text/plain", "Reporting User ID: " + view.getReportingUserId()+"\nReported User ID: "+ view.getOffendingUserId()+ "\nComments: " + view.getComments() ));

        send(mail);
    }
    /*Handles the prep and sending of mail via Sendgrid API*/
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
