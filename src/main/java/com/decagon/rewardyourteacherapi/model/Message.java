package com.decagon.rewardyourteacherapi.model;

import javax.persistence.*;

@Entity
@Table(name ="message")
@IdClass(MessageId.class)
public class Message {
    @Id
    @OneToOne
    private User sender;
    @Id
    @OneToOne
    private User receiver;
    @Column(columnDefinition = "TEXT")
    private String content;
    private Boolean read;

}
