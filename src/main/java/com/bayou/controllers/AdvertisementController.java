package com.bayou.controllers;

import com.bayou.loggers.Loggable;
import com.bayou.managers.impl.AdvertisementManager;
import com.bayou.types.AdType;
import com.bayou.views.AdvertisementView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.NotFoundException;
import java.util.List;

/**
 * File: AdvertisementController
 * Package: com.bayou.controllers
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
@RestController
@RequestMapping("service/v1/advertisements")
public class AdvertisementController {
    @Autowired
    private AdvertisementManager manager;

    @Loggable
    @ApiOperation(value = "Get a list of advertisements", response = ResponseEntity.class)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<AdvertisementView>> getAll() throws NotFoundException {
        ResponseEntity<List<AdvertisementView>> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(manager.getAll(), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @Loggable
    @ApiOperation(value = "Get a list of advertisements by page number", response = ResponseEntity.class)
    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
    public ResponseEntity<List<AdvertisementView>> getAll(@PathVariable("page") Integer page) throws NotFoundException {
        ResponseEntity<List<AdvertisementView>> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(manager.getAll(page), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @Loggable
    @ApiOperation(value = "Get an advertisement by id", response = ResponseEntity.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AdvertisementView> get(@PathVariable("id") Long id) throws NotFoundException {
        ResponseEntity<AdvertisementView> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(manager.get(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }
    @Loggable
    @ApiOperation(value = "Get a list of user's advertisements", response = ResponseEntity.class)
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<AdvertisementView>> getUserAdvertisements(@PathVariable("id") Long id) throws NotFoundException {
        ResponseEntity<List<AdvertisementView>> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(manager.getAllByOwner(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }
    @Loggable
    @ApiOperation(value = "Get a list of user's advertisements by page number", response = ResponseEntity.class)
    @RequestMapping(value = "/users/{id}/page/{page}", method = RequestMethod.GET)
    public ResponseEntity<List<AdvertisementView>> getUserAdvertisements(@PathVariable("id") Long id,
                                                                         @PathVariable("page") Integer page) throws NotFoundException {
        ResponseEntity<List<AdvertisementView>> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(manager.getAllByOwner(id, page), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }
    @Loggable
    @ApiOperation(value = "Get a count of user's advertisements", response = ResponseEntity.class)
    @RequestMapping(value = "/users/count/{id}", method = RequestMethod.GET)
    public ResponseEntity<Integer> getUserAdvertisementCount(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<Integer>(manager.countByOwner(id), HttpStatus.OK);
    }
    @Loggable
    @ApiOperation(value = "Get a list of category's advertisements", response = ResponseEntity.class)
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<AdvertisementView>> getCategoryAdvertisements(@PathVariable("id") Long id) throws NotFoundException {
        ResponseEntity<List<AdvertisementView>> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(manager.getAllByCategory(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }
    @Loggable
    @ApiOperation(value = "Get a list of category's advertisements by page number", response = ResponseEntity.class)
    @RequestMapping(value = "/categories/{id}/page/{page}", method = RequestMethod.GET)
    public ResponseEntity<List<AdvertisementView>> getCategoryAdvertisements(@PathVariable("id") Long id,
                                                                             @PathVariable("page") Integer page) throws NotFoundException {
        ResponseEntity<List<AdvertisementView>> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(manager.getAllByCategory(id, page), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }
    @Loggable
    @ApiOperation(value = "Get a list of advertisements in the list of categories by page number", response = ResponseEntity.class)
    @RequestMapping(value = "/categoryList/page/{ids}/{page}", method = RequestMethod.GET)
    public ResponseEntity<List<AdvertisementView>> getCategoryAdvertisements(@PathVariable("id") Long[] ids,
                                                                             @PathVariable("page") Integer page) throws NotFoundException {
        System.out.println(ids.length);
        ResponseEntity<List<AdvertisementView>> responseEntity;
        try {
            //responseEntity = new ResponseEntity<List<AdvertisementView>>(HttpStatus.OK);
            responseEntity = new ResponseEntity<>(manager.getAllByCategories(ids, page), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }
    @Loggable
    @ApiOperation(value = "Search for advertisements", response = ResponseEntity.class)
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<List<AdvertisementView>> search(@RequestParam(value = "categoryId", required = false) Long[] categoryIds,
                                                          @RequestParam(value = "title", required = false) String title,
                                                          @RequestParam(value = "description", required = false) String desc,
                                                          @RequestParam(value = "adType", required = false) String type,
                                                          @RequestParam(value = "page") Integer page) {
        ResponseEntity<List<AdvertisementView>> responseEntity;
        if (page == null) {
            return new ResponseEntity<List<AdvertisementView>>(HttpStatus.BAD_REQUEST);
        }
        try {
            AdType adType = type == null ? null : Enum.valueOf(AdType.class, type);
            List<AdvertisementView> adList = manager.search(categoryIds, title, desc, adType, page);
            HttpStatus status;
            if (adList.size() == 0) {
                status = HttpStatus.NO_CONTENT;
            } else {
                status = HttpStatus.OK;
            }
            responseEntity = new ResponseEntity<List<AdvertisementView>>(adList, status);
        } catch (IllegalArgumentException iae) {
            responseEntity = new ResponseEntity<List<AdvertisementView>>(HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }
    @Loggable
    @ApiOperation(value = "Add an advertisement", response = ResponseEntity.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Long> add(@RequestBody AdvertisementView view) {
        Long id = manager.add(view);

        ResponseEntity<Long> responseEntity;
        if (id > 0)
            responseEntity = new ResponseEntity<>(id, HttpStatus.OK);
        else
            responseEntity = new ResponseEntity<>(id, HttpStatus.CONFLICT);

        return responseEntity;
    }
    @Loggable
    @ApiOperation(value = "Add Image to an Advertisement", response = ResponseEntity.class)
    @RequestMapping(value = "/addImage/{adID}/{imageID}", method = RequestMethod.PUT)
    public ResponseEntity<Integer> addImage(@PathVariable("adID") Long adID, @PathVariable("imageID") Long imageID) {
        try {
            manager.addImage(adID, imageID);
        } catch (NotFoundException nfe) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Loggable
    @ApiOperation(value = "Remove Image from an Advertisement", response = ResponseEntity.class)
    @RequestMapping(value = "/removeImage/{adID}/{imageID}", method = RequestMethod.PUT)
    public ResponseEntity<Integer> removeImage(@PathVariable("adID") Long adID, @PathVariable("imageID") Long imageID) {
        try {
            manager.removeImage(adID, imageID);
        } catch (NotFoundException nfe) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Loggable
    @ApiOperation(value = "Update an advertisement", response = ResponseEntity.class)
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody AdvertisementView view) {
        ResponseEntity<Long> responseEntity;
        Long id = -1L;

        try {
            id = manager.update(view); //update the advertisement, returns -1 if data is stale
            responseEntity = new ResponseEntity<>(id, HttpStatus.OK);
        } catch (javax.ws.rs.NotFoundException e) {  //catches the case of non-existent ad
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
    @ApiOperation(value = "Delete an advertisement", response = ResponseEntity.class)
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        manager.delete(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
