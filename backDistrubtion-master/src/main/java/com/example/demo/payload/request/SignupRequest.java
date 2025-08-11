package com.example.demo.payload.request;

import java.sql.Date;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.demo.entities.Role;

import antlr.collections.List;

public class SignupRequest {

	
	 	@NotBlank
	    @Size(max = 50)
	    @Email
	    private String email;
	    
	    private Set<String> role;
	    private  Integer id ; 
	    @NotBlank
	    @Size(min = 6, max = 40)
	    private String password;

	    
	    
	    private  String nom  ;
	    private String nomFournissuer ;
	    private String nomContent;
	   
	    private  String prenom  ;

	    private  Byte[] image ;

	    
	    private  String nArtistique  ;

	   
	    private  String phone  ;

	   
	    private  String nationnalite  ;

	    
	    private  String cin  ;

	    private  Byte[] contrat  ;

	    private  float part  ;

	    private  float retenu ;
	    
	    private Boolean pro ;

	    
	    private  String proposition ;

	    private  Date date  ;

	    private  Date datecin  ;

	    private Date cdate  ;

	    private Date udate;
	    

	    public String getNomContent() {
			return nomContent;
		}

		public void setNomContent(String nomContent) {
			this.nomContent = nomContent;
		}

		public Boolean getPro() {
			return pro;
		}

		public void setPro(Boolean pro) {
			this.pro = pro;
		}

	    public String getEmail() {
	        return email;
	    }
	 
		public void setEmail(String email) {
	        this.email = email;
	    }
	 
	    public String getPassword() {
	        return password;
	    }
	 
	    public void setPassword(String password) {
	        this.password = password;
	    }
	    
	    public Set<String> getRole() {
	      return this.role;
	    }
	    
	    public void setRole(Set<String> role) {
	      this.role = role;
	    }

	    public String getNom() {
	        return nom;
	    }

	    public void setNom(String nom) {
	        this.nom = nom;
	    }

	    public String getPrenom() {
	        return prenom;
	    }

	    public void setPrenom(String prenom) {
	        this.prenom = prenom;
	    }

	    public Byte[] getImage() {
	        return image;
	    }

	    public void setImage(Byte[] image) {
	        this.image = image;
	    }

	    public String getnArtistique() {
	        return nArtistique;
	    }

	    public String getNomFournissuer() {
			return nomFournissuer;
		}

		public void setNomFournissuer(String nomFournissuer) {
			this.nomFournissuer = nomFournissuer;
		}

		public void setnArtistique(String nArtistique) {
	        this.nArtistique = nArtistique;
	    }

	    public String getPhone() {
	        return phone;
	    }

	    public void setPhone(String phone) {
	        this.phone = phone;
	    }

	    public String getNationnalite() {
	        return nationnalite;
	    }

	    public void setNationnalite(String nationnalite) {
	        this.nationnalite = nationnalite;
	    }

	    public String getCin() {
	        return cin;
	    }

	    public void setCin(String cin) {
	        this.cin = cin;
	    }

	    public Byte[] getContrat() {
	        return contrat;
	    }

	    public void setContrat(Byte[] contrat) {
	        this.contrat = contrat;
	    }

	    public float getPart() {
	        return part;
	    }

	    public void setPart(float part) {
	        this.part = part;
	    }

	    public float getRetenu() {
	        return retenu;
	    }

	    public void setRetenu(float retenu) {
	        this.retenu = retenu;
	    }

	    public String getProposition() {
	        return proposition;
	    }

	    public void setProposition(String proposition) {
	        this.proposition = proposition;
	    }

	    public Date getDate() {
	        return date;
	    }

	    public void setDate(Date date) {
	        this.date = date;
	    }

	    public Date getDatecin() {
	        return datecin;
	    }

	    public void setDatecin(Date datecin) {
	        this.datecin = datecin;
	    }

	    public Date getCdate() {
	        return cdate;
	    }

	    public void setCdate(Date cdate) {
	        this.cdate = cdate;
	    }

	    public Date getUdate() {
	        return udate;
	    }

	    public void setUdate(Date udate) {
	        this.udate = udate;
	    }

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

}
