package com.bayou.controllers;

import com.bayou.domains.Image;
import com.bayou.managers.impl.ImageManager;
import com.bayou.views.ImageInfoView;
import com.bayou.views.ImageView;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by Rachel on 3/19/2017.
 */
@RestController
@RequestMapping("service/v1/images")
public class ImageController {

    @Autowired
    private ImageManager manager;

    @ApiOperation(value = "Get a image info by id", response = ResponseEntity.class)
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)   //sets the mapping url and the HTTP method
    public ResponseEntity<ImageInfoView> getInfoById(@PathVariable("id") Long id) throws NotFoundException {

        ResponseEntity<ImageInfoView> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(manager.get(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Get a image by id", response = ResponseEntity.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)   //sets the mapping url and the HTTP method
    public void getById(@PathVariable("id") Long id, HttpServletResponse response) throws NotFoundException {
        ImageView view;
        try {
            view = manager.get(id);
            ByteArrayInputStream in = new ByteArrayInputStream(view.getImageData());
            response.setContentType(MediaType.parseMediaType(view.getImageMimeType()).toString());
            IOUtils.copy(in, response.getOutputStream());
        } catch (NotFoundException e) {
            response.setStatus(204); //HttpStatus.NO_CONTENT);
            return;
        } catch (IOException ioe) {
            response.setStatus(204);//HttpState.NO_CONTENT
            return;
        }
    }
}
