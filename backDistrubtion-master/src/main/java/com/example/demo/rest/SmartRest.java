package com.example.demo.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ExcelOrange.ExcelTT.StateArtisteTotal;
import com.example.demo.ExcelOrange.ExcelTT.StateCategoryTotal;
import com.example.demo.ExcelOrange.ExcelTT.StateChansonTotal;
import com.example.demo.ExcelOrange.ExcelTT.StateTotalTT;
import com.example.demo.Interface.TTExcel;
import com.example.demo.dao.SmartDao;
import com.example.demo.pdf.ExportStatDateTT;
import com.example.demo.repository.TunisieTelecomeRepo;

@RestController
@RequestMapping("/tt")

@CrossOrigin(origins = "http://192.168.1.111:4200")
public class SmartRest {

	
	@Autowired
	SmartDao smartDao ;
	
	@Autowired
	TunisieTelecomeRepo tunisieTelecomeRepo ;

	@Autowired
	ExportStatDateTT exportStatDateTT ;
	
	@GetMapping("/allSmart")
	public List<Object[]> getAllSmart() {
		return smartDao.getAllSmart();
	}
	@GetMapping("/allArtisteSmart")
	public List<Object[]> getAllArtisteSmart() {
		return smartDao.getAllArtisteSmart();
	}
	
	@GetMapping("/allArtisteMusicBi")
	public List<Object[]> statArtisteSmartMusicBee() {
		return smartDao.getAllArtisteMusic();
	}
	
	//statArtisteSmartMusicBee
	@GetMapping("/allChansonSmart")
	public List<Object[]> getAllChanosnSmart() {
		return smartDao.getAllChanosnSmart();
	}
	@GetMapping("/allSouCatSmart")
	public List<Object[]> getAllCatSubCatSmart() {
		return smartDao.getAllCatSubCatSmart();
	}
	
	
	@RequestMapping(path = "/StatTotalSmartExcel", method = RequestMethod.GET)

	public void StatTotalOrangeExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statSmartExcel();
		
		StateTotalTT excel = new StateTotalTT(detailsArtistes);
		excel.export(response);
	}
	
	
	
	
	@GetMapping("/StatArtisteSmartExcel")
	public void StatArtisteOrangeExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statArtisteSmartExcel();
		
		StateArtisteTotal excel = new StateArtisteTotal(detailsArtistes);
		excel.export(response);
	}
	
	
	
	
	@GetMapping("/StatChansonSmartExcel")
	public void StatChanosnOrangeExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statChansonSmartExcel();
		
		StateChansonTotal excel = new StateChansonTotal(detailsArtistes);
		excel.export(response);
	}
	
	
	
	
	@GetMapping("/StatCatSubCatSmartExcel")
	public void StatCatSubCatTTExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statSouCategorySmartExcel();
		
		StateCategoryTotal excel = new StateCategoryTotal(detailsArtistes);
		excel.export(response);
	}
	
	
	
	
	@GetMapping("/TotstatDateExcelPdf")
	public ResponseEntity<InputStreamResource> statDatePdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statSmartExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateDatePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	@GetMapping("/TotstatArtisteSmartPdf")
	public ResponseEntity<InputStreamResource> TotstatArtisteTTPdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statArtisteSmartExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateArtistePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	@GetMapping("/TotstatChansonSmartPdf")
	public ResponseEntity<InputStreamResource> TotstatChansonTTPdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statChansonSmartExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateChansonPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	@GetMapping("/TotstatCatSmartPdf")
	public ResponseEntity<InputStreamResource> TotstatCatTTPdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statSouCategorySmartExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateCatPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(path = "/statDateSmart/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statDateById(@PathVariable("id") Integer id) {
		return tunisieTelecomeRepo.statDateTTById(id);
	}
	
	
	
	@RequestMapping(path = "/statSmartChansonById/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statTTSmartChansonById(@PathVariable("id") Integer id) {
		
		System.out.println(Object.class);
		return tunisieTelecomeRepo.statTTSmartChansonById(id);
	}
	
	@RequestMapping(path = "/statSmartCatById/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statTTSmartCatById(@PathVariable("id") Integer id) {
		
		System.out.println(Object.class);
		return tunisieTelecomeRepo.statTTSmartCatById(id);
	}
	
	/*@RequestMapping(path = "/statDateSmart/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statDateById(@PathVariable("id") Integer id) {
		return tunisieTelecomeRepo.statDateTTById(id);
	}*/
	
	
	
	@RequestMapping(path = "/topArtisteSmart", method = RequestMethod.GET)
	public List<Object[]> topArtiste() {
		return tunisieTelecomeRepo.topArtisteSmart();
	}
	
	@RequestMapping(path = "/topChansonSmart", method = RequestMethod.GET)
	public List<Object[]> listChanson() {
		return tunisieTelecomeRepo.topChansonSmart();
	}
	
	
	@RequestMapping(path = "/topDateSmart", method = RequestMethod.GET)
	public List<Object[]> topDate() {
		return tunisieTelecomeRepo.topDateSmart();
	}
	
	
	@RequestMapping(path = "/ChartsChansTT", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsChans() {
		return tunisieTelecomeRepo.ChartsChansTT();
	}
	
	
	@RequestMapping(path = "/ChartsPartsRevenu/by-userId/{id}", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsPartsRevenuById(@PathVariable("id") Integer id) {
		return tunisieTelecomeRepo.ChartsPartsRevenuTT(id);
	}
	
	
	@RequestMapping(path = "/ChartsPaysTT", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsPays() {
		return tunisieTelecomeRepo.ChartsPaysTT();
	}
	
	
	
	@RequestMapping(path = "/topChansontt/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statChansonUsersById(@PathVariable("id") Integer id) {
		return tunisieTelecomeRepo.statChansonUsersByIdTT(id);
	}
	
	
	@RequestMapping(path = "/ChartsDateTT", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsDate() {
		return tunisieTelecomeRepo.ChartsDateTT();
	}
	
	
}
