package com.example.demo.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.AdminDao;
import com.example.demo.entities.Admin;
import com.example.demo.entities.Artiste;
import com.example.demo.entities.Message;
import com.example.demo.repository.AdminRepo;
@CrossOrigin(origins = "http://192.168.1.111:4200")
@RestController
@RequestMapping("/admin")

public class AdminRest {

	
	
	@Autowired
	AdminDao adminDAO;
	
	@Autowired
	AdminRepo adminRepo ;
	
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@PostMapping("/inscriptionAdmin")
	public Admin inscrpition(@RequestBody Admin ad) {
	return adminDAO.ajouterAdmin(ad);
	}
	
	@GetMapping("/all")
	public List<Admin> getAllAdmins() {
		return adminDAO.getAllAdmins();
	}
	
	
	
	@GetMapping("/{id}")
	public Admin getArtiste(@PathVariable(value = "id") Integer id) {
		return adminDAO.getAdmin(id);
	}
	
	
	
	@PutMapping("/update")
	public Admin updateAdmin(@RequestBody Admin admin) {
		
		this.passwordEncoder = new BCryptPasswordEncoder();
		//user.get().setPassword(password);
		
		String password = admin.getPassword();
		String encodePasswod = this.passwordEncoder.encode(password);
		System.out.println("lhne"+encodePasswod);
		admin.setPassword(encodePasswod);
		
		Integer id = admin.getId();
		adminRepo.changePWD(encodePasswod, id);
		return adminDAO.updateAdmin(admin);
	}
	
	
	@DeleteMapping("/deleteadmin/{id}")
	public void deleteAdminId(@PathVariable("id") Integer id) {
		adminRepo.deleteById(id);
	}
	
	
	@GetMapping("/checkemail/{email}")
	public List<Admin> getAdminByEmail(@PathVariable(value = "email") String email) {
		return adminDAO.getAdminByEmail(email);
	}
	
	
	
	@GetMapping("/forgotPwd/{email}")
	public String forgotPassword(@PathVariable String email) throws MessagingException {
		ResponseEntity<Message> m = adminDAO.forgotPassword(email);
		return "m";
	}
	
	
	@GetMapping("/resetPwd/{token}/{password}")
	public String restPassword(@PathVariable String token, @PathVariable String password) {
		ResponseEntity<Message> m = adminDAO.resetPassword(token, password);
		return "m";
	}
	
	
	
	@RequestMapping(path = "/uploadImage/{id}", method = RequestMethod.PUT)
	public org.springframework.http.ResponseEntity.BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile files,@PathVariable("id") Integer id) throws IOException {
		System.out.println("Original Image Byte Size - " + files.getBytes().length);
		Admin img = new Admin(files.getOriginalFilename(), files.getContentType(), compressBytes(files.getBytes()));
		adminRepo.savenew(files.getOriginalFilename(), compressBytes(files.getBytes()), files.getContentType(), id);
		return null;
		}
	
	
	@RequestMapping(path = "/getImage/{id}", method = RequestMethod.GET)
	public Admin getImage(@PathVariable("id") Integer id) throws IOException {
		Optional<Admin> retrievedImage = adminRepo.findById(id);
		System.out.println("img "+retrievedImage);
			if(retrievedImage.get().getType()!=null) {
				Admin img = new Admin(retrievedImage.get().getName(), retrievedImage.get().getType(),
						decompressBytes(retrievedImage.get().getPicByte()));
				return img;
			}
		else return	retrievedImage.get();

	}
	
	
	public static byte[] compressBytes(byte[] data) {

		Deflater deflater = new Deflater();
 
		deflater.setInput(data);

		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

		byte[] buffer = new byte[1024];

		while (!deflater.finished()) {

			int count = deflater.deflate(buffer);

			outputStream.write(buffer, 0, count);

		}

		try {

			outputStream.close();

		} catch (IOException e) {

		}

		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();

	}

// uncompress the image bytes before returning it to the angular application

	public static byte[] decompressBytes(byte[] data) {

		Inflater inflater = new Inflater();

		inflater.setInput(data);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

		byte[] buffer = new byte[1024];

		try {

			while (!inflater.finished()) {

				int count = inflater.inflate(buffer);

				outputStream.write(buffer, 0, count);

			}

			outputStream.close();

		} catch (IOException ioe) {

		} catch (DataFormatException e) {

		}

		return outputStream.toByteArray();

	}

}
