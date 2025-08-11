package com.example.demo.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ExcelOrange.ExcelTT.StateArtisteTotal;
import com.example.demo.ExcelOrange.ExcelTT.StateCategoryTotal;
import com.example.demo.ExcelOrange.ExcelTT.StateChansonTotal;
import com.example.demo.ExcelOrange.ExcelTT.StateTotalTT;
import com.example.demo.Interface.TTExcel;
import com.example.demo.dao.ArpuPlusDao;
import com.example.demo.pdf.ExportStatDateTT;
import com.example.demo.repository.TunisieTelecomeRepo;

@RestController
@RequestMapping("/tt")
@CrossOrigin(origins = "http://192.168.1.111:4200")
public class ArpuPlusRest {

	
	@Autowired
	ArpuPlusDao arpuPlusDao ;
	
	@Autowired
	
	TunisieTelecomeRepo tunisieTelecomeRepo ;
	
	@Autowired
	ExportStatDateTT exportStatDateTT ;

	@GetMapping("/allArpuPlus")
	public List<Object[]> getAllArpuPlus() {
		return arpuPlusDao.getAllArpuPlus();
	}
	
	@GetMapping("/allMusicBee")
	public List<Object[]> getAllMusicBee() {
		return arpuPlusDao.getMusicBee();
	}
	
	
	@GetMapping("/allChrome")
	public List<Object[]> getAllChrome() {
		return arpuPlusDao.getChrome();
	}
	
	@GetMapping("/allDigitalsound")
	public List<Object[]> getAllDigitalsound() {
		return arpuPlusDao.getDigitalsound();
	}
	
	@GetMapping("/allArtsiteArpuPlus")
	public List<Object[]> getAllArtisteArpuPlus() {
		return arpuPlusDao.getAllArtisteArpuPlus();
	}
	
	@GetMapping("/allArtsiteMusicBee")
	public List<Object[]> getAllArtisteMusicBee() {
		return arpuPlusDao.getAllArtisteMusicBee();
	}
	
	
	@GetMapping("/allArtsiteDigitalsound")
	public List<Object[]> getAllArtisteDigitalsound() {
		return arpuPlusDao.getAllArtisteDigitalsound();
	}
	
	
	@GetMapping("/allArtsiteChrome")
	public List<Object[]> getAllArtisteChrome() {
		return arpuPlusDao.getAllArtisteChrome();
	} 
	
	
	@GetMapping("/allChansonArpuPlus")
	public List<Object[]> getAllChanosnArpuPlus() {
		return arpuPlusDao.getAllChanosnArpuPlus();
	}
	
	
	
	@GetMapping("/allChansonMusicBee")
	public List<Object[]> getAllChanosnMusicBee() {
		return arpuPlusDao.getAllChanosnMusicBee();
	}
	
	@GetMapping("/allChansonChrome")
	public List<Object[]> getAllChanosnChrome() {
		return arpuPlusDao.getAllChanosnChrome();
	}
	
	
	@GetMapping("/allChansonDigitalsound")
	public List<Object[]> getAllChanosnDigitalsound() {
		return arpuPlusDao.getAllChanosDisitalSound();
	}
	
	
	@GetMapping("/allSubCatArpuPlus")
	public List<Object[]> getAllCatSubCatArpuPlus() {
		return arpuPlusDao.getAllCatSubCatArpuPlus();
	} 
	
	
	
	@RequestMapping(path = "/StatTotalARpuPlusExcel", method = RequestMethod.GET)

	public void StatTotalOrangeExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statARPUPlusExcel();
		
		StateTotalTT excel = new StateTotalTT(detailsArtistes);
		excel.export(response);
	}
	
	
	
	@GetMapping("/StatArtisteArpuPlusExcel")
	public void StatArtisteOrangeExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statArtisteARPUExcel();
		
		StateArtisteTotal excel = new StateArtisteTotal(detailsArtistes);
		excel.export(response);
	}
	
	
	
	@GetMapping("/StatChansonArpuPlusExcel")
	public void StatChanosnOrangeExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statChansonSpExcel();
		
		StateChansonTotal excel = new StateChansonTotal(detailsArtistes);
		excel.export(response);
	}
	
	
	@GetMapping("/StatCatSubCatArpuPlusExcel")
	public void StatCatSubCatTTExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statSouCategoryARPUExcel();
		
		StateCategoryTotal excel = new StateCategoryTotal(detailsArtistes);
		excel.export(response);
	}
	
	
	
	
	@GetMapping("/TotstatDateArpuPlusPdf")
	public ResponseEntity<InputStreamResource> statDatePdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statARPUPlusExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateDatePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	
	@GetMapping("/TotstatArtisteArpuPlusPdf")
	public ResponseEntity<InputStreamResource> TotstatArtisteTTPdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statArtisteARPUExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateArtistePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	
	@GetMapping("/TotstatChansonArpuPlusPdf")
	public ResponseEntity<InputStreamResource> TotstatChansonTTPdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statChansonSpExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateChansonPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	@GetMapping("/TotstatCatArpuPlusPdf")
	public ResponseEntity<InputStreamResource> TotstatCatTTPdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statSouCategoryARPUExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateCatPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
}
