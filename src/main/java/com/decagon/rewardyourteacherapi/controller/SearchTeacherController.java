package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.model.Role;

import com.decagon.rewardyourteacherapi.payload.SearchTeacherResponse;
import com.decagon.rewardyourteacherapi.service.SearchTeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/app")
public class SearchTeacherController {

    private final SearchTeacherService searchTeacherService;

    public SearchTeacherController(SearchTeacherService searchTeacherService) {
        this.searchTeacherService = searchTeacherService;
    }
    @GetMapping(value = "/search")
    public ResponseEntity <SearchTeacherResponse> searchTeacher(@PathVariable(value = "role")Role role,
                                                                @PathVariable(value = "name")String name,
                                                                @PathVariable(value = "id")long id){
       return ResponseEntity.ok().body(searchTeacherService.searchTeacher(role,name,id));
    }
}
