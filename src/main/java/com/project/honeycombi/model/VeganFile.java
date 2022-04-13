package com.project.honeycombi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class VeganFile {
    @Id
    @GeneratedValue
    Long vfId;

    String originalFileName;
    
    String saveFileName;

    @ManyToOne
    @ToString.Exclude
    Vegan vegan;

    
}

