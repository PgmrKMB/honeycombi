package com.project.honeycombi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String content;
    Date createDate;
    
    @ManyToOne
    @ToString.Exclude
    User user;

    @ManyToOne
    @ToString.Exclude
    Vegan vegan;

    @ManyToOne
    @ToString.Exclude
    Honey honey;

}
