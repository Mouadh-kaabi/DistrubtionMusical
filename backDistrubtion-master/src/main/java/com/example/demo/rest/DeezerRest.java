package com.example.demo.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.example.demo.BelieveExcel.StateExcelBelieveById;
import com.example.demo.BelieveExcel.StateRevenuExcelBelieve;
import com.example.demo.BelieveExcel.StateRevenuExcelBelieveById;
import com.example.demo.ExcelOrange.StateExcelOrange;
import com.example.demo.Exception.DateException;
import com.example.demo.Exception.nullException;
import com.example.demo.Interface.BelieveExcel;
import com.example.demo.Interface.DeezerExcel;
import com.example.demo.Interface.OrangeExcel;
import com.example.demo.Interface.TTExcel;
import com.example.demo.dao.DeezerDao;
import com.example.demo.deezerExcel.StateArtisteExcelDeezer;
import com.example.demo.deezerExcel.StateChansonExcelDeezer;
import com.example.demo.deezerExcel.StateExcelDeezer;
import com.example.demo.deezerExcel.StateExcelDeezerById;
import com.example.demo.deezerExcel.StateFournisseurExcelDeezer;
import com.example.demo.deezerExcel.StateRevenuExcelDeezer;
import com.example.demo.deezerExcel.StateRevenuExcelDeezerById;
import com.example.demo.excel.ExcelServiceDeezer;
import com.example.demo.excel.ExcelServiceTT;
import com.example.demo.payload.response.ResponseMessage;
import com.example.demo.pdf.ExportStatDateDeezer;
import com.example.demo.repository.DeezerRepo;

@RestController
@RequestMapping("/deezer")
@CrossOrigin(origins = "http://192.168.1.111:4200")
public class DeezerRest {

	
	@Autowired
	ExcelServiceDeezer excelServiceDeezer ;
	
	@Autowired
	DeezerDao deezerDao ;
	
	@Autowired
	DeezerRepo deezerRepo ;
	
	@Autowired
	ExportStatDateDeezer exportStatDateDeezer ;
	
	
	@GetMapping("/all")
	public List<Object[]> getAllDateDeezer() {
		return deezerDao.getAllDateDeezer();
	}

	
	
	@GetMapping("/allPlatforme")
	public List<Object[]> getAllDateDeezerPlatforme() {
		return deezerRepo.statPLatforme();
	}
	@GetMapping("/allArtisteDeezer")
	public List<Object[]> getAllArtisteDeezer() {
		return deezerDao.getAllArtisteDeezer();
	}
	@GetMapping("/allFournisseurDeezer")
	public List<Object[]> getAllFournisseurDeezer() {
		return deezerDao.getAllFournisseurDeezer();
	}
	@GetMapping("/allChansonDeezer")
	public List<Object[]> getAllChansonDeezer() {
		return deezerDao.getAllChansonDeezer();
	}
	
	@PostMapping("/uploadExcelDeezer")
	public ResponseEntity<ResponseMessage> uploadFileTT(@RequestParam("file") MultipartFile file) {
		String message = "";

		if (ExcelServiceTT.hasExcelFormat(file)) {
			try {
				excelServiceDeezer.uploadExcelDeezer(file);
				
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
	
	
	@RequestMapping(path = "/StatTotalDeezerExcel/excel", method = RequestMethod.GET)

	public void StatTotalDeezerExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<DeezerExcel> detailsArtistes =deezerRepo.statDateDeezerExcel();
		
		StateExcelDeezer excel = new StateExcelDeezer(detailsArtistes);
		excel.export(response);
	}
	
	
	@RequestMapping(path = "/StatTotalDeezerExcel/excel/{id}", method = RequestMethod.GET)

	public void StatTotalOrangeExcel(HttpServletResponse response,@PathVariable("id") Integer id ) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		
		List<DeezerExcel> detailsArtistes =deezerRepo.statDateByIdExcel(id);
		
		StateExcelDeezerById excel = new StateExcelDeezerById(detailsArtistes);
		excel.export(response);
	}
	
	
	@RequestMapping(path = "/StatArtisteDeezerExcel", method = RequestMethod.GET)

	public void StatArtisteDeezerExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<DeezerExcel> detailsArtistes =deezerRepo.statArtisteDeezerExcel();
		
		StateArtisteExcelDeezer excel = new StateArtisteExcelDeezer(detailsArtistes);
		excel.export(response);
	}
	
	
	@RequestMapping(path = "/StatChansonDeezerExcel", method = RequestMethod.GET)

	public void StatChansonDeezerExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<DeezerExcel> detailsArtistes =deezerRepo.statChansonDeezerExcel();
		
