package com.bayou.managers.impl;

import com.bayou.domains.Review;
import com.bayou.engines.ReportEngine;
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

    public void submitReportEmail(ReportView reportView) throws IOException {
        try {
            engine.emailReport(reportView);
        } catch (IOException e) {
            throw new IOException();
        }
    }
}
