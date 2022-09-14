package com.decagon.rewardyourteacherapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name ="message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Users sender;
    @OneToOne
    private Users receiver;
    @Column(columnDefinition = "TEXT")
    private String content;

    public Message(Users sender, Users receiver, String content){
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }
}
