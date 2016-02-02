package com.strath.visu.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.strath.visu.domain.DataClass;
import com.strath.visu.repository.DataClassRepository;

/**
 * REST controller for managing DataClass.
 */
@RestController
@RequestMapping("/api")
public class DataClassResource {

    private final Logger log = LoggerFactory.getLogger(DataClassResource.class);

    @Inject
    private DataClassRepository dataClassRepository;
    
    /**
     * GET  /dataClass/:routeId 
     */
    @RequestMapping(value = "/dataClass/routes/{routeId}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DataClass> getDataClassForRoute(@PathVariable Long routeId) {
        log.debug("REST request to get DataClass : {}", routeId);
        return dataClassRepository.getDataClassByRoute(routeId);     
    }
    
    /**
     * POST  /dataClasss -> Create a new dataClass.
     */
    @RequestMapping(value = "/dataClasss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataClass> createDataClass(@RequestBody DataClass dataClass) throws URISyntaxException {
        log.debug("REST request to save DataClass : {}", dataClass);
        if (dataClass.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new dataClass cannot already have an ID").body(null);
        }
        DataClass result = dataClassRepository.save(dataClass);
        return ResponseEntity.created(new URI("/api/dataClasss/" + result.getId()))
            .body(result);
    }

    /**
     * PUT  /dataClasss -> Updates an existing dataClass.
     */
    @RequestMapping(value = "/dataClasss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataClass> updateDataClass(@RequestBody DataClass dataClass) throws URISyntaxException {
        log.debug("REST request to update DataClass : {}", dataClass);
        if (dataClass.getId() == null) {
            return createDataClass(dataClass);
        }
        DataClass result = dataClassRepository.save(dataClass);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * GET  /dataClasss -> get all the dataClasss.
     */
    @RequestMapping(value = "/dataClasss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DataClass> getAllDataClasss() {
        log.debug("REST request to get all DataClasss");
        return dataClassRepository.findAll();
    }

    /**
     * GET  /dataClasss/:id -> get the "id" dataClass.
     */
    @RequestMapping(value = "/dataClasss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataClass> getDataClass(@PathVariable Long id) {
        log.debug("REST request to get DataClass : {}", id);
        return Optional.ofNullable(dataClassRepository.findOne(id))
            .map(dataClass -> new ResponseEntity<>(
                dataClass,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /dataClasss/:id -> delete the "id" dataClass.
     */
    @RequestMapping(value = "/dataClasss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteDataClass(@PathVariable Long id) {
        log.debug("REST request to delete DataClass : {}", id);
        dataClassRepository.delete(id);
        return ResponseEntity.ok().build();
    }
}
