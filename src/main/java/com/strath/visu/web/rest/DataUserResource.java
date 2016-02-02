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

import com.strath.visu.domain.DataUser;
import com.strath.visu.repository.DataUserRepository;

/**
 * REST controller for managing DataUser.
 */
@RestController
@RequestMapping("/api")
public class DataUserResource {

    private final Logger log = LoggerFactory.getLogger(DataUserResource.class);

    @Inject
    private DataUserRepository dataUserRepository;

    /**
     * POST  /dataUsers -> Create a new dataUser.
     */
    @RequestMapping(value = "/dataUsers",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataUser> createDataUser(@RequestBody DataUser dataUser) throws URISyntaxException {
        log.debug("REST request to save DataUser : {}", dataUser);
        if (dataUser.getDataUserId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new dataUser cannot already have an ID").body(null);
        }
        DataUser result = dataUserRepository.save(dataUser);
        return ResponseEntity.created(new URI("/api/dataUsers/" + result.getDataUserId()))
            .body(result);
    }

    /**
     * PUT  /dataUsers -> Updates an existing dataUser.
     */
    @RequestMapping(value = "/dataUsers",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataUser> updateDataUser(@RequestBody DataUser dataUser) throws URISyntaxException {
        log.debug("REST request to update DataUser : {}", dataUser);
        if (dataUser.getDataUserId() == null) {
            return createDataUser(dataUser);
        }
        DataUser result = dataUserRepository.save(dataUser);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * GET  /dataUsers -> get all the dataUsers.
     */
    @RequestMapping(value = "/dataUsers",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DataUser> getAllDataUsers() {
        log.debug("REST request to get all DataUsers");
        return dataUserRepository.findAll();
    }

    /**
     * GET  /dataUsers/:id -> get the "id" dataUser.
     */
    @RequestMapping(value = "/dataUsers/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataUser> getDataUser(@PathVariable Long id) {
        log.debug("REST request to get DataUser : {}", id);
        return Optional.ofNullable(dataUserRepository.findOne(id))
            .map(dataUser -> new ResponseEntity<>(
                dataUser,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /dataUsers/:id -> delete the "id" dataUser.
     */
    @RequestMapping(value = "/dataUsers/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteDataUser(@PathVariable Long id) {
        log.debug("REST request to delete DataUser : {}", id);
        dataUserRepository.delete(id);
        return ResponseEntity.ok().build();
    }
}
