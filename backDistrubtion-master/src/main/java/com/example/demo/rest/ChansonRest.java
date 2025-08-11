package com.example.demo.rest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.mail.Multipart;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.ChansonDao;
import com.example.demo.entities.Artiste;
import com.example.demo.entities.Chanson;
import com.example.demo.repository.ChasnonRepo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;


@RestController
@RequestMapping("/chanon")
@CrossOrigin(origins = "http://192.168.1.111:4200")
public class ChansonRest {

	
	@Autowired
	ChansonDao chansonDao ;
	
	
	@PostMapping("/addch")
	public ResponseEntity<Chanson> addConsultaionDetails(@RequestBody Chanson c) {
		return chansonDao.addConsultaionDetails(c);
	}



	@Autowired
	uploadchanson uploadchanson ; 
	
	@RequestMapping("/foloader/{id}")
	public byte[] getChansonPar(@PathVariable(value = "id") Integer id) throws Exception {
		return uploadchanson.getChanson(id);
	}



	@Autowired
	ChasnonRepo chanRepo;

	
	

	@Autowired
	ChasnonRepo ChasnonRepo ;

	
	@PostMapping("/upload/ch")
	public ResponseEntity<?> uploadChanson(@RequestParam  MultipartFile file, @RequestParam Integer id)
			throws JsonParseException, JsonMappingException, Exception {
		return uploadchanson.uploadChanson(file, id);
	}
	
	
	
	
	
	
	/*@RequestMapping(path = "/uploadChanson/{id}", method = RequestMethod.POST)
	public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile files,@PathVariable("id") Integer id) throws IOException {
		System.out.println("Original Image Byte Size - " + files.getBytes().length);
		Chanson img = new Chanson(files.getOriginalFilename(), files.getContentType(), compressBytes(files.getBytes()));
		ChasnonRepo.savenewIn(files.getOriginalFilename(), compressBytes(files.getBytes()), files.getContentType(), id);
		return null;
		}*/
	
	@RequestMapping(path = "/uploadCover", method = RequestMethod.PUT)
	public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile files) throws IOException {
		System.out.println("Original Image Byte Size - " + files.getBytes().length);
		Chanson img = new Chanson(files.getOriginalFilename(), files.getContentType(), compressBytes(files.getBytes()));
		ChasnonRepo.savenew(files.getOriginalFilename(), compressBytes(files.getBytes()), files.getContentType());
		return null;
		}
	
	@PutMapping("/update")
	public Chanson updateChanson(@RequestBody Chanson chanson) {
		return chansonDao.updateChanson(chanson);
	}

	
	@RequestMapping("/{id}")
	public Chanson getChanson(@PathVariable(value = "id") Integer id) {
		return chansonDao.getChanson(id);
	}


		
	
	 
	
	
	@RequestMapping("/all")
	public List<Chanson> getAllchanson() {
		return chansonDao.getAllchanson();
	}

	//chansonByArtiste
	

	@RequestMapping(path = "/allChanson/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> chansonById(@PathVariable("id") Integer id) {
		
		System.out.println(Object.class);
		return ChasnonRepo.chansonByArtiste(id);
	}
	
	
	@RequestMapping(path = "/allChansonCp/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> chansonByIdCp(@PathVariable("id") Integer id) {
		
		System.out.println(Object.class);
		return ChasnonRepo.chansonByArtisteCp(id);
	}

	@PostMapping("/add")
	public Chanson add(@RequestBody Chanson chanson) {
		return chansonDao.add(chanson);
	}

	
	
	
	@PostMapping("/ajouterChanson")
	public ResponseEntity<Chanson> addChonson(Chanson c) {
		return chansonDao.addChonson(c);
	} 
	
	
	

	/*@PostMapping("/newChanson")
	public void newChanson(@Valid @RequestBody Chanson p) {
		Chanson ch=new Chanson(p.getId(),
				p.getCdate(),
				p.getNom(),
				p.getGenre(),
				p.getDatec(),
				p.getType(),
				p.getRbt_src(),
				p.getFeaturing(),
				p.getUdate(),
				p.getAlb(),
				(Artiste) p.getArtiste(),
				p.getPlatformes(),
				p.getOperateurs());
		System.out.println(ch.getPlatformes().size());
		p=ch;
		ChasnonRepo.save(p);
		}*/
		// ChansonRepository.updatecdate(p.getId());
	
	@DeleteMapping("/deletechanson/{id}")
	public void deleteAdminId(@PathVariable("id") Integer id) {
		ChasnonRepo.deleteById(id);
	}
	
	

	@RequestMapping(path = "/getImage/{id}", method = RequestMethod.GET)
	public Chanson getImage(@PathVariable("id") Integer id) throws IOException {
		Optional<Chanson> retrievedImage = chanRepo.findById(id);
		System.out.println("img "+retrievedImage);
			if(retrievedImage.get().getType()!=null) {
				Chanson img = new Chanson(retrievedImage.get().getName(), retrievedImage.get().getType(),
						decompressBytes(retrievedImage.get().getPicByte()));
				return img;
			}
		else return	retrievedImage.get();

	}
	
	
	/*@PostMapping("uploadChanson")
	public ResponseEntity<?> creatChanson(@RequestPart("chanson")Chanson chanson,@RequestPart("file") MultipartFile file)
	{
		if(ChasnonRepo.existsChansonByFileNameEquals(chanson.getFileName()) || 
			ChasnonRepo.existsChansonByNomEquals(chanson.getNom()) )
		{
			return ResponseEntity.badRequest().body("Taken");
		}
		
		else {
			System.out.println("uploading file ...");
			
		}
		return null;
		
	}*/
	@PostMapping("/upload")
	public ResponseEntity<?> handFileUpload(@RequestParam("file")MultipartFile file)
	{
		String fileName = file.getOriginalFilename();
		
		try {
			file.transferTo(new File("C:\\upload\\"+fileName));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.ok("File Uplaad succ");
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
	
	@PostMapping("/ajouter-Chanson")
	public Chanson ajouterChanson(@Valid @RequestBody Chanson p) {
	/*	Chanson ch=new Chanson(p.getId(),
				p.getNom(),
				p.getFeaturing(),
				p.getGenre(),
				p.getSouGenre(),p.getType(),
				p.getPlatformes());
		System.out.println(ch.getPlatformes().size());
		p=ch;*/
		return ChasnonRepo.save(p);
		
	
		}
	
	

	@PostMapping("/ajouter-ChansonCp")
	public void ajouterChansonCp(@Valid @RequestBody Chanson p) {
	
		
		ChasnonRepo.save(p);
		}
	
	
}
