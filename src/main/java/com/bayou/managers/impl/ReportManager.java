package com.bayou.managers.impl;

import com.bayou.validators.ReportValidator;
import com.bayou.engines.ReportEngine;
import com.bayou.exceptions.ValidationException;
import com.bayou.views.ReportView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

/**
 * Created by joshuaeaton on 4/5/17.
 */
@Service
public class ReportManager {

    @Autowired
    ReportEngine engine;
    @Autowired
    ReportValidator validator;

    /*Logic: checks if an ad report, if so it submits it, else it checks if it is a user report, if so then report else check if a valid
      bug report, if so then submit else throw validation exception as the submitted report is none of these cases.*/
    public void submitReportEmail(ReportView reportView) throws IOException, ValidationException {

        try {
            if(reportView.getAdvertisementId() != null) {   //checks if valid ad report
                if(validator.isValidAdReport(reportView)) {
                    engine.emailAdReport(reportView); //ad id not null so a ad report
                }  else { throw new ValidationException("ERROR: Not a valid ad report");}
            } else {
                if(validator.isValidUserReport(reportView)) {   //checks if valid user report
                    engine.emailUserReport(reportView); //user report
                } else if(validator.isValidBugReport(reportView)){  //checks if valid bug report
                    engine.emailBugReport(reportView); //bug report
                }
                else { throw new ValidationException("ERROR: Not a valid bug, ad, or user report");}
            }
        } catch (IOException e) { throw new IOException(); }
    }
}
