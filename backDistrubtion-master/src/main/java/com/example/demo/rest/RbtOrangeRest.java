package com.example.demo.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.RbtOrangeDao;
import com.example.demo.dao.RbtTTDao;
import com.example.demo.entities.RbtOrange;
import com.example.demo.entities.RbtTT;
import com.example.demo.repository.RbttOrangeRepo;

@RestController
@RequestMapping("/rbt")
@CrossOrigin(origins = "http://192.168.1.111:4200")
public class RbtOrangeRest {

	
	@Autowired
	
	RbtOrangeDao rbtOrangeDao ;
	
	@Autowired
	RbttOrangeRepo rbttOrangeRepo ;
	
	@GetMapping("/allOrange")
	public List<RbtOrange> getAllRbtTTOrange() {
		return rbtOrangeDao.getAllRbtTTOrange();
	}

	@PostMapping("/addOrange")
	public RbtOrange addRbt(@RequestBody RbtOrange rbtOrange) {
		return rbtOrangeDao.addRbt(rbtOrange);
	}
	
	
	@RequestMapping(path = "/uploadInOrange", method = RequestMethod.PUT)
	public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile files) throws IOException {
		System.out.println("Original Image Byte Size - " + files.getBytes().length);
		RbtTT img = new RbtTT(files.getOriginalFilename(), files.getContentType(), compressBytes(files.getBytes()));
		rbttOrangeRepo.savenewIn(files.getOriginalFilename(), compressBytes(files.getBytes()), files.getContentType());
		return null;
		}
	
	
	@RequestMapping(path = "/getRbttOrange/{id}", method = RequestMethod.GET)
	public RbtOrange getImage(@PathVariable("id") Integer id) throws IOException {
		Optional<RbtOrange> retrievedImage = rbttOrangeRepo.findById(id);
		System.out.println("img "+retrievedImage);
			if(retrievedImage.get().getType()!=null) {
				RbtOrange img = new RbtOrange(retrievedImage.get().getName(), retrievedImage.get().getType(),
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

	
	
	@RequestMapping(path = "/allRbtOrange/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> chansonById(@PathVariable("id") Integer id) {
		
		System.out.println(Object.class);
		return rbttOrangeRepo.rbtOrangeByArtiste(id);
	}
	
	
	
	
}
