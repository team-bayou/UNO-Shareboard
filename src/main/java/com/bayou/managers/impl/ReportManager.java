package com.bayou.managers.impl;

import com.bayou.Validators.ReportValidator;
import com.bayou.domains.Review;
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

    public void submitReportEmail(ReportView reportView) throws IOException, ValidationException {


        try {
            if(reportView.getAdvertisementId() != null) {
                if(validator.isValidAdReport(reportView)) {
                    engine.emailAdReport(reportView); //ad id not null so a ad report
                }  else { throw new ValidationException("ERROR: Not a valid bug or ad report");}
            } else {
                if(validator.isValidBugReport(reportView)) {
                    engine.emailBugReport(reportView); //ad id null so a bug report
                }
                else { throw new ValidationException("ERROR: Not a valid bug or ad report");}
            }
        } catch (IOException e) {
            throw new IOException();
        }
    }
}
