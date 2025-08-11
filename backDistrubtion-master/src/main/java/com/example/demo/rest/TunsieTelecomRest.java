package com.example.demo.rest;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Interface.TTExcel;
import com.example.demo.dao.TunisieTelecomDao;
import com.example.demo.pdf.ExportStatDateTT;
import com.example.demo.repository.TunisieTelecomeRepo;

@RestController
@RequestMapping("/tt")
@CrossOrigin(origins = "http://192.168.1.111:4200")
public class TunsieTelecomRest {

	
	@Autowired
	TunisieTelecomDao tunisieTelecomDao ;
	
	@Autowired
	TunisieTelecomeRepo tunisieTelecomeRepo ;
	
	ExportStatDateTT exportStatDateTT ;

	@GetMapping("/allTunsieTelcom")
	public List<Object[]> getAllTunisieTelecom() {
		return tunisieTelecomDao.getAllTunisieTelecom();
	}
	@GetMapping("/allTunsieTelcomArtiste")
	public List<Object[]> getAllArtisteTunisieTelecom() {
		return tunisieTelecomDao.getAllArtisteTunisieTelecom();
	}

	@GetMapping("/allTunsieTelcomChanson")
	public List<Object[]> getAllChanosnTunisieTelecom() {
		return tunisieTelecomDao.getAllChanosnTunisieTelecom();
	}

	@GetMapping("/allTunsieTelcomCatSub")
	public List<Object[]> getAllCatSubCatTunisieTelecom() {
		return tunisieTelecomDao.getAllCatSubCatTunisieTelecom();
	}
	
	
	
	@GetMapping("/TotstatDateTuniseTelecomExcelPdf")
	public ResponseEntity<InputStreamResource> statDatePdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statTunisiTelecomExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateDatePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	@GetMapping("/TotstatArtisteTuniseTelecomPdf")
	public ResponseEntity<InputStreamResource> TotstatArtisteTTPdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statArtisteTunisieTelecomExce();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateArtistePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	@GetMapping("/TotstatChansonTuniseTelecomPdf")
	public ResponseEntity<InputStreamResource> TotstatChansonTTPdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statChansonTunisieTelecomExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateChansonPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	@GetMapping("/TotstatCatTuniseTelecomPdf")
	public ResponseEntity<InputStreamResource> TotstatCatTTPdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statSouCategoryTuniseTelecomExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateCatPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	
	
	
}
