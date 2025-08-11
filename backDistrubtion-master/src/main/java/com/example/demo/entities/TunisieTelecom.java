package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="tunisie_telecom")
@DiscriminatorValue("tunisie_telecom")
public class TunisieTelecom extends Details {

	
	
	
	
	public TunisieTelecom() {
		super();
		// TODO Auto-generated constructor stub
	}

	
		
	@OneToMany(mappedBy = "tunisietelecom", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnoreProperties("tunisietelecom")
    private List<ContentProvider> contentProviders = new ArrayList<ContentProvider>();



	public List<ContentProvider> getContentProviders() {
		return contentProviders;
	}



	public void setContentProviders(List<ContentProvider> contentProviders) {
		this.contentProviders = contentProviders;
	}







	
	

	






	






	








	



	
	
}
