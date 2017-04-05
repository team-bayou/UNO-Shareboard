package com.bayou.controllers;

import com.bayou.managers.impl.ReportManager;
import com.bayou.views.ReportView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by joshuaeaton on 4/5/17.
 */
@RestController
@RequestMapping("service/v1/reports")
public class ReportController {

    @Autowired
    ReportManager manager;

    @ApiOperation(value = "Submit a issue report", response = ResponseEntity.class)
    @RequestMapping(value = "/submit", method = RequestMethod.POST)   //sets the mapping url and the HTTP method
    public ResponseEntity<Long> submit(@RequestBody ReportView view) {

        try {
            manager.submitReportEmail(view);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.OK);

        return responseEntity;
    }
}
