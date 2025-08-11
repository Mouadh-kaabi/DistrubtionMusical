package com.example.demo.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity(name="communication")
public class Communication {

	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private  Integer id  ;	
		private  Date cdate  ;
		private  String path  ;
		private  Byte[] image ;
		private  String url  ;
		private  Date dateC  ;
		private  String place  ;
		private  Date udate  ;


		@ManyToOne
		private Users user;
		
		
		@OneToMany(mappedBy = "communication", cascade = CascadeType.ALL)
	    @JsonIgnoreProperties("communication")
	    private List<hist_communication> communications = new ArrayList<hist_communication>();

	    
		public Integer getId() {
			return id;
		}
		

		public void setId(Integer id) {
			this.id = id;
		}


		public Date getCdate() {
			return cdate;
		}


		public void setCdate(Date cdate) {
			this.cdate = cdate;
		}


		public String getPath() {
			return path;
		}


		public void setPath(String path) {
			this.path = path;
		}


		public Byte[] getImage() {
			return image;
		}


		public void setImage(Byte[] image) {
			this.image = image;
		}


		public String getUrl() {
			return url;
		}


		public void setUrl(String url) {
			this.url = url;
		}


		public Date getDateC() {
			return dateC;
		}


		public void setDateC(Date dateC) {
			this.dateC = dateC;
		}


		public String getPlace() {
			return place;
		}


		public void setPlace(String place) {
			this.place = place;
		}


		public Date getUdate() {
			return udate;
		}


		public void setUdate(Date udate) {
			this.udate = udate;
		}


		public Users getUser() {
			return user;
		}


		public void setUser(Users user) {
			this.user = user;
		}


		public Communication(Integer id, Date cdate, String path, Byte[] image, String url, Date dateC, String place,
				Date udate, Users user) {
			super();
			this.id = id;
			this.cdate = cdate;
			this.path = path;
			this.image = image;
			this.url = url;
			this.dateC = dateC;
			this.place = place;
			this.udate = udate;
			this.user = user;
		}


		public Communication() {
			super();
			// TODO Auto-generated constructor stub
		}


		@Override
		public String toString() {
			return "Communication [id=" + id + ", cdate=" + cdate + ", path=" + path + ", image="
					+ Arrays.toString(image) + ", url=" + url + ", dateC=" + dateC + ", place=" + place + ", udate="
					+ udate + ", user=" + user + "]";
		}
	
	
		
		
}
