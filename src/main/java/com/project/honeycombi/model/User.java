package com.project.honeycombi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnore
    List<Mania> manias = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Vegan> vegans = new ArrayList<>();
 
}
