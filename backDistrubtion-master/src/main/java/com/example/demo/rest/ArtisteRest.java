package com.example.demo.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity.BodyBuilder;
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

import com.example.demo.dao.ArtisteDao;
import com.example.demo.entities.Admin;
import com.example.demo.entities.Album;
import com.example.demo.entities.Artiste;
import com.example.demo.entities.Message;
import com.example.demo.intefrace.ArtisteGe;
import com.example.demo.repository.ArtisteRepo;
@CrossOrigin(origins = "http://192.168.1.111:4200")
@RestController
@RequestMapping("/artiste")

public class ArtisteRest {

	@Autowired
	ArtisteDao artisteDao ; 
	
	
	/*@PostMapping("/AffecterArtiste")
	public ResponseEntity<Artiste> addConsultaionDetails(@RequestBody Artiste c) {
		return artisteDao.addConsultaionDetails(c);
	}*/


	@Autowired
	ArtisteRepo artisteRepo ; 
	

	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/allartiste")
	public List<Artiste> getNomArtiste()
	{
		
		//List<ArtisteGe>listart= artisteRepo.listNomArtiste();
		
		//List<Artiste> artistes = new ArrayList<Artiste>();
		
		//artisteRepo.saveAll((Artiste)listart);
		//artistes.add((Artiste) listart);
		
		return artisteRepo.findAll() ;
		
		
	}
	
	
	@PostMapping("/inscriptionArtiste")
	public Artiste inscrpition(@RequestBody Artiste ad) {
	return artisteDao.ajouterArtiste(ad);
	}
	
	
	@GetMapping("/all")
	public List<Artiste> getAllArtistes() {
		return artisteDao.getAllArtistes();
	}
	
	
	
	@GetMapping("/{id}")
	public Artiste getArtiste(@PathVariable(value = "id") Integer id) {
		return artisteDao.getArtiste(id);
	}
	
	
	
	@PutMapping("/update")
	public Artiste updateArtiste(@RequestBody Artiste artiste) {
		
		
		this.passwordEncoder = new BCryptPasswordEncoder();
		//user.get().setPassword(password);
		
		String password = artiste.getPassword();
		String encodePasswod = this.passwordEncoder.encode(password);
		System.out.println("lhne"+encodePasswod);
		artiste.setPassword(encodePasswod);
		
		Integer id = artiste.getId();
		artisteRepo.changePWD(encodePasswod, id);
		
		return artisteDao.updateArtiste(artiste);
	}
	
	

	@DeleteMapping("/delete/{id}")
	public void deleteArtisteId(@PathVariable("id") Integer id) {
		artisteRepo.deleteById(id);
		
	}
	
	
	@RequestMapping(path = "/uploadImage/{id}", method = RequestMethod.PUT)
	public org.springframework.http.ResponseEntity.BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile files,@PathVariable("id") Integer id) throws IOException {
		System.out.println("Original Image Byte Size - " + files.getBytes().length);
		Artiste img = new Artiste(files.getOriginalFilename(), files.getContentType(), compressBytes(files.getBytes()));
		artisteRepo.savenew(files.getOriginalFilename(), compressBytes(files.getBytes()), files.getContentType(), id);
		return null;
		}
	
	
	@RequestMapping(path = "/uploadIn", method = RequestMethod.PUT)
	public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile files) throws IOException {
		System.out.println("Original Image Byte Size - " + files.getBytes().length);
		Artiste img = new Artiste(files.getOriginalFilename(), files.getContentType(), compressBytes(files.getBytes()));
		artisteRepo.savenewIn(files.getOriginalFilename(), compressBytes(files.getBytes()), files.getContentType());
		return null;
		}
	
	@RequestMapping(path = "/getImage/{id}", method = RequestMethod.GET)
	public Artiste getImage(@PathVariable("id") Integer id) throws IOException {
		Optional<Artiste> retrievedImage = artisteRepo.findById(id);
		System.out.println("img "+retrievedImage);
			if(retrievedImage.get().getType()!=null) {
				Artiste img = new Artiste(retrievedImage.get().getName(), retrievedImage.get().getType(),
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

	
	@PutMapping("/changePWD/{id}/{password}")
	public ResponseEntity<Artiste> changePWD(@PathVariable(value = "id") Integer id, @PathVariable(value = "password") String password) {
		return artisteDao.changePWD(id, password);
	}
	
	@GetMapping("/forgotPwd/{email}")
	public String forgotPassword(@PathVariable String email) throws MessagingException {
		ResponseEntity<Message> m = artisteDao.forgotPassword(email);
		return "m";
	}
	
	
	
	//resetPassword
	
	@GetMapping("/resetPwd/{token}/{password}")
	public String restPassword(@PathVariable String token, @PathVariable String password) {
		ResponseEntity<Message> m = artisteDao.resetPassword(token, password);
		return "m";
	}
	
	
	
	@GetMapping("/id/{userName}")
	public Artiste getUserIdByUserName(@PathVariable String email) {
		Artiste user = artisteDao.getUserId(email, "username");
		if(user == null) {
			user = artisteDao.getUserId(email, "email");
		}
		return user;
	}
	
	
	//getArtisteByEmail
	
	
	@GetMapping("/checkemail/{email}")
	public List<Artiste> getArtisteByEmail(@PathVariable(value = "email") String email) {
		return artisteDao.getArtisteByEmail(email);
	}
	
	
	//findBynArtistique
	
	@GetMapping("/checknArtistique/{nArtistique}")
	public List<Artiste> getArtisteBynArtistique(@PathVariable(value = "nArtistique") String nArtistique) {
		return artisteDao.findBynArtistique(nArtistique);
	}
	
	
	@RequestMapping(path = "/artiste-ByNom/{nom}", method = RequestMethod.GET)
	public List<Artiste> rechercheParNom(@PathVariable("nom") String nom) {
		return artisteRepo.findByNom(nom);
	}
	
	
	
}
