package com.bayou.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by joshuaeaton on 1/27/17.
 */
@RestController
@RequestMapping("service/v1")
public class SimpleController {


    //To test use http://localhost:8090/service/v1/stringResponse
    @RequestMapping(value = "/stringResponse", method = RequestMethod.GET)   //sets the mapping url and the HTTP method
    public ResponseEntity<String> getStringResponse() {

        return new ResponseEntity<String>("Success" , HttpStatus.OK);
    }

    //To test use http://localhost:8090/service/v1/stringResponseCustom?name=<your name>
    @RequestMapping(value = "/stringResponseCustom", method = RequestMethod.GET)    //sets the mapping url and the HTTP method
    public ResponseEntity<String> getStringResponseCustom(@RequestParam(value="name", defaultValue="Bob") String name) {    //@RequestParam sets the var name to give a string arg

        return new ResponseEntity<String>("Success " + name , HttpStatus.OK);   //return a new ResponseEntity with the body containing the given String in the request
    }

}
