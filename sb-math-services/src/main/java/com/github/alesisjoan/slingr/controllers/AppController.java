package com.github.alesisjoan.slingr.controllers;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/app")
public class AppController {

    @ApiOperation(value = "shows app info", nickname = "appHealthGet", notes = "", tags={ "info", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal error") })
    @RequestMapping(value = "/health",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<String> appHealthGet() {
        return ResponseEntity.ok("");
    }


    @ApiOperation(value = "shows last (n) requested expressions", nickname = "appLastGet", notes = "", tags={ "info", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal error") })
    @RequestMapping(value = "/last",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<String> appLastGet() {
        return ResponseEntity.ok("");
    }


    @ApiOperation(value = "shows top most requested expressions", nickname = "appTopGet", notes = "", tags={ "info", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal error") })
    @RequestMapping(value = "/top",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<String> appTopGet(){
        return ResponseEntity.ok("");
    }
}
