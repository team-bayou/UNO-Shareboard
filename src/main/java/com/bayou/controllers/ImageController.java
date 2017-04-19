package com.bayou.controllers;

import com.bayou.domains.Image;
import com.bayou.loggers.Loggable;
import com.bayou.managers.impl.ImageManager;
import com.bayou.views.ImageInfoView;
import com.bayou.views.ImageView;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Rachel on 3/19/2017.
 */
@RestController
@RequestMapping("service/v1/images")
public class ImageController {

    @Autowired
    private ImageManager manager;

    @Loggable
    @ApiOperation(value = "Get a image info by id", response = ResponseEntity.class)
    @RequestMapping(value = "/{id}/info", method = RequestMethod.GET)   //sets the mapping url and the HTTP method
    public ResponseEntity<ImageInfoView> getInfoById(@PathVariable("id") Long id) throws NotFoundException {

        ResponseEntity<ImageInfoView> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(manager.getInfo(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }
    @Loggable
    @ApiOperation(value = "Get image info by owner", response = ResponseEntity.class)
    @RequestMapping(value = "/owner/{id}/info", method = RequestMethod.GET)   //sets the mapping url and the HTTP method
    public ResponseEntity<List<ImageInfoView>> getInfoByOwner(@PathVariable("id") Long id) throws NotFoundException {
        ResponseEntity<List<ImageInfoView>> responseEntity;
        try {
            responseEntity = new ResponseEntity<List<ImageInfoView>>(manager.findByOwner(id), HttpStatus.OK);
        } catch(NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }
    @Loggable
    @ApiOperation(value = "Get a image by id", response = ResponseEntity.class)
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)   //sets the mapping url and the HTTP method
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
            response.setStatus(400);//HttpState.BAD_REQUEST
            return;
        }
    }
    @Loggable
    @ApiOperation(value = "Add an image", response = ResponseEntity.class)
    @RequestMapping(value = "/upload", method = RequestMethod.POST)   //sets the mapping url and the HTTP method
    public ResponseEntity<Long> uploadImage(@RequestParam("description") String desc, @RequestParam("owner") Long owner_id, @RequestParam("image_data") MultipartFile file) {
        ImageView view = new ImageView();
        try {
            view.setImageData(file.getBytes());
        } catch (IOException ioe) {
            return new ResponseEntity<Long>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        view.setDescription(desc);
        String contentType = file.getContentType();
        if(contentType.lastIndexOf("image/") == 0) {
            view.setImageMimeType(file.getContentType());
            view.setOwner(owner_id);
        } else {
            return new ResponseEntity<Long>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
        Long id = manager.add(view);
        ResponseEntity<Long> responseEntity;
        if (id > 0)
            responseEntity = new ResponseEntity<>(id, HttpStatus.OK);
        else
            responseEntity = new ResponseEntity<>(id, HttpStatus.CONFLICT);
        return responseEntity;
    }
    @Loggable
    @ApiOperation(value = "Update an image", response = ResponseEntity.class)
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody ImageView view) { // Image data and MIME type will not be changed
        ResponseEntity<Long> responseEntity;
        Long id = -1L;
        try {
            id = manager.update(view); //update the image, returns -1 if data is stale
            responseEntity = new ResponseEntity<>(id, HttpStatus.OK);
        } catch (javax.ws.rs.NotFoundException e) {  //catches the case of non-existent image
            System.out.println("Error: requested user does not exist");
            responseEntity = new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
        } catch (DataIntegrityViolationException e) {   //catches the case where for example an id is null thus implying a insert
            System.out.println("Error: can not determine if insert or update");
            responseEntity = new ResponseEntity<>(id, HttpStatus.BAD_REQUEST);
        }
        if (id == -1L) {    //catches the case if there was an attempt to update outdated information
            System.out.println("Error: stale data detected");
            responseEntity = new ResponseEntity<>(id, HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @Loggable
    @ApiOperation(value = "Delete a image", response = ResponseEntity.class)
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)   //sets the mapping url and the HTTP method
    public ResponseEntity delete(@PathVariable("id") Long id) {
        manager.delete(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<Long> handleMissingParams(MissingServletRequestPartException ex) {
        return new ResponseEntity<Long>(-1L, HttpStatus.BAD_REQUEST);
    }
}
