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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
            response.setStatus(404); //HttpStatus.NOT_FOUND);
            return;
        } catch (IOException ioe) {
            response.setStatus(404);//HttpState.NOT_FOUND
            return;
        }
    }

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
        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }
}
