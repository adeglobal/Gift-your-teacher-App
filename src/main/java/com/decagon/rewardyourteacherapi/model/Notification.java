package com.decagon.rewardyourteacherapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String message;
    @OneToOne
    @JoinColumn(name = "recipient_id")
    private User user;
    @CreationTimestamp
    private LocalDateTime createdAt;

    private  long transaction_id;

    public Notification(String message, User user){
        this.user = user;
        this.message = message;
    }

}
