package com.bayou.views;

/**
 * Created by joshuaeaton on 4/5/17.
 */
public class ReportView  {



    private String reportingUserEmail;
    private String reportingUserName;
    private String offendingUser;
    private String comments;
    private Long advertisementId;


    public String getReportingUserEmail() { return reportingUserEmail; }

    public void setReportingUserEmail(String reportingUserEmail) { this.reportingUserEmail = reportingUserEmail; }

    public String getReportingUserName() {
        return reportingUserName;
    }

    public void setReportingUserName(String reportingUser) {
        this.reportingUserName = reportingUser;
    }

    public String getOffendingUser() {
        return offendingUser;
    }

    public void setOffendingUser(String offendingUser) {
        this.offendingUser = offendingUser;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(Long advertisementId) {
        this.advertisementId = advertisementId;
    }

}
