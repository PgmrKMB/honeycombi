package com.project.honeycombi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

    

}
