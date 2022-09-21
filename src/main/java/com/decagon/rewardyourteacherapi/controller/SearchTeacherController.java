package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.service.SearchTeacherService;
import com.decagon.rewardyourteacherapi.util.Responder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/app")
public class SearchTeacherController {

    private final SearchTeacherService searchTeacherService;

    public SearchTeacherController(SearchTeacherService searchTeacherService) {
        this.searchTeacherService = searchTeacherService;
    }
    @GetMapping(value = "/search={name}")
    public ResponseEntity <APIResponse> searchTeacher(@PathVariable(value = "name")String name){
       return Responder.okay(searchTeacherService.searchTeacher(name));
    }
}
