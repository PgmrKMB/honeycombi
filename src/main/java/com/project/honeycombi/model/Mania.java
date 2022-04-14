package com.project.honeycombi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Mania {
    @Id
    @GeneratedValue
    Long mId;

    String mTitle;
    
    String mContent;
  
    @ManyToOne
    @ToString.Exclude
    User user;

    Date createDate;

    Integer mCount = 0;
    
    @OneToMany(mappedBy = "mania", cascade = CascadeType.REMOVE)
    List<ManiaFile> maniaFiles = new ArrayList<>();

    @OneToMany(mappedBy = "mania")
    List<ManiaRecommend> maniaRecommends = new ArrayList<>();

}
