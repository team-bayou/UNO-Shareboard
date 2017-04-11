package com.bayou.validators;

import com.bayou.views.ReportView;
import org.springframework.stereotype.Component;

/**
 * Created by joshuaeaton on 4/9/17.
 */
@Component
public class ReportValidator {


    public Boolean isValidBugReport(ReportView view) {
        if(view.getComments() == null) {
            return false;
        }
        if(view.getReportingUserId() == null) {
            return false;
        }

        return true;
    }

    public Boolean isValidAdReport(ReportView view){
        if(view.getComments() == null) {
            return false;
        }
        if(view.getReportingUserId() == null) {
            return false;
        }
        if(view.getOffendingUserId() == null) {
            return false;
        }
        if(view.getAdvertisementId() == null) {
            return false;
        }

        return true;
    }

    public Boolean isValidUserReport(ReportView view) {
        if(view.getComments() == null) {
            return false;
        }
        if(view.getReportingUserId() == null) {
            return false;
        }
        if(view.getOffendingUserId() == null) {
            return false;
        }

        return true;
    }
}
