package com.decagon.rewardyourteacherapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "school")
public class School{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String schoolName;

    public School(long id) {
        this.id = id;
    }
}
