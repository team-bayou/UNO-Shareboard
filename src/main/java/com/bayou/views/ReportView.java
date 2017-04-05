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

    public String getEmail() {
        return reportingUserEmail;
    }

    public void setEmail(String email) {
        this.reportingUserEmail = email;
    }

    public String getReportingUser() {
        return reportingUserName;
    }

    public void setReportingUser(String reportingUser) {
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
