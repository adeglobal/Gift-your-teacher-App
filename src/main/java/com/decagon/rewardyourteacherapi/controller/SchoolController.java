package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.service.SchoolService;
import com.decagon.rewardyourteacherapi.util.Responder;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/School")
public class SchoolController {
    private final SchoolService service;

    @GetMapping("/retrieveSchools")
    public ResponseEntity<APIResponse> retrieveSchools(@RequestParam("schoolPage") int page, @RequestParam("schoolSize") int size){
        return Responder.okay(service.retrieveSchools(page, size));
    }
}
