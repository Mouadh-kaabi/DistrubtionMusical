package com.example.demo.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.ExcelOrange.StateArtisteOrange;
import com.example.demo.ExcelOrange.StateExcelOrange;
import com.example.demo.ExcelOrange.StatePlatformeOrangExcelById;
import com.example.demo.ExcelOrange.ExcelTT.StateArtisteCpTotalById;
import com.example.demo.ExcelOrange.ExcelTT.StateArtisteTotal;
import com.example.demo.ExcelOrange.ExcelTT.StateArtisteTotalById;
import com.example.demo.ExcelOrange.ExcelTT.StateCategoryCpById;
import com.example.demo.ExcelOrange.ExcelTT.StateCategoryTotal;
import com.example.demo.ExcelOrange.ExcelTT.StateCategoryTotalById;
import com.example.demo.ExcelOrange.ExcelTT.StateChansonCpTotalById;
import com.example.demo.ExcelOrange.ExcelTT.StateChansonTotal;
import com.example.demo.ExcelOrange.ExcelTT.StateChansonTotalById;
import com.example.demo.ExcelOrange.ExcelTT.StateRevenuTotal;
import com.example.demo.ExcelOrange.ExcelTT.StateRevenuTotalById;
import com.example.demo.ExcelOrange.ExcelTT.StateTotalCpById;
import com.example.demo.ExcelOrange.ExcelTT.StateTotalTT;
import com.example.demo.ExcelOrange.ExcelTT.StateTotalTTById;
import com.example.demo.Exception.DateException;
import com.example.demo.Exception.nullException;
import com.example.demo.Interface.OrangeExcel;
import com.example.demo.Interface.TTExcel;
import com.example.demo.dao.TTDao;
import com.example.demo.entities.Artiste;
import com.example.demo.entities.Details;
import com.example.demo.entities.TunisieTelecom;
import com.example.demo.excel.ExcelServiceTT;
import com.example.demo.intefrace.ArtisteGe;
import com.example.demo.payload.response.ResponseMessage;
import com.example.demo.pdf.ExportStatDateTT;
import com.example.demo.repository.ArtisteRepo;
import com.example.demo.repository.TunisieTelecomeRepo;

@CrossOrigin(origins = "http://192.168.1.95:8086")
@RestController
@RequestMapping("/tt")
public class TTRest {

	@Autowired
	ExcelServiceTT excelServiceTT ;
	
	
	@Autowired
	TTDao ttDao ;
	

	
	@Autowired
	TunisieTelecomeRepo tunisieTelecomeRepo ; 
	

	@Autowired
	ExportStatDateTT exportStatDateTT ;

	@Autowired
	ArtisteRepo artisteRepo ;
	
