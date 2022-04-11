package com.project.honeycombi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    Long uId;
    
    String email;

    String pwd;

    String nickname;

    @OneToMany(mappedBy = "user")
    List<Mania> manias = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Vegan> vegans = new ArrayList<>();
 
}
