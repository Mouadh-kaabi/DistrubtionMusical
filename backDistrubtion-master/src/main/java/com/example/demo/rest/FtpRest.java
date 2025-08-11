package com.example.demo.rest;

import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dao.FtpDao;
import com.example.demo.entities.FTP;
import com.example.demo.repository.FtpRepo;

@RestController
@RequestMapping("/ftp")
@CrossOrigin(origins = "http://192.168.1.111:4200")
public class FtpRest {

	
	@Autowired
	FtpDao ftpDao ;
	
	@Autowired
	FtpRepo ftpRepo ;

	@PostMapping("/add")
	public FTP addFtp(@RequestBody FTP alb) {
		return ftpDao.addFtp(alb);
	}

	@GetMapping("/all")
	public List<FTP> getAllFTP() {
		return ftpDao.getAllFTP();
	}

	@GetMapping("{id}")
	public FTP getFTP(@PathVariable(value = "id") Integer id) {
		return ftpDao.getFTP(id);
	}

	@PutMapping("/update")
	public FTP updateFTP(@RequestBody FTP ftp) {
		return ftpDao.updateFTP(ftp);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public void deleteFtpId(@PathVariable("id") Integer id) {
		ftpRepo.deleteById(id);
		
	}
	
	@PostMapping("/uploadFile")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
	                               RedirectAttributes redirectAttributes) {
	    String FTP_ADDRESS = "https://smartstore.tn/sfm/";
	    String LOGIN = "mouadh";
	    String PSW = "9rxGZ57a";

	    FTPClient con = null;

	    try {
	        con = new FTPClient();
	        con.connect(FTP_ADDRESS);

	        if (con.login(LOGIN, PSW)) {
	            con.enterLocalPassiveMode(); // important!
	            con.setFileType(FTP.BINARY_FILE_TYPE);

	            boolean result = con.storeFile(file.getOriginalFilename(), file.getInputStream());
	            con.logout();
	            con.disconnect();
	            redirectAttributes.addFlashAttribute("message",
	                    "You successfully uploaded " + file.getOriginalFilename() + "!");
	        }
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("message",
	                "Could not upload " + file.getOriginalFilename() + "!");
	    }

	    return "redirect:https://smartstore.tn/sfm/";
	}
	
	
}
