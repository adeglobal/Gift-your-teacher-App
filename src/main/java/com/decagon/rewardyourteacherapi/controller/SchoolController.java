package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.model.School;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.APIResponse;
import com.decagon.rewardyourteacherapi.service.UserService;
import com.decagon.rewardyourteacherapi.util.Responder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/school")
public class SchoolController {


    private final UserService userService;

    @GetMapping(path = "/{id}/{page}&{size}")
    public ResponseEntity<APIResponse> getTeachers(@PathVariable("id") Long id,
                                                   @PathVariable("page") int page,
                                                   @PathVariable("size") int size){
        return Responder.okay(userService.getSchoolTeachers(id, page, size));
    }
}
