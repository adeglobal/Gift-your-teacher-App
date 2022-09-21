package com.decagon.rewardyourteacherapi.payload;

import com.decagon.rewardyourteacherapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
public class SearchTeacherResponse {
    private String message;
    private LocalDateTime timeStamp;
    private List<User> userList;
}
