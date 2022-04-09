package com.project.honeycombi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;

import lombok.Data;

@Data
@Entity
public class Mania {
    @Id
    @GeneratedValue
    Long mId;

    String mTitle;
    
    String mContent;
  
    @ManyToOne
    User user;

    Date createDate;

    Integer mCount;
    
    Integer mRecommend;

    

}
