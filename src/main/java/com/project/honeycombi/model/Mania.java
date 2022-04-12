package com.project.honeycombi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

    Integer mCount;
    
    Integer mRecommend;

    @OneToMany(mappedBy = "mania", cascade = CascadeType.REMOVE)
    List<ManiaFile> maniaFiles = new ArrayList<>();



}