		StateChansonExcelDeezer excel = new StateChansonExcelDeezer(detailsArtistes);
		excel.export(response);
	}
	
	
	//statFournisseurDeezerExcel
	
	@RequestMapping(path = "/StatFournisseurDeezerExcel", method = RequestMethod.GET)

	public void StatFournisseurDeezerExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<DeezerExcel> detailsArtistes =deezerRepo.statFournisseurDeezerExcel();
		
		StateFournisseurExcelDeezer excel = new StateFournisseurExcelDeezer(detailsArtistes);
		excel.export(response);
	}
	
	@GetMapping("/TotstatDateDeezerPdf")
	public ResponseEntity<InputStreamResource> statDatePdf()
	{
		List<DeezerExcel>  deatils =  deezerRepo.statDateDeezerExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateDeezer.StateDatePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	//statDateByIdExcel
	
	@RequestMapping(path = "/TotstatDateDeezerPdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> statPaysTotalPdfByid(@PathVariable("id") Integer id)
	{
	
		List<DeezerExcel>  deatils =  deezerRepo.statDateByIdExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateDeezer.StateDatePdfReportById(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}	
	
	@GetMapping("/TotstatArtisteDeezerPdf")
	public ResponseEntity<InputStreamResource> TotstatArtisteDeezerPdf()
	{
		List<DeezerExcel>  deatils =  deezerRepo.statArtisteDeezerExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateDeezer.StateArtistePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	
	@RequestMapping(path = "/TotstatArtisteDeezerPdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> TotstatArtisteDeezerPdfByid(@PathVariable("id") Integer id)
	{
	
		List<DeezerExcel>  deatils =  deezerRepo.statArtisteDeezerByIdExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateDeezer.StateArtistePdfReportById(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}
	
	@GetMapping("/TotstatChansonDeezerPdf")
	public ResponseEntity<InputStreamResource> TotstatChansonDeezerPdf()
	{
		List<DeezerExcel>  deatils =  deezerRepo.statChansonDeezerExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateDeezer.StateChansonPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	@RequestMapping(path = "/TotstatChansonDeezerPdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource>TotstatChansonDeezerPdfByid(@PathVariable("id") Integer id)
	{
	
		List<DeezerExcel>  deatils =  deezerRepo.statChansonDeezerByIdExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateDeezer.StateChansonPdfReportById(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}
	
	
	@GetMapping("/TotstatFournisseurDeezerPdf")
	public ResponseEntity<InputStreamResource> TotstatDeezerPdf()
	{
		List<DeezerExcel>  deatils =  deezerRepo.statArtisteDeezerExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateDeezer.StateArtistePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
/*@RequestMapping(path = "/TotstatFournisseurDeezerPdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource>TotstatFournisseurDeezerPdfByid(@PathVariable("id") Integer id)
	{
	
		List<DeezerExcel>  deatils =  deezerRepo.statFournisseurDeezerExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateDeezer.StateChansonPdfReportById(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}*/
	
	
	@RequestMapping(path = "/statDateDeezer/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statDateById(@PathVariable("id") Integer id) {
		return deezerRepo.statDateById(id);
	}
	
	
	@RequestMapping(path = "/statDateDeezerPlatf/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statDateByIdPlatf(@PathVariable("id") Integer id) {
		return deezerRepo.statDateByIdPlatforme(id);
	}
	
	
	@RequestMapping(path = "/statArtisteDeezer/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statArtisteById(@PathVariable("id") Integer id) {
		return deezerRepo.statArtisteDeezerById(id);
	}
	
	//statChansonDeezerById
	
	/*@RequestMapping(path = "/statChansonDeezer/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statArtisteDeezerById(@PathVariable("id") Integer id) {
		return deezerRepo.statChansonDeezerById(id);
	}*/
	
	

@RequestMapping(path = "/statChansonDeezer/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statChansonById(@PathVariable("id") Integer id) {
		return deezerRepo.statChansonById(id);
	}
	
	
	
	
	@RequestMapping(path = "/HistRevenuDeezer", method = RequestMethod.GET)
	public List<Object[]> statRevenu() {
		return deezerRepo.statRevenuDeezer();
	}
	
	
	
	@RequestMapping(path = "/HistRevenuDezeer/by-id/{id}", method = RequestMethod.GET)
	public List<Object[]> HistRevenu(@PathVariable("id") Integer id) {
		return deezerRepo.HistRevenuDeezer(id);
	}
	
	
	
	
	@GetMapping("/TotRevenuPdf")
	public ResponseEntity<InputStreamResource> statRevenuExcel()
	{
		List<DeezerExcel>  deatils =  deezerRepo.statRevenuDeezerExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais =exportStatDateDeezer.StateRevenuPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	
	
	//HistRevenuOrangeExcel
	@RequestMapping(path = "/TotRevenuPdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> HistRevenuOrangeExcelByid(@PathVariable("id") Integer id)
	{
	
		List<DeezerExcel>  deatils =  deezerRepo.HistRevenuDeezerExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateDeezer.StateRevenuPdfReportById(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}
	
	
	@RequestMapping(path = "/HistRevenuDeezerExcel/excel/{id}", method = RequestMethod.GET)

	public void HistRevenuDeezerExcel(HttpServletResponse response,@PathVariable("id") Integer id ) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		
		List<DeezerExcel> detailsArtistes =deezerRepo.HistRevenuDeezerExcel(id);
		
		StateRevenuExcelDeezerById excel = new StateRevenuExcelDeezerById(detailsArtistes);
		excel.export(response);
	}
	
	//statRevenuExcel
	
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
		
		List<DeezerExcel> detailsArtistes =deezerRepo.statRevenuDeezerExcel();
		
		StateRevenuExcelDeezer excel = new StateRevenuExcelDeezer(detailsArtistes);
		excel.export(response);
	}
	@RequestMapping(path = "/statDateCp/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statDateByFournisseurId(@PathVariable("id") Integer id) {
		return deezerRepo.statDateFourisseurById(id);
	}
	@RequestMapping(path = "/statCpChansonById/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statTTSmartChansonById(@PathVariable("id") Integer id) {
		
		System.out.println(Object.class);
		return deezerRepo.statCpChansonById(id);
	}
	@RequestMapping(path = "/statCpCatById/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statTTSmartCatById(@PathVariable("id") Integer id) {
		
		System.out.println(Object.class);
		return deezerRepo.statTTCpCatById(id);
	}
	

	//statDateFourisseurByIdExcel
	//HistRevenuOrangeExcel
	@RequestMapping(path = "/StateCpPdfByid/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> StateCpPdfByid(@PathVariable("id") Integer id)
	{
	
		List<DeezerExcel>  deatils =  deezerRepo.statDateFourisseurByIdExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateDeezer.StateDateCpPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}
	
	//statCpChansonByIdExcel
	
	@RequestMapping(path = "/statCpChansonByIdExcel/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> statCpChansonByIdExcel(@PathVariable("id") Integer id)
	{
	
		List<DeezerExcel>  deatils =  deezerRepo.statCpChansonByIdExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateDeezer.StateChansonCpPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}
	/*@RequestMapping(path = "/statCpChansonByNom/by-userId/{nomcontent}", method = RequestMethod.GET)
	public List<Object[]> statTTSmartChansonByNom(@PathVariable("nomcontent") String nomcontent) {
		
		System.out.println(Object.class);
		return deezerRepo.statCpChansonByNom(nomcontent);
	}*/
	
	//statPaysDeezer
	
	
	@RequestMapping(path = "/statPaysDeezer", method = RequestMethod.GET)
	public List<Object[]> statPaysDeezer() {
		return deezerRepo.statPaysDeezer();
	}
	
	
	@RequestMapping(path = "/statPaysDeezer/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statPaysDeezer(@PathVariable("id") Integer id) {
		return deezerRepo.statPaysDeezer(id);
	}
	
	
	
	@RequestMapping(path = "/statPaysByIdExcel/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> statPaysByIdExcel(@PathVariable("id") Integer id)
	{
	
		List<DeezerExcel>  deatils =  deezerRepo.statPaysDeezerExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateDeezer.StatePaysPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}
	

	@GetMapping("/TotstatPaysDeezerPdf")
	public ResponseEntity<InputStreamResource> TotstatPaysDeezerPdf()
	{
		List<DeezerExcel>  deatils =  deezerRepo.statPaysDeezerExce();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateDeezer.StatePaysPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	@RequestMapping(path = "/deleteFileByDate/{date1}/{date2}/{file}", method = RequestMethod.DELETE)
	public void deleteByFichier(@PathVariable java.sql.Date date1, @PathVariable java.sql.Date date2,@PathVariable String file) {
		deezerRepo.deleteByDateFichier(date1,date2,file);
	}
	
	
	@RequestMapping(path = "/statDateDeezerLabel/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statDateByIdLabel(@PathVariable("id") Integer id) {
		return deezerRepo.statDateByIdLabel(id);
	}
	
	@RequestMapping(path = "/statArtisteDeezerLabel/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statArtisteByIdLabel(@PathVariable("id") Integer id) {
		return deezerRepo.statArtisteDeezerByIdLabel(id);
	}
	
	
	@RequestMapping(path = "/statChansonDeezerLabel/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statChansonByIdLabel(@PathVariable("id") Integer id) {
		return deezerRepo.statChansonByIdLabel(id);
	}
	
	
	@RequestMapping(path = "/topArtisteDeezer", method = RequestMethod.GET)
	public List<Object[]> topArtiste() {
		return deezerRepo.topArtisteDeezer();
	}
	
	
	@RequestMapping(path = "/topChansonDeezer", method = RequestMethod.GET)
	public List<Object[]> listChanson() {
		return deezerRepo.topChansonDeezer();
	}
	
	
	@RequestMapping(path = "/topDateDeezer", method = RequestMethod.GET)
	public List<Object[]> topDate() {
		return deezerRepo.topDateDeezer();
	}
	
	
	
	
	@RequestMapping(path = "/ChartsChansDeezer", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsChans() {
		return deezerRepo.ChartsChansDeezer();
	}
	
	
	@RequestMapping(path = "/ChartsPartsRevenuDeezer/by-userId/{id}", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsPartsRevenuById(@PathVariable("id") Integer id) {
		return deezerRepo.ChartsPartsRevenuDeezer(id);
	}
	
	
	
	@RequestMapping(path = "/ChartsDateDeezer", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsDate() {
		return deezerRepo.ChartsDateDeezer();
	}
}
