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


    private String reportingUserEmail;
    private String reportingUserName;
    private String offendingUserId;
    private String comments;
    private Long advertisementId;

    private void constructMessageData(ReportView view) {

        if(view.getReportingUserName() != null) {
            reportingUserName = "Reporter Username: " + view.getReportingUserName();
        } else {
            reportingUserName = "";
        }
        if(view.getAdvertisementId() != null) {
            advertisementId = view.getAdvertisementId();
        }
        if(view.getOffendingUserId() != null) {
            offendingUserId = "Reported User ID: " + view.getOffendingUserId();
        }

        /*Only two variables required when doing a bug report*/
        reportingUserEmail = "Reporting User Email: " + view.getReportingUserEmail();
        comments = "Comments: " + view.getComments();

    }

    public void emailBugReport(ReportView view) throws IOException {

        constructMessageData(view); //initializes the variables with data from the report

        Mail mail = new Mail(
                new Email(view.getReportingUserEmail()), //set who the email is from
                "User Submitted Bug Report", //set the subject of the email
                new Email("unoshareboard.dev@gmail.com"), //set who the email is to be sent to
                new Content("text/plain", reportingUserName +"\n"+ reportingUserEmail+"'s\n" +"\n"+ comments) //set the content of the email
        );

        send(mail);
    }

    public void emailAdReport(ReportView view) throws IOException {

        constructMessageData(view); //initializes the variables with data from the report

        Mail mail = new Mail(
                new Email(view.getReportingUserEmail()), //set who the email is from
                "User Submitted Ad Report", //set the subject of the email
                new Email("unoshareboard.dev@gmail.com"), //set who the email is to be sent to
                new Content("text/plain",
                        reportingUserName +"\n"+ reportingUserEmail+"'s" +"\n"+offendingUserId+"\n"+"Advertisement Id: "+
                                advertisementId+"\n"+
                                "\n" + comments) //set the content of the email
        );

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
