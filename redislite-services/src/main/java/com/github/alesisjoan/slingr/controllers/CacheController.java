package com.github.alesisjoan.slingr.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.alesisjoan.slingr.domain.KeyValue;
import com.github.alesisjoan.slingr.services.CacheService;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayDeque;
import java.util.Map;

@RestController
public class CacheController {

    private static final Logger log = LoggerFactory
            .getLogger(CacheController.class);
    
    ObjectMapper mapper = new ObjectMapper();
    

    @Autowired
    CacheService cacheService;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/expressions", method = RequestMethod.GET)
    ResponseEntity<String> expressionsGet(@NotNull
                                        @Valid @RequestParam(value = "expression", required = true) String expression){
        
        String result = cacheService.get(expression);
        
        return new ResponseEntity<>(result, HttpStatus.OK);                                       
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/expressions/last", method = RequestMethod.GET)
    ResponseEntity<String> expressionsGet(){

        ArrayDeque<String> result = cacheService.getLast();

        String resultSerialized = "";

        try {
            resultSerialized = mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            log.error("Error while serializing result", e);
        }

        return new ResponseEntity<>(resultSerialized, HttpStatus.OK);
    }

    @RequestMapping(value = "/expressions",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.PUT)
    @CrossOrigin(origins = "*")
    ResponseEntity<Void> expressionsPut(@ApiParam(value = "KeyValue Object" ,required=true )  @Valid @RequestBody KeyValue body) {

        cacheService.put(body.getKey(), body.getValue());
        return new ResponseEntity(HttpStatus.OK);
    }
}
