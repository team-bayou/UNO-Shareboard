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
    private String reportingUserName = "";
    private String offendingUser = "";
    private String comments;
    private Long advertisementId;


    public void emailReport(ReportView view) throws IOException {


        Mail mail = new Mail(
                new Email(view.getReportingUser()), //set who the email is from
                "User Submitted Report", //set the subject of the email
                new Email("unoshareboard.dev@gmail.com"), //set who the email is to be sent to
                new Content("text/plain", view.getReportingUser()+"'s" +" Comments" + view.getComments()) //set the content of the email
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
