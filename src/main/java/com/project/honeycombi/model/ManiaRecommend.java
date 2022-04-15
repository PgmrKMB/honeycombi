package com.project.honeycombi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class ManiaRecommend {
    @Id
    @GeneratedValue
    Long mrId;

    @ManyToOne
    User user;

    @ManyToOne
    Mania mania;

}
