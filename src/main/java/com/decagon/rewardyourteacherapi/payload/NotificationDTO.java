package com.decagon.rewardyourteacherapi.payload;


import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
public class NotificationDTO {
    private long id;
    private String message;
    private LocalDateTime createdAt;

}