	@PostMapping("/uploadExcelTT")
	public ResponseEntity<ResponseMessage> uploadFileTT(@RequestParam("file") MultipartFile file) {
		String message = "";

		if (ExcelServiceTT.hasExcelFormat(file)) {
			try {
				excelServiceTT.uploadExcelTT(file);
				
				message = "l'importation et terminé avec succes: " + file.getOriginalFilename();
				
				
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (DateException | ParseException | InvalidFormatException | nullException e) {
				message = "echec d'importation: " + e.getMessage();
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}
		message = "echec d'importation: le fichier n'est pas de type excel !";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}	
	
	@GetMapping("/stateDateTT")
	public List<Object[]> statDateTT() {
			
		
		return ttDao.statDateTT();
	}
	
	
	@RequestMapping(path = "/statTTSmartMusicTotal/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statDateSmartById(@PathVariable("id") Integer id) {
		return tunisieTelecomeRepo.statDateSmartById(id);
	}
	@GetMapping("/stateArtisteTT")
	public List<Object[]> statArtsiteTT() {
		return ttDao.statArtsiteTT();
	}
	
	@RequestMapping(path = "/stateArtisteTT/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> stateArtisteTTById(@PathVariable("id") Integer id) {
		
		System.out.println(Object.class);
		return tunisieTelecomeRepo.statArtisteTTById(id);
	}
	
	
	@GetMapping("/stateChansonTT")
	public List<Object[]> statChansonTT() {
		return ttDao.statChansonTT();
	}
	
/*	@RequestMapping(path = "/statSmartChansonById/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statTTSmChansonById(@PathVariable("id") Integer id) {
		
		System.out.println(Object.class);
		return tunisieTelecomeRepo.statTTSmartChansonById(id);
	}*/
	@RequestMapping(path = "/statChansonBySmartById/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statChansonBySmartById(@PathVariable("id") Integer id) {
		
		System.out.println(Object.class);
		return tunisieTelecomeRepo.statTTSmartChansonById(id);
	}
	
	//statArtisteTTById
	
	
	@GetMapping("/stateCategoryTT")
	public List<Object[]> statcategoryTT() {
		return ttDao.statcategoryTT();
	}
	
	
	
	
	@RequestMapping(path = "/statSmartSubCatById/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statTTCatSmartById(@PathVariable("id") Integer id) {
		
		System.out.println(Object.class);
		return tunisieTelecomeRepo.statTTSmartCatById(id);
	}
	/*@GetMapping("/allartiste")
	public List<ArtisteGe> getNomArtiste()
	{
		
		List<ArtisteGe>listart= tunisieTelecomeRepo.listNomArtiste();
		
		//List<Artiste> artistes = new ArrayList<>();
		
		//artisteRepo.saveAll(listart);
		//artistes.add((Artiste) listart);
		
		return listart ;
		
		
	}*/
	
	
	
	@RequestMapping(path = "/StatTotalTTExcel", method = RequestMethod.GET)

	public void StatTotalOrangeExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statDateTTExcel();
		
		StateTotalTT excel = new StateTotalTT(detailsArtistes);
		excel.export(response);
	}
	
	//statDateSmartByIdExcel
	
	@RequestMapping(path = "/StatTotalTTExcel/excel/{id}", method = RequestMethod.GET)
	public void StatePlatformeOrangeExcelById(HttpServletResponse response,@PathVariable("id") Integer id ) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statDateSmartByIdExcel(id);
		
		StateTotalTTById excel = new StateTotalTTById(detailsArtistes);
		excel.export(response);
	}
	
	
	
	
	@GetMapping("/StatArtisteTTExcel")
	public void StatArtisteOrangeExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statArtisteTTExcel();
		
