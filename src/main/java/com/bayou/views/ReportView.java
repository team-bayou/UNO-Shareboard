package com.bayou.views;

/**
 * Created by joshuaeaton on 4/5/17.
 */
public class ReportView  {

    private Long reportingUserId;
    private Long offendingUserId;
    private Long advertisementId;
    private String comments;

    public Long getReportingUserId() { return reportingUserId; }

    public void setReportingUserId(Long reportingUserId) { this.reportingUserId = reportingUserId; }

    public Long getOffendingUserId() {
        return offendingUserId;
    }

    public void setOffendingUserId(Long offendingUserId) { this.offendingUserId = offendingUserId;}

    public Long getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(Long advertisementId) {
        this.advertisementId = advertisementId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
