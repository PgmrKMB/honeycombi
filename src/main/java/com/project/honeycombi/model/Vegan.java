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

	@OneToMany(mappedBy = "vegan", cascade = CascadeType.REMOVE)
	List<VeganFile> veganFiles = new ArrayList<>();
	

}
