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
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.ContentProviderDao;

import com.example.demo.entities.Admin;
import com.example.demo.entities.Artiste;
import com.example.demo.entities.ContentProvider;
import com.example.demo.entities.Details;
import com.example.demo.entities.Message;
import com.example.demo.repository.ArtisteRepo;
import com.example.demo.repository.ContentProviderRepo;

@RestController
@RequestMapping("/cp")

@CrossOrigin(origins = "http://192.168.1.111:4200")
public class ContentProviderRest {

	
	@Autowired
	ContentProviderRepo contentProviderRepo ;
	
	
	@Autowired
	ContentProviderDao contentProviderDao ;
	
	@Autowired
	ArtisteRepo  artisteRepo ;
	
	
	
	@RequestMapping(value = "/savecp", method = RequestMethod.POST)
    @ResponseBody
	public ContentProvider saveCp(ContentProvider cp) {
		return contentProviderDao.saveCp(cp);
	}
	
	
	@RequestMapping(value = "/by/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<ContentProvider> getBookDetails(@PathVariable Integer id) {
		Optional<ContentProvider> bookResponse = contentProviderDao.findByBookId(id);
        return bookResponse;
    }

	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/allCp")
	public List<ContentProvider> getAllCps() {
		return contentProviderDao.getAllCp();
	}
	
	
	@PostMapping("/inscriptioncp")
	public ContentProvider inscrpition(@RequestBody ContentProvider ad) {
	return contentProviderDao.ajouterContentProvider(ad);
	}
	
	@GetMapping("/all")
	public List<ContentProvider> getAllCP() {
		return contentProviderDao.getAllContentProviders();
	}
	
	
	
	@GetMapping("test")
	public String getaall()
	{
		ContentProvider cp = new ContentProvider();
		System.out.println("heeeeeeloo"+cp.getPart_cp());
		
		return "jjjj" ;
		
		
	}
	
	@GetMapping("/{id}")
	public ContentProvider getcp(@PathVariable(value = "id") Integer id) {
		return contentProviderDao.getContentProvider(id);
	}
	
	
	@PostMapping("/affecterArtiste")
	public Artiste affecterArtiste(@RequestBody Artiste artiste)
	{
		return artisteRepo.save(artiste);
	}
	
	
	@PutMapping("/update")
	public ContentProvider updateContentProvider(@RequestBody ContentProvider contentProvider) {
		
		this.passwordEncoder = new BCryptPasswordEncoder();
		String password = contentProvider.getPassword();
		String encodePasswod = this.passwordEncoder.encode(password);
		System.out.println("lhne"+encodePasswod);
		contentProvider.setPassword(encodePasswod);
		
		Integer id = contentProvider.getId();
		contentProviderRepo.changePWD(encodePasswod, id);
		
		
		List<Artiste> artistes = new ArrayList<>();
		
		/*for (int i = 0 ; i<artistes.size();i++)
		{
			System.out.println(artistes);
		}*/
		Artiste art = new Artiste();
		art.setContentProvider(contentProvider);
		
		artistes.add(art);
		contentProvider.setArtistes(artistes);
		return contentProviderDao.updateContentProvider(contentProvider);
	}
	
	
	@DeleteMapping("/deletecp/{id}")
	public void deleteContentProviderId(@PathVariable("id") Integer id) {
		contentProviderRepo.deleteById(id);
	}
	
	
	@PutMapping("/changePWD/{id}/{password}")
	public ResponseEntity<ContentProvider> changePWD(@PathVariable(value = "id") Integer id, @PathVariable(value = "password") String password) {
		return contentProviderDao.changePWD(id, password);
	}
	
	@GetMapping("/forgotPwd/{email}")
	public ResponseEntity<Message> forgotPassword(@PathVariable String email) throws MessagingException {
		ResponseEntity<Message> m = contentProviderDao.forgotPassword(email);
		return m;
	}
	
	
	@GetMapping("/resetPwd/{token}/{password}")
	public ResponseEntity<Message> restPassword(@PathVariable String token, @PathVariable String password) {
		ResponseEntity<Message> m = contentProviderDao.resetPassword(token, password);
		return m;
	}
	
	@GetMapping("/checkemail/{email}")
	public List<ContentProvider> findCpByEmail(@PathVariable String email) {
		return contentProviderDao.findCpByEmail(email);
	}
	
	//findCpBynomContent
	@GetMapping("/checknomContent/{nomContent}")
	public List<ContentProvider> findCpBynomContent(@PathVariable String nomContent) {
		return contentProviderDao.findCpBynomContent(nomContent);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/new-cp")
	public void newChanson(@Valid @RequestBody ContentProvider p) {
		ContentProvider ch=new ContentProvider(p.getId(),
				
				p.getEmail(),
				
				
				
				//p.getAlbum(),
				//p.getUser(),
				//p.getPlatformes(),
				p.getArtistes());
		System.out.println(ch.getArtistes().size());
		p=ch;
		contentProviderRepo.save(p);
		// ChansonRepository.updatecdate(p.getId());
	}
	
	
	@RequestMapping(path = "/uploadImage/{id}", method = RequestMethod.PUT)
	public org.springframework.http.ResponseEntity.BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile files,@PathVariable("id") Integer id) throws IOException {
		System.out.println("Original Image Byte Size - " + files.getBytes().length);
		ContentProvider img = new ContentProvider(files.getOriginalFilename(), files.getContentType(), compressBytes(files.getBytes()));
		contentProviderRepo.savenew(files.getOriginalFilename(), compressBytes(files.getBytes()), files.getContentType(), id);
		return null;
		}
	
	
	@RequestMapping(path = "/getImage/{id}", method = RequestMethod.GET)
	public ContentProvider getImage(@PathVariable("id") Integer id) throws IOException {
		Optional<ContentProvider> retrievedImage = contentProviderRepo.findById(id);
		System.out.println("img "+retrievedImage);
			if(retrievedImage.get().getType()!=null) {
				ContentProvider img = new ContentProvider(retrievedImage.get().getName(), retrievedImage.get().getType(),
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

	
	
	@GetMapping("/testCp")
	public List<ContentProvider> getAllDetail()
	{
		return contentProviderRepo.findAll();
	}
	
	
}
