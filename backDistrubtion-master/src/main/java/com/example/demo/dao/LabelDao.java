package com.example.demo.dao;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Artiste;
import com.example.demo.entities.Label;
import com.example.demo.entities.Message;
import com.example.demo.repository.LabelRepo;
import com.example.demo.rest.SendEmail;

@Service
public class LabelDao {


	@Autowired
	LabelRepo labelRepo ;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	SendEmail sendEmail ;
	
	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;
	
	
	
	
	
	public Label getLabel(Integer id) {

		return labelRepo.findById(id).get();

	}
	
	
	public Label updateLabel(Label label) {
		if (label.getId() != 0) {
			Label pt = labelRepo.findById(label.getId()).get();
			if (pt != null) {
				labelRepo.save(label);
			}
		}
		return label;
	}
	
	
	
	public ResponseEntity<Label> changePWD(Integer id, String password) {

		Optional<Label> user = labelRepo.findById(id);
		if(user.isPresent()) {
			System.out.println("hethi password"+password);
			
			this.passwordEncoder = new BCryptPasswordEncoder();
			//user.get().setPassword(password);
			String encodePasswod = this.passwordEncoder.encode(password);
			System.out.println("lhne"+encodePasswod);
			user.get().setPassword(encodePasswod);
			labelRepo.changePWD(encodePasswod, id);
			
			return new ResponseEntity<Label>(user.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Label>(new Label(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	public ResponseEntity<Message> forgotPassword(String email) throws MessagingException {

		List<Label> lu = labelRepo.findByEmail(email);

		if (lu.isEmpty()) {
			System.out.println("email not found");
			return new ResponseEntity<Message>(new Message("invalid"), HttpStatus.OK);
		}
		
		if (lu.size() > 1) {
			return new ResponseEntity<Message>(new Message("invalid"), HttpStatus.OK);
		}
		
		Label user = lu.get(0);
		user.setToken(generateToken());
		user.setToken_creation_date(LocalDateTime.now());

		user = labelRepo.save(user);
		
		sendEmail.sendLinkResetPwd(user.getEmail(),user.getToken(), "Restaurer votre mot de passe", user.getNom(), user.getPrenom());
		
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

		List<Label> lu = labelRepo.findByToken(token);

		if (lu.isEmpty()) {
			return new ResponseEntity<Message>(new Message("Invalid token."), HttpStatus.NOT_FOUND);
		}

		LocalDateTime tokenCreationDate = lu.get(0).getToken_creation_date();

		if (isTokenExpired(tokenCreationDate)) {
			return new ResponseEntity<Message>(new Message("Token expired."), HttpStatus.NOT_FOUND);
		}

		Label user = lu.get(0);

		//user.setPassword(cryptDAO.encryption(password));
		this.passwordEncoder = new BCryptPasswordEncoder();
		//user.get().setPassword(password);
		String encodePasswod = this.passwordEncoder.encode(password);
		System.out.println("lhne"+encodePasswod);
		user.setPassword(encodePasswod);
		user.setToken(null);
		user.setToken_creation_date(null);

		labelRepo.save(user);
		return new ResponseEntity<Message>(new Message("success"), HttpStatus.OK);
	}

	
	public List<Label> findLabelByEmail(String email) {
		return labelRepo.findByEmail(email);
	}

	
	
	public List<Label> getAllLabels() {
		return labelRepo.findAll();
	}
	
}