		StateArtisteTotal excel = new StateArtisteTotal(detailsArtistes);
		excel.export(response);
	}
	//statArtisteTTByIdExcel
	
	@RequestMapping(path = "/StatArtisteTTExcel/excel/{id}", method = RequestMethod.GET)
	public void StatArtisteTTExcelById(HttpServletResponse response,@PathVariable("id") Integer id ) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statArtisteTTByIdExcel(id);
		
		StateArtisteTotalById excel = new StateArtisteTotalById(detailsArtistes);
		excel.export(response);
	}
	
	
	
	
	//statRevenuExcel
	
	@RequestMapping(path = "/StatChansonTTExcel/excel/{id}", method = RequestMethod.GET)
	public void StatChansonTTExcelById(HttpServletResponse response,@PathVariable("id") Integer id ) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statTTSmartChansonByIdExcel(id);
		
		StateChansonTotalById excel = new StateChansonTotalById(detailsArtistes);
		excel.export(response);
	}
	
	

	@GetMapping("/StatCatSubCatTTExcel")
	public void StatCatSubCatTTExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statcategoryTTExcel();
		
		StateCategoryTotal excel = new StateCategoryTotal(detailsArtistes);
		excel.export(response);
	}
	
	
	@RequestMapping(path = "/StatCatSubCatTTExcel/excel/{id}", method = RequestMethod.GET)
	public void StatCatSubCatTTExcelById(HttpServletResponse response,@PathVariable("id") Integer id ) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statTTSmartCatByIdExcel(id);
		
		StateCategoryTotalById excel = new StateCategoryTotalById(detailsArtistes);
		excel.export(response);
	}
	@GetMapping("/statRevenuExcel")
	public void statRevenuExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statRevenuExcel();
		
		StateRevenuTotal excel = new StateRevenuTotal(detailsArtistes);
		excel.export(response);
	}
	
	@RequestMapping(path = "/statRevenuExcel/excel/{id}", method = RequestMethod.GET)
	public void statRevenuExcelById(HttpServletResponse response,@PathVariable("id") Integer id ) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.HistRevenuCpExcel(id);
		
		StateRevenuTotalById excel = new StateRevenuTotalById(detailsArtistes);
		excel.export(response);
	}
	
	@GetMapping("/TotstatDateTTPdf")
	public ResponseEntity<InputStreamResource> statDatePdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statDateTTExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateDatePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	@RequestMapping(path = "/TotstatDateTTPdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> statPaysTotalPdfByid(@PathVariable("id") Integer id)
	{
	
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statDateSmartByIdExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateDatePdfReportById(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}	
	
	@GetMapping("/TotstatArtisteTTPdf")
	public ResponseEntity<InputStreamResource> TotstatArtisteTTPdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statArtisteTTExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateArtistePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	//statArtisteTTByIdExcel
	@RequestMapping(path = "/TotstatArtisteTTPdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> TotstatArtisteTTPdfByid(@PathVariable("id") Integer id)
	{
	
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statArtisteTTByIdExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateArtistePdfReportByID(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}	
	
	/*@GetMapping("/TotstatChansonTTPdf")
	public ResponseEntity<InputStreamResource> TotstatChansonTTPdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statChansonTTExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateChansonPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}*/
	//statTTSmartChansonByIdExcel
	
	@RequestMapping(path = "/TotstatChansonTTPdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> TotstatChansonTTPdfByid(@PathVariable("id") Integer id)
	{
	
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statTTSmartChansonByIdExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateChansonPdfReportById(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}	
	
	@GetMapping("/TotstatCatTTPdf")
	public ResponseEntity<InputStreamResource> TotstatCatTTPdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statcategoryTTExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateCatPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	//statTTSmartCatByIdExcel
	
	
	@RequestMapping(path = "/TotstatCatTTPdfTTPdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> TotstatCatTTPdfByid(@PathVariable("id") Integer id)
	{
	
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statTTSmartCatByIdExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateCatPdfReportById(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}	
	//statRevenuExcel
	
	@RequestMapping(path = "/statRevenuPdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> statRevenuExcelByid(@PathVariable("id") Integer id)
	{
	
		List<TTExcel>  deatils =  tunisieTelecomeRepo.HistRevenuCpExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateRevenuPdfReportById(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}
	

	@GetMapping("/statRevenuPdf")
	public ResponseEntity<InputStreamResource> statRevenuPdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statRevenuExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateRevenuPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	@RequestMapping(path = "/statDateCp/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statDateById(@PathVariable("id") Integer id) {
		return tunisieTelecomeRepo.statDateCpById(id);
	}
	
	@RequestMapping(path = "/statDateCpPdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> statDateCpByid(@PathVariable("id") Integer id)
	{
	
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statDateCpByIdExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateDateCPPdfReportById(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}
	
	
	@RequestMapping(path = "/statDateCpExcel/excel/{id}", method = RequestMethod.GET)
	public void statDateCpById(HttpServletResponse response,@PathVariable("id") Integer id ) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statDateCpByIdExcel(id);
		
		StateTotalCpById excel = new StateTotalCpById(detailsArtistes);
		excel.export(response);
	}
	
	@RequestMapping(path = "/statCpChansonById/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statTTSmartChansonById(@PathVariable("id") Integer id) {
		
		System.out.println(Object.class);
		return tunisieTelecomeRepo.statCpChansonById(id);
	}
	
	
	@RequestMapping(path = "/statCpChansonPdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> statCpChansonByid(@PathVariable("id") Integer id)
	{
	
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statCpChansonByIdExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateChansonPdfReportCpById(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}
	
	@RequestMapping(path = "/statCpChansonExcel/excel/{id}", method = RequestMethod.GET)
	public void statCpChansonById(HttpServletResponse response,@PathVariable("id") Integer id ) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statCpChansonByIdExcel(id);
		
		StateChansonCpTotalById excel = new StateChansonCpTotalById(detailsArtistes);
		excel.export(response);
	}
	
	//statCpArtisteById
	@RequestMapping(path = "/statCpArtisteById/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statCpArtisteById(@PathVariable("id") Integer id) {
		
		System.out.println(Object.class);
		return tunisieTelecomeRepo.statCpArtisteById(id);
	}
	
	
	@RequestMapping(path = "/statCpArtistePdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> statCpArtisteByid(@PathVariable("id") Integer id)
	{
	
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statCpArtisteByIdExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateArtistePdfReportCpById(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}
	
	
	@RequestMapping(path = "/statCpArtisteExcel/excel/{id}", method = RequestMethod.GET)
	public void statCpArtisteById(HttpServletResponse response,@PathVariable("id") Integer id ) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statCpArtisteByIdExcel(id);
		
		StateArtisteCpTotalById excel = new StateArtisteCpTotalById(detailsArtistes);
		excel.export(response);
	}
	
	@RequestMapping(path = "/statCpCatById/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statTTSmartCatById(@PathVariable("id") Integer id) {
		
		System.out.println(Object.class);
		return tunisieTelecomeRepo.statTTCpCatById(id);
	}
	
	//statTTCpCatByIdExcel
	@RequestMapping(path = "/statTTCpCatPdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> statTTCpCatPdfByid(@PathVariable("id") Integer id)
	{
		
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statTTCpCatByIdExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateCatCpPdfReportById(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}
	
	
	@RequestMapping(path = "/HistRevenuExcel/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> HistRevenuExcelByid(@PathVariable("id") Integer id)
	{
		
		List<TTExcel>  deatils =  tunisieTelecomeRepo.HistRevenuExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateCatCpPdfReportById(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}
	
	//HistRevenuExcel
	
	@RequestMapping(path = "/statTTCpCatExcel/excel/{id}", method = RequestMethod.GET)
	public void statTTCpCatById(HttpServletResponse response,@PathVariable("id") Integer id ) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statTTCpCatByIdExcel(id);
		
		StateCategoryCpById excel = new StateCategoryCpById(detailsArtistes);
		excel.export(response);
	}
	
	@RequestMapping(path = "/HistRevenu/by-id/{id}", method = RequestMethod.GET)
	public List<Object[]> HistRevenu(@PathVariable("id") Integer id) {
		return tunisieTelecomeRepo.HistRevenu(id);
	}
	
	
	
	@RequestMapping(path = "/HistRevenuCp/by-id/{id}", method = RequestMethod.GET)
	public List<Object[]> HistRevenuCp(@PathVariable("id") Integer id) {
		return tunisieTelecomeRepo.HistRevenuCp(id);
	}

	
	@RequestMapping(path = "/HistRevenuTT", method = RequestMethod.GET)
	public List<Object[]> statRevenu() {
		return tunisieTelecomeRepo.statRevenu();
	}
	
/*
@RequestMapping(path = "/deleteFileByDateTT/{date1}/{file}", method = RequestMethod.DELETE)
public void deleteByFichier(@PathVariable java.sql.Date date1,@PathVariable String file) {
	tunisieTelecomeRepo.deleteByDateFichierTT(date1,file);
}*/

/*@RequestMapping(path = "/deleteFileByDateTT/{date1}/{date2}/{file}", method = RequestMethod.DELETE)
public void deleteByFichier(@PathVariable java.sql.Date date1,,@PathVariable String file) {
	tunisieTelecomeRepo.deleteByDateFichierTT(date1,date2,file);
}*/
	

@RequestMapping(path = "/deleteFileByDateTT/{date1}/{date2}/{file}", method = RequestMethod.DELETE)
public void deleteByFichier(@PathVariable java.sql.Date date1, @PathVariable java.sql.Date date2,@PathVariable String file) {
	tunisieTelecomeRepo.deleteByDateFichier(date1,date2,file);
}
	//HistRevenuPdfArtiste



@RequestMapping(path = "/statDateLabelTT/by-userId/{id}", method = RequestMethod.GET)
public List<Object[]> statDateLabelById(@PathVariable("id") Integer id) {
	return tunisieTelecomeRepo.statDateLabelById(id);
}

/*@RequestMapping(path = "/statById/by-userId/{id}", method = RequestMethod.GET)
public Artiste statTotalBycpId(@PathVariable("id") Integer id) {
	
	Artiste artiste =  artisteRepo.findByCpId(id);
	return artiste ;
}*/

@RequestMapping(path = "/statCpChansonLabelById/by-userId/{id}", method = RequestMethod.GET)
public List<Object[]> statTTChansonLabelById(@PathVariable("id") Integer id) {
	
	System.out.println(Object.class);
	return tunisieTelecomeRepo.statCpChansonByIdLabel(id);
}



	
@GetMapping("/statPlatformeTT")
public List<Object[]> statPlatformeOrange() {
	return tunisieTelecomeRepo.statPlateformeTT();
}



@RequestMapping(path = "/statPlateformeTT/by-userId/{id}", method = RequestMethod.GET)
public List<Object[]> statPlateformeById(@PathVariable("id") Integer id) {
	return tunisieTelecomeRepo.statPlateformeTTById(id);
}


/*@RequestMapping(path = "/statChansonLabel/by-userId/{id}", method = RequestMethod.GET)
public List<Object[]> statChansonLabelById(@PathVariable("id") Integer id) {
	return tunisieTelecomeRepo.statCpChansonById(id);
}*/
	
}
