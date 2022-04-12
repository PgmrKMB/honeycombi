package com.project.honeycombi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class VeganFile {
    @Id
    @GeneratedValue
    Long id;

    String originalFileName;
    String saveFileName;

    
}

