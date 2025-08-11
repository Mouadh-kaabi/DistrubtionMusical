package com.example.demo.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.RbtTTDao;
import com.example.demo.entities.Chanson;
import com.example.demo.entities.RbtTT;
import com.example.demo.repository.RbtTTRepo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/rbt")

@CrossOrigin(origins = "http://192.168.1.111:4200")
public class RbttRest {
	
	@Autowired
	
	RbtTTDao rbtTTDao ;
	
	@GetMapping("/rbtFolader/{id}")
	public byte[] getRbt(@PathVariable("id") Integer id) throws Exception {
		return rbtTTDao.getRbt(id);
	}



	@Autowired
	RbtTTRepo rbtTTRepo ;

	
	@RequestMapping("/all")
	public List<RbtTT> getAllRbtTT() {
		return rbtTTDao.getAllRbtTT();
	}

	@RequestMapping(path = "/allRbtTT/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> chansonById(@PathVariable("id") Integer id) {
		
		System.out.println(Object.class);
		return rbtTTRepo.RbtTTByArtiste(id);
	}

	@PostMapping("/addTT")
	
	public RbtTT addAlbum( @RequestBody RbtTT p) {
	
		return rbtTTRepo.save(p);
	}
	
	@PostMapping("/ajouter-Rbt")
	public RbtTT ajouterChanson(@Valid @RequestBody RbtTT p) {
	/*	Chanson ch=new Chanson(p.getId(),
				p.getNom(),
				p.getFeaturing(),
				p.getGenre(),
				p.getSouGenre(),p.getType(),
				p.getPlatformes());
		System.out.println(ch.getPlatformes().size());
		p=ch;*/
		return rbtTTRepo.save(p);
		
	
		}
	
@PostMapping("/addTTCp")
	
	public RbtTT addAlbumCp(@Valid @RequestBody RbtTT p) {
	
	//	return rbtTTDao.addRbt(p);
	
	
	return rbtTTRepo.save(p);
	}
	
	
	@RequestMapping(path = "/uploadIn", method = RequestMethod.PUT)
	public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile files) throws IOException {
		System.out.println("Original Image Byte Size - " + files.getBytes().length);
		RbtTT img = new RbtTT(files.getOriginalFilename(), files.getContentType(), compressBytes(files.getBytes()));
		rbtTTRepo.savenewIn(files.getOriginalFilename(), compressBytes(files.getBytes()), files.getContentType());
		return null;
		}
	
	
	@RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
	public RbtTT getImage(@PathVariable("id") Integer id) throws IOException {
		Optional<RbtTT> retrievedImage = rbtTTRepo.findById(id);
		System.out.println("img "+retrievedImage);
			if(retrievedImage.get().getType()!=null) {
				RbtTT img = new RbtTT(retrievedImage.get().getName(), retrievedImage.get().getType(),
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
	
	
	@DeleteMapping("/deleteRbtTT/{id}")
	public void deleteRbt(@PathVariable("id") Integer id) {
		rbtTTRepo.deleteById(id);
	}
	
	@RequestMapping(path = "/allrbt/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> RBTById(@PathVariable("id") Integer id) {
		
		System.out.println(Object.class);
		return rbtTTRepo.chansonByArtiste(id);
	}
	
	
	
	@RequestMapping(path = "/allrbtCp/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> RBTByIdCp(@PathVariable("id") Integer id) {
		
		System.out.println(Object.class);
		return rbtTTRepo.chansonByCp(id);
	}
	
	
	
	@PostMapping("/uploadRbt/ch")
	public ResponseEntity<?> uploadRbt(@RequestParam  MultipartFile file, @RequestParam Integer id)
			throws JsonParseException, JsonMappingException, Exception {
		return rbtTTDao.uploadRbt(file, id);
	}
	
}
