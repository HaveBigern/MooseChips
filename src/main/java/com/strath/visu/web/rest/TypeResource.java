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

import com.strath.visu.domain.Type;
import com.strath.visu.repository.TypeRepository;

/**
 * REST controller for managing Type.
 */
@RestController
@RequestMapping("/api")
public class TypeResource {

    private final Logger log = LoggerFactory.getLogger(TypeResource.class);

    @Inject
    private TypeRepository typeRepository;

    /**
     * POST  /types -> Create a new type.
     */
    @RequestMapping(value = "/types",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Type> createType(@RequestBody Type type) throws URISyntaxException {
        log.debug("REST request to save Type : {}", type);
        if (type.getTypeId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new type cannot already have an ID").body(null);
        }
        Type result = typeRepository.save(type);
        return ResponseEntity.created(new URI("/api/types/" + result.getTypeId()))
            .body(result);
    }

    /**
     * PUT  /types -> Updates an existing type.
     */
    @RequestMapping(value = "/types",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Type> updateType(@RequestBody Type type) throws URISyntaxException {
        log.debug("REST request to update Type : {}", type);
        if (type.getTypeId() == null) {
            return createType(type);
        }
        Type result = typeRepository.save(type);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * GET  /types -> get all the types.
     */
    @RequestMapping(value = "/types",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Type> getAllTypes() {
        log.debug("REST request to get all Types");
        return typeRepository.findAll();
    }

    /**
     * GET  /types/:id -> get the "id" type.
     */
    @RequestMapping(value = "/types/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Type> getType(@PathVariable Long id) {
        log.debug("REST request to get Type : {}", id);
        return Optional.ofNullable(typeRepository.findOne(id))
            .map(type -> new ResponseEntity<>(
                type,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /types/:id -> delete the "id" type.
     */
    @RequestMapping(value = "/types/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteType(@PathVariable Long id) {
        log.debug("REST request to delete Type : {}", id);
        typeRepository.delete(id);
        return ResponseEntity.ok().build();
    }
}
