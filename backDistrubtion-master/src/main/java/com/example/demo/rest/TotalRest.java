package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Details;
import com.example.demo.repository.DetailRepo;

@CrossOrigin(origins = "http://192.168.1.111:4200")
@RestController
@RequestMapping("/total")
public class TotalRest {

	@Autowired
	
	DetailRepo TotalStatRepository ;
	
	@RequestMapping(path = "/totalstatDate", method = RequestMethod.GET)
	public List<Object[]> statDate() {
	
		
		
		
		return TotalStatRepository.TotStatDate();
	}
	
	
	@RequestMapping(path = "/totalstatChanson", method = RequestMethod.GET)
	public List<Object[]> statChanson() {
		return TotalStatRepository.TotStatChanson();
	}
	
	@RequestMapping(path = "/totalstatArtiste", method = RequestMethod.GET)
	public List<Object[]> statArtiste() {
		return TotalStatRepository.TotStatArtiste();
	}
	
	
	@RequestMapping(path = "/totalstatcategory", method = RequestMethod.GET)
	public List<Object[]> statcategory() {
		return TotalStatRepository.TotStatcategory();
	}
	
	@RequestMapping(path = "/ChartsAdvancedPie", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsAdvancedPie() {
		return TotalStatRepository.ChartsAdvancedPie();
	}
	
	
	//ChartsAdvancedPieMulti
	
	@RequestMapping(path = "/ChartsAdvancedPieMulti", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsAdvancedPieMulti() {
		return TotalStatRepository.ChartsAdvancedPieMulti();
	}
	
	//ChartsAdvancedPie
	
	/*public List<Details>getChartAnne()
	{
		List<Details>details = new ArrayList<>();
		for(int i =0 ; i< details.size();i++)
		{
			details.get(i).getDate1();
			
			details.get(i).getPart_smart();
		}
	}*/
	
	
	@RequestMapping(path = "/ChartsAdvancedPie/by-userId/{id}", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsAdvancedPieById(@PathVariable("id") Integer id) {
		return TotalStatRepository.ChartsAdvancedPieById(id);
	}
	
	@RequestMapping(path = "/ChartsAdvancedPieMulti/by-userId/{id}", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsAdvancedPieMultiById(@PathVariable("id") Integer id) {
		return TotalStatRepository.ChartsAdvancedPieMultiById(id);
	}
	
	
	@RequestMapping(path = "/totalstatArtiste/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statArtisteById(@PathVariable("id") Integer id) {
		return TotalStatRepository.TotStatArtisteById(id);
	}
	
	
	@RequestMapping(path = "/totalstatDate/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statDateById(@PathVariable("id") Integer id) {
		return TotalStatRepository.TotStatDateById(id);
	}
	
	@RequestMapping(path = "/statPaysTotal/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statAbonnementPays(@PathVariable("id") Integer id) {
		return TotalStatRepository.statPaysTotalByid(id);
	}
	
	@RequestMapping(path = "/totalstatChanson/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statChansonById(@PathVariable("id") Integer id) {
		return TotalStatRepository.TotStatChansonById(id);
	}
	
	
	@RequestMapping(path = "/totalstatCategorie/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statCategorieById(@PathVariable("id") Integer id) {
		return TotalStatRepository.TotStatCategoryById(id);
	}
	
	
	@RequestMapping(path = "/ChartsDate", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsDate() {
		return TotalStatRepository.ChartsDate();
	}
	
	//ChartsDateOrange
	
	@RequestMapping(path = "/ChartsDateOrange", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsDateOrange() {
		return TotalStatRepository.ChartsDateOrange();
	}
	
	//ChartsDateTT
	
	@RequestMapping(path = "/ChartsDateTT", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsDateTT() {
		return TotalStatRepository.ChartsDateTT();
	}
	
	//ChartsDateDeezer
	@RequestMapping(path = "/ChartsDateDeezer", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsDateDeezer() {
		return TotalStatRepository.ChartsDateDeezer();
	}
	
	@RequestMapping(path = "/ChartsDateBelieve", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsDateBeleive() {
		return TotalStatRepository.ChartsDateBeleive();
	}
	
	@RequestMapping(path = "/ChartsDate/by-userId/{id}", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsDateById(@PathVariable("id") Integer id) {
		return TotalStatRepository.ChartsDateById(id);
	}
	
	//ChartsChansonById
	@RequestMapping(path = "/Chartschanson/by-userId/{id}", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsChansonById(@PathVariable("id") Integer id) {
		return TotalStatRepository.ChartsChansonById(id);
	}
	
	@RequestMapping(path = "/ChartschansonBelieve/by-userId/{id}", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsChansonBelieveById(@PathVariable("id") Integer id) {
		return TotalStatRepository.ChartsChansonBelieveById(id);
	}
	
	
	@RequestMapping(path = "/ChartschansonDeezer/by-userId/{id}", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsChansonDeezerById(@PathVariable("id") Integer id) {
		return TotalStatRepository.ChartsChansonDeezerById(id);
	}
	
	@RequestMapping(path = "/ChartschansonTT/by-userId/{id}", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsChansonTTById(@PathVariable("id") Integer id) {
		return TotalStatRepository.ChartsChansonTTById(id);
	}
	@RequestMapping(path = "/ChartschansonOrange/by-userId/{id}", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsChansonOrnageById(@PathVariable("id") Integer id) {
		return TotalStatRepository.ChartsChansonOrangeById(id);
	}
	
	@RequestMapping(path = "/ChartsChans", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsChans() {
		return TotalStatRepository.ChartsChans();
	}
	
	
	@RequestMapping(path = "/ChartsChansOrange", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsChansOrange() {
		return TotalStatRepository.ChartsChansOrange();
	}
	
	
	@RequestMapping(path = "/ChartsChansTT", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsChansTT() {
		return TotalStatRepository.ChartsChansTT();
	}
	
	
	@RequestMapping(path = "/ChartsChansDeezer", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsChansSDeezer() {
		return TotalStatRepository.ChartsChansSDeezer();
	}
	
	
	@RequestMapping(path = "/ChartsChansBelieve", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsChansBeleive() {
		return TotalStatRepository.ChartsChansBeleive();
	}
	
	
	
	@RequestMapping(path = "/topChanson", method = RequestMethod.GET)
	public List<Object[]> listChanson() {
		return TotalStatRepository.topChanson();
	}
	
	
	
	/*@RequestMapping(path = "/ChartsDate/by-userId/{id}", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsDateById(@PathVariable("id") Integer id) {
		return ChartsRepository.ChartsDateById(id);
	}*/

	@RequestMapping(path = "/ChartsArts", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsArts() {
		return TotalStatRepository.chartsArts();
	}
	
	
	@RequestMapping(path = "/HistRevenu", method = RequestMethod.GET)
	public List<Object[]> statRevenu() {
		return TotalStatRepository.statRevenuTotal();
	}

	
	@RequestMapping(path = "/paiementParMois/{namea}/{date1}/{date2}", method = RequestMethod.PUT)
	public void paiementParMois(@PathVariable String namea, @PathVariable java.sql.Date date1,
			@PathVariable java.sql.Date date2) {
		System.out.println(namea + ' ' + date1 + ' ' + date2);
		TotalStatRepository.paiementParMois(namea, date1, date2);
	}
	
	
	@RequestMapping(path = "/paiementParMoisHist/{namea}/{date1}/{date2}", method = RequestMethod.PUT)
	public void paiementParMoisHist(@PathVariable String namea, @PathVariable java.sql.Date date1,
			@PathVariable java.sql.Date date2) {
		System.out.println(namea + ' ' + date1 + ' ' + date2);
		TotalStatRepository.paiementParMoisHist(namea, date1, date2);
	}
	
	
	@RequestMapping(path = "/compenseParMois/{namea}/{date1}/{date2}", method = RequestMethod.PUT)
	public void compenseParMois(@PathVariable String namea, @PathVariable java.sql.Date date1,
			@PathVariable java.sql.Date date2) {
		System.out.println(namea + ' ' + date1 + ' ' + date2);
		TotalStatRepository.compenseParMois(namea, date1, date2);
	}

	
	
	@RequestMapping(path = "/ChartsPartsRevenu/by-userId/{id}", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsPartsRevenuById(@PathVariable("id") Integer id) {
		return TotalStatRepository.ChartsPartsRevenu(id);
	}
	
	
	@RequestMapping(path = "/ChartsAdvancedPieCp/by-userId/{id}", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsAdvancedPieByIdContent(@PathVariable("id") Integer id) {
		return TotalStatRepository.ChartsAdvancedPieByIdContent(id);
	}
	
	
	@RequestMapping(path = "/ChartschansonByCp/by-userId/{id}", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsChansonByIdCp(@PathVariable("id") Integer id) {
		return TotalStatRepository.ChartsChansonByIdCp(id);
	}
	
	

	@RequestMapping(path = "/statDateLabelCp/by-userId/{id}", method = RequestMethod.GET)
	public List<Map<String, Object>> statDateLabelByIdCp(@PathVariable("id") Integer id) {
		
		//Optional<Content> doctor = doctorRepository.findById(idDoctor);
		return TotalStatRepository.statDateLabelByIdCp(id);
	}
	
	//findAllProduitTypes
	
	
	@GetMapping("/alTest/{id}")
	public List<Object[]> getAll(@PathVariable("id") Integer id)
	{
		return TotalStatRepository.findAllProduitTypes(id);
	}
	
	@GetMapping("/alTestOrange/{id}")
	public List<Object[]> getAllOrangeCp(@PathVariable("id") Integer id)
	{
		return TotalStatRepository.findAllProduitTypesOrange(id);
	}
	
	@GetMapping("/alTesttt/{id}")
	public List<Object[]> getAllTTCp(@PathVariable("id") Integer id)
	{
		return TotalStatRepository.findAllProduitTypesTT(id);
	}
	
	@GetMapping("/alTestDeezer/{id}")
	public List<Object[]> getAllTTDeezer(@PathVariable("id") Integer id)
	{
		return TotalStatRepository.findAllProduitTypesDeezer(id);
	}

	
	@GetMapping("/alTestBelieve/{id}")
	public List<Object[]> getAllBELEIEVE(@PathVariable("id") Integer id)
	{
		return TotalStatRepository.findAllProduitTypesBeleieve(id);
	}
	
	@RequestMapping(path = "/statDateLabel/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statDateLabelById(@PathVariable("id") Integer id) {
		
		//Optional<Content> doctor = doctorRepository.findById(idDoctor);
		return TotalStatRepository.statDateLabelById(id);
	}
	
	
	
	@RequestMapping(path = "/statDateLabelChanson/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statDateLabelChansonById(@PathVariable("id") Integer id) {
		
		//Optional<Content> doctor = doctorRepository.findById(idDoctor);
		return TotalStatRepository.TotStatChansonCpById(id);
	}
	
	
	@RequestMapping(path = "/statDateLabelChansonOrange/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statDateLabelChansonByIdOrange(@PathVariable("id") Integer id) {
		
		//Optional<Content> doctor = doctorRepository.findById(idDoctor);
		return TotalStatRepository.TotStatChansonCpByIdOrange(id);
	}
	
	
	@RequestMapping(path = "/statDateLabelChansonTT/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statDateLabelChansonByIdTT(@PathVariable("id") Integer id) {
		
		//Optional<Content> doctor = doctorRepository.findById(idDoctor);
		return TotalStatRepository.TotStatChansonCpByIdTT(id);
	}
	
	@RequestMapping(path = "/statDateLabelChansonDeezer/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statDateLabelChansonByIdDeezer(@PathVariable("id") Integer id) {
		
		//Optional<Content> doctor = doctorRepository.findById(idDoctor);
		return TotalStatRepository.TotStatChansonCpByIdDeezer(id);
	}
	
	@RequestMapping(path = "/statDateLabelChansonBelieve/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statDateLabelChansonByIdBeleieve(@PathVariable("id") Integer id) {
		
		//Optional<Content> doctor = doctorRepository.findById(idDoctor);
		return TotalStatRepository.TotStatChansonCpByIdBeleiev(id);
	}
	
	
	@RequestMapping(path = "/totalstatCategorieCp/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statCategorieCpById(@PathVariable("id") Integer id) {
		return TotalStatRepository.TotStatCategoryByIdCp(id);
	}
	
	@RequestMapping(path = "/totalstatCategorieCpPOrange/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statCategorieCpByIdOrange(@PathVariable("id") Integer id) {
		return TotalStatRepository.TotStatCategoryByIdCpOrange(id);
	}
	@RequestMapping(path = "/totalstatCategorieCpTT/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statCategorieCpByIdTT(@PathVariable("id") Integer id) {
		return TotalStatRepository.TotStatCategoryByIdCpTT(id);
	}
	@RequestMapping(path = "/totalstatPaysCpDeezr/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statCategorieCpByIdDeezr(@PathVariable("id") Integer id) {
		return TotalStatRepository.TotStatCategoryByIdCpDeezer(id);
	}
	@RequestMapping(path = "/totalstatPayspBelieve/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statCategorieCpByIdBelieve(@PathVariable("id") Integer id) {
		return TotalStatRepository.TotStatCategoryByIdCpBelieve(id);
	}
	
	
	@GetMapping("/allDetails")
	public List<Details>getAll()
	{
		return TotalStatRepository.findAll();
	}
	
	
	
	@GetMapping("/allNArtistique/{namea}")
	
	public List<Details> getAllnArtistique(@PathVariable("namea") String namea)
	{
		return TotalStatRepository.findByNamea(namea);
	}
	
	
}
