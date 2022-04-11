package com.project.honeycombi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Vegan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long vId;
	
	String vSubject;
	String vContent;

	@Temporal(TemporalType.TIMESTAMP)
	Date createDate;
	

	@ManyToOne
	@ToString.Exclude
	User user;
	

}
