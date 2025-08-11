package com.example.demo.dao;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Admin;
import com.example.demo.entities.Artiste;
import com.example.demo.entities.ContentProvider;
import com.example.demo.entities.Message;
import com.example.demo.entities.Role;
import com.example.demo.repository.AdminRepo;
import com.example.demo.repository.ArtisteRepo;
import com.example.demo.repository.ContentProviderRepo;
import com.example.demo.repository.RoleRepo;
import com.example.demo.rest.SendEmail;

@Service
public class ContentProviderDao {

	@Autowired
	RoleRepo roleRepo;

	@Autowired
	ContentProviderRepo contentProviderRepo;

	PasswordEncoder passwordEncoder;
	
	@Autowired
	ArtisteRepo artisteRepo ;
	
	@Autowired
	SendEmail sendEmail;
	
	
	 public Optional<ContentProvider> findByBookId(Integer id) {
		 Optional<ContentProvider> book = contentProviderRepo.findById(id);
	        return book;
	    }
	public List<ContentProvider> getAllCp() {
		return contentProviderRepo.findAll();
	}
	
	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;
	
	public ContentProvider ajouterContentProvider(ContentProvider pt) {

		
		contentProviderRepo.save(pt);

		return pt;
	}
	
	
	


	public List<ContentProvider> getAllContentProviders() {
		return contentProviderRepo.findAll();
	}

	public ContentProvider getContentProvider(Integer id) {

		return contentProviderRepo.findById(id).get();

	}

	public ContentProvider updateContentProvider(ContentProvider contentProvider) {
		if (contentProvider.getId() != 0) {
			ContentProvider pt = contentProviderRepo.findById(contentProvider.getId()).get();
			if (pt != null) {
				List<Artiste> artistes = new ArrayList<>();
				
				/*Artiste art = new Artiste();
				
				
				
				artistes.add(art);*/
				
				contentProvider.getArtistes();
				
				
				//contentProvider.setArtistes(artistes);
				contentProviderRepo.save(contentProvider);
			}
		}
		return contentProvider;
	}
	
	
	
	public ResponseEntity<ContentProvider> changePWD(Integer id, String password) {

		Optional<ContentProvider> user = contentProviderRepo.findById(id);
		if(user.isPresent()) {
			System.out.println("hethi password"+password);
			this.passwordEncoder = new BCryptPasswordEncoder();
			//user.get().setPassword(password);
			String encodePasswod = this.passwordEncoder.encode(password);
			System.out.println("lhne"+encodePasswod);
			user.get().setPassword(encodePasswod);
			
			contentProviderRepo.changePWD(encodePasswod, id);
			return new ResponseEntity<ContentProvider>(user.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<ContentProvider>(new ContentProvider(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	public ResponseEntity<Message> forgotPassword(String email) throws MessagingException {

		List<ContentProvider> lu = contentProviderRepo.findByEmail(email);

		if (lu.isEmpty()) {
			System.out.println("email not found");
			return new ResponseEntity<Message>(new Message("invalid"), HttpStatus.OK);
		}
		
		if (lu.size() > 1) {
			return new ResponseEntity<Message>(new Message("invalid"), HttpStatus.OK);
		}
		
		ContentProvider user = lu.get(0);
		user.setToken(generateToken());
		user.setToken_creation_date(LocalDateTime.now());

		user = contentProviderRepo.save(user);
		
		sendEmail.sendLinkResetPwdCp(user.getEmail(),user.getToken(), "Restaurer votre mot de passe", user.getNomContent());
		
		return new ResponseEntity<Message>(new Message(user.getToken()), HttpStatus.OK);
	}
	

	private String generateToken() {
		StringBuilder token = new StringBuilder();

		return token.append(UUID.randomUUID().toString())
				.append(UUID.randomUUID().toString()).toString();
	}
	
	
	private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

		LocalDateTime now = LocalDateTime.now();
		Duration diff = Duration.between(tokenCreationDate, now);

		return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
	}
	
	
	public ResponseEntity<Message> resetPassword(String token, String password) {

		List<ContentProvider> lu = contentProviderRepo.findByToken(token);

		if (lu.isEmpty()) {
			return new ResponseEntity<Message>(new Message("Invalid token."), HttpStatus.NOT_FOUND);
		}

		LocalDateTime tokenCreationDate = lu.get(0).getToken_creation_date();

		if (isTokenExpired(tokenCreationDate)) {
			return new ResponseEntity<Message>(new Message("Token expired."), HttpStatus.NOT_FOUND);
		}

		ContentProvider user = lu.get(0);

		//user.setPassword(cryptDAO.encryption(password));
		this.passwordEncoder = new BCryptPasswordEncoder();
		//user.get().setPassword(password);
		String encodePasswod = this.passwordEncoder.encode(password);
		System.out.println("lhne"+encodePasswod);
		user.setPassword(encodePasswod);
		user.setToken(null);
		user.setToken_creation_date(null);

		contentProviderRepo.save(user);
		return new ResponseEntity<Message>(new Message("success"), HttpStatus.OK);
	}
	
	//findCpByEmail
	
	public List<ContentProvider> findCpByEmail(String email) {
		return contentProviderRepo.findCpByEmail(email);
	}
	
	
	//findCpBynomContent
	
	public List<ContentProvider> findCpBynomContent(String email) {
		return contentProviderRepo.findCpBynomContent(email);
	}
	
	
	
	public ContentProvider saveCp(ContentProvider cp )
	{
		List<Artiste> artistes = new ArrayList<>();
		
		Artiste art = new Artiste();
		//art.setnArtistique("TestA1");
		
		
		artistes.add(art);
		
		
		cp.setArtistes(artistes);
		
		cp = contentProviderRepo.save(cp);
		
		return cp ;
	}
	
	
	
	
	
	
	
	
}
