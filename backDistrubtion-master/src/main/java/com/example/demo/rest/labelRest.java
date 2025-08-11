package com.example.demo.rest;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.LabelDao;

import com.example.demo.entities.Artiste;
import com.example.demo.entities.ContentProvider;
import com.example.demo.entities.Label;
import com.example.demo.entities.Message;
import com.example.demo.repository.LabelRepo;

@RestController
@RequestMapping("/label")
@CrossOrigin(origins = "http://192.168.1.111:4200")
public class labelRest {

	
	@Autowired
	LabelRepo labelRepo ;
	
	@Autowired
	LabelDao labelDao ;
	

@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@PutMapping("/update")
	public Label updateLabel(@RequestBody Label label) {
		
		
		this.passwordEncoder = new BCryptPasswordEncoder();
		String password = label.getPassword();
		String encodePasswod = this.passwordEncoder.encode(password);
		System.out.println("lhne"+encodePasswod);
		label.setPassword(encodePasswod);
		
		Integer id = label.getId();
		labelRepo.changePWD(encodePasswod, id);
		return labelDao.updateLabel(label);
	}

	public int hashCode() {
		return labelDao.hashCode();
	}

	public ResponseEntity<Message> resetPassword(String token, String password) {
		return labelDao.resetPassword(token, password);
	}

	@GetMapping("/{id}")
	public Label getLabel(@PathVariable(value = "id") Integer id) {
		return labelDao.getLabel(id);
	}

	@PutMapping("/changePWD/{id}/{password}")
	public ResponseEntity<Label> changePWD(@PathVariable(value = "id") Integer id, @PathVariable(value = "password") String password) {
		return labelDao.changePWD(id, password);
	}
	
	@GetMapping("/forgotPwd/{email}")
	public ResponseEntity<Message> forgotPassword(@PathVariable String email) throws MessagingException {
		ResponseEntity<Message> m = labelDao.forgotPassword(email);
		return m;
	}
	
	
	@GetMapping("/resetPwd/{token}/{password}")
	public ResponseEntity<Message> restPassword(@PathVariable String token, @PathVariable String password) {
		ResponseEntity<Message> m = labelDao.resetPassword(token, password);
		return m;
	}
	
	
	//@RequestMapping(method = RequestMethod.POST, path = "/affecterArtiste")
	@PostMapping("/affecterArtiste")
	public void affecterLabel(@Valid @RequestBody Label p) {
		/*Label ch=new Label(
				p.getEmail(),
				
				p.getNom(),
				p.getArtistes(),
				p.getPassword());*/
			
			
				
			
			
		//System.out.println(ch.getPlatformes().size());
		//p=ch;
		labelRepo.save(p);
		// ChansonRepository.updatecdate(p.getId());
	}
	

	
	
	//findLabelByEmail
	
	@GetMapping("/checkemail/{email}")
	public List<Label> findLabelByEmail(@PathVariable String email) {
		return labelDao.findLabelByEmail(email);
	}
	
/*	@RequestMapping(path = "/updateLabel", method = RequestMethod.PUT)
	public void update(@RequestBody Label u) {
		labelRepo.save(u);
 	}*/
	
	
	@GetMapping("/all")
	public List<Label> getAllLabels() {
		return labelDao.getAllLabels();
	}

	
}
