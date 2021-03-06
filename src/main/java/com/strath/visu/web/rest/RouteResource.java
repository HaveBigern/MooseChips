package com.strath.visu.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.strath.visu.domain.Route;
import com.strath.visu.domain.Type;
import com.strath.visu.repository.RouteRepository;
import com.strath.visu.service.AverageDataService;
import com.strath.visu.web.rest.dto.RouteDTO;

/**
 * REST controller for managing Route.
 */
@RestController
@RequestMapping("/api")
public class RouteResource {

    private final Logger log = LoggerFactory.getLogger(RouteResource.class);

    @Inject
    private RouteRepository routeRepository;

    @Inject
    private AverageDataService avgDataService;
    
    /**
     * POST  /routes -> Create a new route.
     */
    @RequestMapping(value = "/routes",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Route> createRoute(@RequestBody Route route) throws URISyntaxException {
        log.debug("REST request to save Route : {}", route);
        if (route.getRouteId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new route cannot already have an ID").body(null);
        }
        Route result = routeRepository.save(route);
        return ResponseEntity.created(new URI("/api/routes/" + result.getRouteId()))
            .body(result);
    }
    
    /**
     * GET  /averages -> get all the routes.
     */
    @RequestMapping(value = "/routes/averages",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RouteDTO> getAverageRoutes(@RequestParam(value = "id") Long id) {
        log.debug("REST request to get all Routes");
        Type type = avgDataService.createAndGetAverageRoutes(id);
        List<Route> finalRouteList = new ArrayList<>();
        if(type.getAverageRoute() != null) {
        	finalRouteList.add(type.getAverageRoute());
        }
		List<RouteDTO> routeDTOs = finalRouteList.stream()
                .map(route -> new RouteDTO(route))
                .collect(Collectors.toList());
        return routeDTOs;
    }

    /**
     * PUT  /routes -> Updates an existing route.
     */
    @RequestMapping(value = "/routes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Route> updateRoute(@RequestBody Route route) throws URISyntaxException {
        log.debug("REST request to update Route : {}", route);
        if (route.getRouteId() == null) {
            return createRoute(route);
        }
        Route result = routeRepository.save(route);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * GET  /routes -> get all the routes.
     */
    @RequestMapping(value = "/routes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Route> getAllRoutes() {
        log.debug("REST request to get all Routes");
        return routeRepository.findAll();
    }

    /**
     * GET  /routes/:id -> get the "id" route.
     */
    @RequestMapping(value = "/routes/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Route> getRoute(@PathVariable Long id) {
        log.debug("REST request to get Route : {}", id);
        return Optional.ofNullable(routeRepository.findOne(id))
            .map(route -> new ResponseEntity<>(
                route,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /routes/:id -> delete the "id" route.
     */
    @RequestMapping(value = "/routes/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        log.debug("REST request to delete Route : {}", id);
        routeRepository.delete(id);
		return null;
    }
}
