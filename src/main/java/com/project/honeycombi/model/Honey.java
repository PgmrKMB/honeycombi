package com.project.honeycombi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Honey {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long hId;
	
	String hSubject;
	String hContent;

	Integer hHit = 0;

	@Temporal(TemporalType.TIMESTAMP)
	Date createDate;
	

	@ManyToOne
	@ToString.Exclude
	User user;

	@OneToMany(mappedBy = "honey", cascade = CascadeType.REMOVE)
	List<HoneyFile> honeyFiles = new ArrayList<>();
	

}
