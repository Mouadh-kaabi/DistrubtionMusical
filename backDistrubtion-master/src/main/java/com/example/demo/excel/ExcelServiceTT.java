package com.example.demo.excel;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Exception.DateException;
import com.example.demo.Exception.nullException;
import com.example.demo.Interface.ContentTT;
import com.example.demo.entities.Artiste;
import com.example.demo.entities.ConfigTT;
import com.example.demo.entities.ContentProvider;
import com.example.demo.entities.Details;

import com.example.demo.entities.Users;
import com.example.demo.repository.ArtisteRepo;
import com.example.demo.repository.ContentProviderRepo;
import com.example.demo.repository.DetailRepo;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.itextpdf.text.pdf.hyphenation.TernaryTree.Iterator;


	
	
@Service
public class ExcelServiceTT {
	
	
	private static final Map<String, String> fileExtensionMap;

	static {
		fileExtensionMap = new HashMap<String, String>();
		
		// excel type
		fileExtensionMap.put("xls", "application/vnd.ms-excel");
		fileExtensionMap.put("csv", "application/vnd.ms-excel");

		fileExtensionMap.put("xlt", "application/vnd.ms-excel");
		fileExtensionMap.put("xla", "application/vnd.ms-excel");
		fileExtensionMap.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		fileExtensionMap.put("xltx", "application/vnd.openxmlformats-officedocument.spreadsheetml.template");
		fileExtensionMap.put("xlsm", "application/vnd.ms-excel.sheet.macroEnabled.12");
		fileExtensionMap.put("xltm", "application/vnd.ms-excel.template.macroEnabled.12");
		fileExtensionMap.put("xlam", "application/vnd.ms-excel.addin.macroEnabled.12");
		fileExtensionMap.put("xlsb", "application/vnd.ms-excel.sheet.binary.macroEnabled.12");
	}

	@Autowired
	DetailRepo detailRepository;
	
	@Autowired
	DetailRepo detailRepo ;
	@Autowired
	ContentProviderRepo contentProviderRepo ; 
	
	@Autowired
	ArtisteRepo artisteRepo;

	private static float quantite;
	private static float value;
	private static double TTC;
	private static double part_smart;
	private static double partcp ; 
	private static double valeur_cpEKLECTIC ; 
	private static double part_TT;
	private static double part_smart_globale;
	private static double part_sixD;
	private static double tax_telecom;
	private static double part_TTC;
	private static double HTVA;
	private static double part_artiste;
	private static double Totalnetderedevance;
	private static String nomColonne;
	private static int nbr_ecoute;
	private static float unit_price;
	private static int revenuG;
	private static java.util.Date datedate ; 
	
	
	private static int toneId ;
	private static double valeur_ttc;
	private static double valeur_tt;
	private static double valeurcp;
	private static double valeurTax;
	
	private static double  valeurttc;
	private static double valeur_ttc_eur;
	private static double  valeurHtva ;
	private static double valeuravecTVa;
	private static double partSMartHt;
	
	private String nom_chonson ;

	public void uploadExcelTT(MultipartFile file)
			throws ParseException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, DateException, nullException {
		try {
			List<Details> details = excelTTToDetails(file.getInputStream());
			detailRepository.saveAll(details);
			//detailRepo.saveAll(details);
			//contentProviderRepo.saveAll(details);
			//System.out.println(details);
			nomColonne = file.getOriginalFilename();
		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
	}

	public static boolean hasExcelFormat(MultipartFile file) {

		if (!fileExtensionMap.containsValue(file.getContentType())) {
			return false;
		}

		return true;
	}
	
	

	
	
	public List<Details> excelTTToDetails(InputStream is)
			throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, ParseException, nullException {

		Workbook workbook = WorkbookFactory.create(is);
		
		Sheet sheet = workbook.getSheetAt(0);
		java.util.Iterator<Row> rows = sheet.iterator();
		List<Details> tutorials = new ArrayList<Details>();
		//List<ContentProvider>contentProviders = new ArrayList<ContentProvider>();
		//List<details> tutorials = detailRepository.getDetailsByDate1andFile("TT");
		SimpleDateFormat formaterrr = new SimpleDateFormat("MMM yyyy");
		//List<details> deatilss = new ArrayList<details>();
		
		
		//List<details> deatilss = detailRepository.getDetailsByDate1andFile("TT");
		System.out.println(tutorials);
		/*-------parsing of date to ---------*/
		
		
		


     	

		//List<details> detailss = new ArrayList<>();
		

		
			int rowNumber = 0;
			while (rows.hasNext()) {
				
				Row currentRow = rows.next();

				// skip header and the unused columns
			if (rowNumber == 0 || rowNumber == 1
						) {
					rowNumber++;
					continue;
				}
				
				java.util.Iterator<Cell> cellsInRow = currentRow.iterator();
				/*if (rowNumber == 0) {
					rowNumber++;
					continue;
				}*/
				
				
			
				
				Details details = new Details();
				Artiste artisteTes = new Artiste();
				
				ConfigTT configtt = new ConfigTT();
				
			//	System.out.println("hethi hne htva + tax telcom"+configtt);
				
				List<ConfigTT>configTTs = new ArrayList<>();	
				System.out.println("hethi htva belhi hay "+configtt.getHtva());
					
				
				
				ContentProvider obj = new ContentProvider();
				System.out.println("test"+obj.getEmail());
				//ContentProvider contentProvider = new ContentProvider(rowNumber, nom_chonson);
				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();
					
					// details.setDate1(d1);
					// details.setDate2(d2);
					
					
					
					
					
					switch (cellIdx) {
					case 0:
						
						if (!(currentRow.getCell(0).getDateCellValue() == null)) {
							details.setDate1(new java.sql.Date(currentRow.getCell(0).getDateCellValue().getTime()));
							
						
						
	
							
						} else
							throw new nullException(
									"la date fin est doit etre non vide verifiez " + "la ligne " + (currentRow.getRowNum() + 1));
						
						
					
						
						
					
					case 1:
						
						
						
					/*	Optional<ContentProvider> contentProvider = contentProviderRepo.findById(details.getContentProvider().getId());
						if(contentProvider.isPresent())
						{
							
							details.setContentProvider(contentProvider.get());
						}*/
						
						
						/*if(!currentRow.getCell(1).getStringCellValue().isEmpty()){
			                details.setContentProvider((ContentProvider) currentRow.getCell(1));
			                //currentRow.getCell(4).getStringCellValue());
			                break ;
			            }
						
						contentProviderRepo.save()

			            else throw new nullException("le Content Provider est doit etre non vide verifiez " +
			                    "la ligne " + (currentRow.getRowNum()+1));*/
						
						
					/*List<ContentProvider> listStrings = new ArrayList<ContentProvider>();
					
					
						
						if(!currentRow.getCell(1).getStringCellValue().isEmpty()){
							
							List<ContentProvider> contentProviders = new ArrayList<>();
						//	details.setContentProvider(currentRow.getCell(5).getStringCellValue());
						//	details.setContentProviders((List<ContentProvider>) currentRow.getCell(1).getStringCellValue());
							contentProviderRepo.saveAll(contentProviders);*/
						//List<ContentProvider>contentProviders = new ArrayList<>();

						//details.setContentProviders((List<ContentProvider>) currentRow.getCell(1).getCellStyle());
						
						
						
						/////////////////////////
						/*if(!currentRow.getCell(1).getClass().){
			                details.setContentProvider(currentRow.getCell(1).getStringCellValue());
			                break ;
			            }
						
			            else throw new nullException("le Content Provider est doit etre non vide verifiez " +
			                    "la ligne " + (currentRow.getRowNum()+1));*/
						
						
						
						
						if(!currentRow.getCell(1).getStringCellValue().isEmpty()){
			                details.setContentProvider(currentRow.getCell(1).getStringCellValue());
			               // ContentProvider contentProvider = new ContentProvider();
							//contentProvider.setNom(currentRow.getCell(1).getStringCellValue());	
							
			                
			              // List<ContentProvider> contentProviders = new ArrayList<>();
			              // ContentProvider cont = new ContentProvider();
			               // contentProviders.add(cont.setNom(currentRow.getCell(1).getStringCellValue()));
			                //details.setContentProviders();
			                break ;
			            }

			            else throw new nullException("le Content Provider est doit etre non vide verifiez " +
			                    "la ligne " + (currentRow.getRowNum()+1));
					case 2:

						if (!(currentRow.getCell(2) == null)) {
							currentRow.getCell(2).setCellType(CellType.NUMERIC);
							toneId =  (int) ( currentRow.getCell(2).getNumericCellValue());
							//details.setGrossrevenu((double) quantite);
							details.setToneId((long) toneId);
							
						} else
							throw new nullException("le nombre d'ecoute est doit etre non vide verifiez " + "la ligne "
									+ (currentRow.getRowNum() + 1));
					case 4:
						if(!currentRow.getCell(4).getStringCellValue().isEmpty()){
			                details.setPackName(currentRow.getCell(4).getStringCellValue());
			                break ;
			            }

			            else throw new nullException("le Content Provider est doit etre non vide verifiez " +
			                    "la ligne " + (currentRow.getRowNum()+1));

					case 5:
						
						if(!currentRow.getCell(5).getStringCellValue().isEmpty()){
			                details.setSubscriberType(currentRow.getCell(5).getStringCellValue());
			                break ;
			            }

			            else throw new nullException("le Content Provider est doit etre non vide verifiez " +
			                    "la ligne " + (currentRow.getRowNum()+1));

					case 6:
						
						
						if(!currentRow.getCell(6).getStringCellValue().isEmpty()){
			                details.setCategory(currentRow.getCell(6).getStringCellValue());
			                break ;
			            }

			            else throw new nullException("le Content Provider est doit etre non vide verifiez " +
			                    "la ligne " + (currentRow.getRowNum()+1));
						
						

					case 7:
						
						if(!currentRow.getCell(7).getStringCellValue().isEmpty()){
			                details.setSubCategory(currentRow.getCell(7).getStringCellValue());
			            }

			            else throw new nullException("le Content Provider est doit etre non vide verifiez " +
			                    "la ligne " + (currentRow.getRowNum()+1));
						
						
					case 8:
						
						String nom_artiste;
						switch (currentCell.getStringCellValue()) {
						
		    				
		    			case "MASHARY AL AFASY":
		    				nom_artiste = "AL SHEIKH MASHARI AL AFASI";
		    				
		    				break;
		    				
		    			case "AL AFASY":
		    				nom_artiste = "AL SHEIKH MASHARI AL AFASI";
		    				
		    				break;
		    				
		    			case "Lotfi_Bouchnak":
		    				nom_artiste = "Lotfi Bouchnak";
		    				
		    				break;
		    				
		    				
		    			case "MORTADHA FTITI FT SANFARA":
		    				nom_artiste = "MORTADHA FTITI";
		    				
		    				break;
		    				
		    			case  "Mortadha Ftiti":
		    				nom_artiste = "MORTADHA FTITI";
		    			break;
		    				
		    				
		    			case "Saber Rebai":
							nom_artiste = "OGITREV FILMS";
							break;
		    				
							
							//SABER REBAI
							
		    			case "SABER REBAI":
							nom_artiste = "OGITREV FILMS";
							break;
							
							
		    				//MORTADHA_FTITI_FT_SANFARA
		    				//MORTADHA_FTITI_FT_SANFARA
		    			case "MORTADHA_FTITI_FT_SANFARA":
		    				nom_artiste = "MORTADHA FTITI";
		    				
		    				break;
		    				
		    				//MORTADHA FTITI FT G.G.A FTITI
		    				
		    			case "MORTADHA FTITI FT G.G.A FTITI":
		    				nom_artiste = "MORTADHA FTITI";
		    				
		    				break;
		    				
		    				//BALTI ET HAMOUDA
		    				
		    			case "BALTI ET HAMOUDA":
		    				nom_artiste = "BALTI";
		    				
		    				break;
		    				
		    			case  "DOUKI FT KAFON":
		    				nom_artiste = "DOUKI";
		    			break;
		    			
		    			case  "IN_S_FEAT KAFON":
		    				nom_artiste = "IN_S_";
		    			break;

						default:
							nom_artiste = currentCell.getStringCellValue();
							
							System.out.println("hetha ya3tik essm artiste"+nom_artiste);
							break;
						}
						if (!nom_artiste.isEmpty()) {
							details.setNamea(nom_artiste);
							
							
							System.out.println("hethi li bech ttttttiiiiiiiiiii"+artisteTes.getnArtistique());
							Set<Artiste> artistes = new HashSet();
							for(int i = 0 ; i <artistes.size();i++)
							{
							
							//	artisteRepo.flush(artisteTes.setnArtistique(nom_artiste));
								//artisteRepo.saveArtiste(artisteTes.setnArtistique(nom_artiste));
								break ;
							}
							
							
							//Artiste art = new Artiste();
							//art.setnArtistique(nom_artiste);
							//artisteRepo.save(art.setnArtistique(nom_artiste));
							System.out.println(nom_artiste);
							System.out.println(currentCell.getStringCellValue());
							//Artiste art = new Artiste();
							
							//Set<?> artistes = new TreeSet<>();
							//artistes.addAll(artistes)
							//((Users) artistes).setnArtistique(nom_artiste);
							//artistes.get(cellIdx).setnArtistique(nom_artiste);
							//artistes.add(art);
							//artistes.addAll(details.getNamea());
							
							//artisteRepo.save(nom_artiste);
							
							//Set<String> artistes = new TreeSet<>();
							//Artiste art = new Artiste();
						//	artistes.addAll(art.setnArtistique(currentRow.getCell(8).getStringCellValue()));
							
							
							break;
							
						}

						else
							throw new nullException("le nom artistique est doit etre non vide verifiez " + "la ligne "
									+ (currentRow.getRowNum() + 1));

					case 9:

						
						if(!currentRow.getCell(9).getStringCellValue().isEmpty()){
			                details.setAlbum(currentRow.getCell(9).getStringCellValue());
			            }

			            else throw new nullException("le Content Provider est doit etre non vide verifiez " +
			                    "la ligne " + (currentRow.getRowNum()+1));
						
						
						

					case 10:
						String content  ;
						switch (currentCell.getStringCellValue()) {
						
		    				
		    			case "Chedda_w_Tzoul":
		    				content = "Cheda W Tzoul";
		    				
		    				break;
		    				
		    		

						default:
							content = currentCell.getStringCellValue();
							break;
						}
						if (!content.isEmpty()) {
							details.setContent(content);
							System.out.println(content);
							System.out.println(currentCell.getStringCellValue());
							break;
						}

						else
							throw new nullException("le nom artistique est doit etre non vide verifiez " + "la ligne "
									+ (currentRow.getRowNum() + 1));

						
						
					/*	if(!currentRow.getCell(10).getStringCellValue().isEmpty()){
							details.setContent(currentRow.getCell(10).getStringCellValue());
							 String content;
							switch (currentRow.getCell(11).getStringCellValue()) {
							case "Chedda_w_Tzoul":
								content = "Cheda W Tzoul";
								break;
								
								
								
							
							}
			             
			            }

			            else throw new nullException("le Content Provider est doit etre non vide verifiez " +
			                    "la ligne " + (currentRow.getRowNum()+1));*/
						
						
						

					case 11:
						if(!currentRow.getCell(11).getStringCellValue().isEmpty()){
			                details.setType(currentRow.getCell(11).getStringCellValue());
			            }

			            else throw new nullException("le Content Provider est doit etre non vide verifiez " +
			                    "la ligne " + (currentRow.getRowNum()+1));
						
						
						 

					case 12:
						
						if (!(currentRow.getCell(12) == null)) {
							currentRow.getCell(12).setCellType(CellType.NUMERIC);
							value =  (float) ( currentRow.getCell(12).getNumericCellValue());
							//details.setGrossrevenu((double) quantite);
							details.setUniteprice((float) value);
							//((int) quantite) ;
							//details.set((double) quantite);
						} else
							throw new nullException("le nombre d'ecoute est doit etre non vide verifiez " + "la ligne "
									+ (currentRow.getRowNum() + 1));
						
						
						
						
					
						
						/*if (!(String.valueOf(currentCell.getNumericCellValue()).isEmpty())) {
							details.setUniteprice((float) currentCell.getNumericCellValue());
							value = (float) currentCell.getNumericCellValue();
							
						} else
							throw new nullException("le prix unitaire est doit etre non vide verifiez " + "la ligne "
									+ (currentRow.getRowNum() + 1));*/

					case 13:

						
						
						
						if(!currentRow.getCell(13).getStringCellValue().isEmpty()){
			                details.setLanguage(currentRow.getCell(13).getStringCellValue());
			            }

			            else throw new nullException("le Content Provider est doit etre non vide verifiez " +
			                    "la ligne " + (currentRow.getRowNum()+1));
						
						

					case 14:

						
						
						
						
						
						
						if(!currentRow.getCell(14).getStringCellValue().isEmpty()){
			                details.setChannel(currentRow.getCell(14).getStringCellValue());
			            }
			            else throw new nullException("la valeur de Upc est doit etre non vide verifiez " +
			                    "la ligne " + (currentRow.getRowNum()+1));
						
						
					case 15:

						
						if (!(currentRow.getCell(15) == null)) {
							currentRow.getCell(15).setCellType(CellType.NUMERIC);
						//	details.setQuantite(cellIdx);
						quantite = ((int) currentRow.getCell(15).getNumericCellValue());
						
							details.setQuantite((int) quantite) ;
							
						} else
							throw new nullException("le nombre d'ecoute est doit etre non vide verifiez " + "la ligne "
									+ (currentRow.getRowNum() + 1));
						
					/*	if(!currentRow.getCell(15).getStringCellValue().isEmpty()){
			                details.setQu(currentRow.getCell(15).getStringCellValue());
			            }
			            else throw new nullException("la valeur de Upc est doit etre non vide verifiez " +
			                    "la ligne " + (currentRow.getRowNum()+1));*/
						
					case 16: 
						
						if (!(currentRow.getCell(16) == null)){
							currentRow.getCell(16).setCellType(CellType.NUMERIC);
							valeur_ttc = ((double) currentRow.getCell(16).getNumericCellValue());
							//details.setGrossrevenu((double) quantite);
							details.setPart_smart_globale((double)valeur_ttc ) ;
							//details.set((double) quantite);
			            }
			            else throw new nullException("la valeur de Upc est doit etre non vide verifiez " +
			                    "la ligne " + (currentRow.getRowNum()+1));
						
				
						
					
						
						
						
						valeur_ttc = details.getPart_smart_globale();
						
						details.setTTC((Double) ((valeur_ttc * 0.35)));
						
					
					//	details.setPart_TT_ins( (valeur_ttc * 0.67));
						
						
						part_smart_globale = details.getTTC();
						
					
						
						details.setTax_telecom((part_smart_globale * 0.05) / 1.19);

						
						valeurTax = details.getTax_telecom();
						valeuravecTVa = details.setGrossrevenu((part_smart_globale/1.19)  *0.19);
						
						details.setTotalnetderedevance(part_smart_globale - (valeurTax+valeuravecTVa));
						Totalnetderedevance = details.getTotalnetderedevance();
						
						details.setParSmatHt(Totalnetderedevance);
						partSMartHt = details.getParSmatHt();
						
						
						
						if(details.getPays()==null)
						{
							details.setPays("Tunisia");
						}
						
						
						if(details.getPlateforme()==null)
						{
							details.setPlateforme("Tunisie Telecome");
						}
						
						
						Calendar cal1 = Calendar.getInstance();
		                cal1.setTime(currentRow.getCell(0).getDateCellValue());
		                cal1.set(Calendar.DAY_OF_MONTH, cal1.getActualMinimum(Calendar.DAY_OF_MONTH));
		                details.setDate1(new Date(cal1.getTime().getTime()));
						
						Calendar cal = Calendar.getInstance();
		                cal.setTime(currentRow.getCell(0).getDateCellValue());
		                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		                details.setDate2(new Date(cal.getTime().getTime()));
						
		                
		               
		            	
					/*	Double partCp = tu.getPart_cp();
						details.setPart_cp(part_smart*partCp);
						partcp=details.getPart_cp();*/
						
					
					
						/*	*/
						
						
						
						
					//	date.setYear(2022);
						//System.out.println("Icciiiiiiiiiiiiiiiiiiiiiiiiiiiii date"+date);
						
						/*details.setPart_TTC((double)(part_smart - tax_telecom));
						part_= part_smart - tax_telecom;
						System.out.println("part_TTC " + part_TTC);*/
						
						//details.setPart_TTC((double)(part_smart - tax_telecom));
					//	part_TTC = part_smart - tax_telecom;
					//	System.out.println("part_TTC " + part_TTC);
						

						//details.setHTVA((double) (part_TTC / 1.19));
					//	HTVA = part_TTC / 1.19;
						//System.out.println(HTVA);
						
						
		                
		              
		                
		              /*  for(int i=0; i<configTTs.size(); i++)
		                {
		                	//configTTs.getClass()[0];
		                	
		                    System.out.println("a3tini tableau de config"  );
		                    break ;
		                }*/
					
					
						
						String cp = 	details.getContentProvider();
						System.out.println("---------------------------------------> hethi "+cp);
						
						if(cp.equals("Qanawat")) {
							
							//  List<ConfigTT>configTTs = new ArrayList<>();
				              
							 // List<Float> namesList = configTTs.stream().map(p -> p.getHtva()).collect(Collectors.toCollection(ArrayList::new));
							//  System.out.println("hnnnnnnnnnnnnnneeeeeeeeeeeeHetha"+namesList);
				          /*  System.out.println("hhhhhhhhelooooooooooo list config"+configTTs.getClass().toString());*/
							System.out.println("Qanawat---->"+cp);
							
							valeur_ttc= details.getParSmatHt();
							//details.setTax_telecom(valeur_ttc*0.050);
							//valeurTax = details.getTax_telecom();
							
							
							//details.setTotalnetderedevance(valeur_ttc - valeurTax);
							//Totalnetderedevance = details.getTotalnetderedevance();
							details.setPart_cp(valeur_ttc*0.50);
							
							
							details.setPart_smart(valeur_ttc * 0.50);
							
							part_smart=details.getPart_smart();
							
							
							//details.setPart_cp(part_smart*0.70);
							//partcp=details.getPart_cp();
							
					
							//details.setHTVA(partcp/1.19);
							
							//valeurHtva=details.getHTVA();
							details.setPart_artiste(part_smart/2);
						}
						
						
						else if (cp.equals("EKLECTIC"))
						{
						//  List<ConfigTT>configTTs = new ArrayList<>();
				              
							 // List<Float> namesList = configTTs.stream().map(p -> p.getHtva()).collect(Collectors.toCollection(ArrayList::new));
							//  System.out.println("hnnnnnnnnnnnnnneeeeeeeeeeeeHetha"+namesList);
				          /*  System.out.println("hhhhhhhhelooooooooooo list config"+configTTs.getClass().toString());*/
							System.out.println("Qanawat---->"+cp);
							
							valeur_ttc= details.getParSmatHt();
							//details.setTax_telecom(valeur_ttc*0.050);
							//valeurTax = details.getTax_telecom();
							
							
							//details.setTotalnetderedevance(valeur_ttc - valeurTax);
							//Totalnetderedevance = details.getTotalnetderedevance();
							details.setPart_cp(valeur_ttc*0.70);
							
							
							details.setPart_smart(valeur_ttc * 0.30);
							
							part_smart=details.getPart_smart();
							
							
							//details.setPart_cp(part_smart*0.70);
							//partcp=details.getPart_cp();
							
					
							//details.setHTVA(partcp/1.19);
							
							//valeurHtva=details.getHTVA();
							details.setPart_artiste(part_smart/2);
						}
						
						else if (cp.equals("ARPU Plus"))
						{
						//  List<ConfigTT>configTTs = new ArrayList<>();
				              
							 // List<Float> namesList = configTTs.stream().map(p -> p.getHtva()).collect(Collectors.toCollection(ArrayList::new));
							//  System.out.println("hnnnnnnnnnnnnnneeeeeeeeeeeeHetha"+namesList);
				          /*  System.out.println("hhhhhhhhelooooooooooo list config"+configTTs.getClass().toString());*/
							//System.out.println("Qanawat---->"+cp);
							
							valeur_ttc= details.getParSmatHt();
							//details.setTax_telecom(valeur_ttc*0.050);
							//valeurTax = details.getTax_telecom();
							
							
							//details.setTotalnetderedevance(valeur_ttc - valeurTax);
							//Totalnetderedevance = details.getTotalnetderedevance();
							details.setPart_cp(valeur_ttc*0.70);
							
							
							details.setPart_smart(valeur_ttc * 0.30);
							
							part_smart=details.getPart_smart();
							
							
							//details.setPart_cp(part_smart*0.70);
							//partcp=details.getPart_cp();
							
					
							//details.setHTVA(partcp/1.19);
							
							//valeurHtva=details.getHTVA();
							details.setPart_artiste(part_smart/2);
						}
						
						
						else if (cp.equals("MusikBI"))
						{
						//  List<ConfigTT>configTTs = new ArrayList<>();
				              
							 // List<Float> namesList = configTTs.stream().map(p -> p.getHtva()).collect(Collectors.toCollection(ArrayList::new));
							//  System.out.println("hnnnnnnnnnnnnnneeeeeeeeeeeeHetha"+namesList);
				          /*  System.out.println("hhhhhhhhelooooooooooo list config"+configTTs.getClass().toString());*/
							//System.out.println("Qanawat---->"+cp);
							
							valeur_ttc= details.getParSmatHt();
							//details.setTax_telecom(valeur_ttc*0.050);
							//valeurTax = details.getTax_telecom();
							
							
							//details.setTotalnetderedevance(valeur_ttc - valeurTax);
							//Totalnetderedevance = details.getTotalnetderedevance();
							details.setPart_cp(valeur_ttc*0.50);
							
							
							details.setPart_smart(valeur_ttc * 0.50);
							
							part_smart=details.getPart_smart();
							
							
							//details.setPart_cp(part_smart*0.70);
							//partcp=details.getPart_cp();
							
					
							//details.setHTVA(partcp/1.19);
							
							//valeurHtva=details.getHTVA();
							details.setPart_artiste(part_smart/2);
						}
						
						
						else if (cp.equals("Digitalsound"))
						{
						//  List<ConfigTT>configTTs = new ArrayList<>();
				              
							 // List<Float> namesList = configTTs.stream().map(p -> p.getHtva()).collect(Collectors.toCollection(ArrayList::new));
							//  System.out.println("hnnnnnnnnnnnnnneeeeeeeeeeeeHetha"+namesList);
				          /*  System.out.println("hhhhhhhhelooooooooooo list config"+configTTs.getClass().toString());*/
							//System.out.println("Qanawat---->"+cp);
							
							valeur_ttc= details.getParSmatHt();
							//details.setTax_telecom(valeur_ttc*0.050);
							//valeurTax = details.getTax_telecom();
							
							
							//details.setTotalnetderedevance(valeur_ttc - valeurTax);
							//Totalnetderedevance = details.getTotalnetderedevance();
							details.setPart_cp(valeur_ttc*0.60);
							
							
							details.setPart_smart(valeur_ttc * 0.40);
							
							part_smart=details.getPart_smart();
							
							
							//details.setPart_cp(part_smart*0.70);
							//partcp=details.getPart_cp();
							
					
							//details.setHTVA(partcp/1.19);
							
							//valeurHtva=details.getHTVA();
							details.setPart_artiste(part_smart/2);
						}
						
						else if (cp.equals("CHROME"))
						{
						//  List<ConfigTT>configTTs = new ArrayList<>();
				              
							 // List<Float> namesList = configTTs.stream().map(p -> p.getHtva()).collect(Collectors.toCollection(ArrayList::new));
							//  System.out.println("hnnnnnnnnnnnnnneeeeeeeeeeeeHetha"+namesList);
				          /*  System.out.println("hhhhhhhhelooooooooooo list config"+configTTs.getClass().toString());*/
							//System.out.println("Qanawat---->"+cp);
							
							valeur_ttc= details.getParSmatHt();
							//details.setTax_telecom(valeur_ttc*0.050);
							//valeurTax = details.getTax_telecom();
							
							
							//details.setTotalnetderedevance(valeur_ttc - valeurTax);
							//Totalnetderedevance = details.getTotalnetderedevance();
							details.setPart_cp(valeur_ttc*0.50);
							
							
							details.setPart_smart(valeur_ttc * 0.50);
							
							part_smart=details.getPart_smart();
							
							
							//details.setPart_cp(part_smart*0.70);
							//partcp=details.getPart_cp();
							
					
							//details.setHTVA(partcp/1.19);
							
							//valeurHtva=details.getHTVA();
							details.setPart_artiste(part_smart/2);
						}
						
						
						else if (cp.equals("IVAS"))
						{
						//  List<ConfigTT>configTTs = new ArrayList<>();
				              
							 // List<Float> namesList = configTTs.stream().map(p -> p.getHtva()).collect(Collectors.toCollection(ArrayList::new));
							//  System.out.println("hnnnnnnnnnnnnnneeeeeeeeeeeeHetha"+namesList);
				          /*  System.out.println("hhhhhhhhelooooooooooo list config"+configTTs.getClass().toString());*/
							System.out.println("Qanawat---->"+cp);
							
							valeur_ttc= details.getParSmatHt();
							//details.setTax_telecom(valeur_ttc*0.050);
							//valeurTax = details.getTax_telecom();
							
							
							
							details.setPart_cp(valeur_ttc*0.50);
							
							
							details.setPart_smart(valeur_ttc * 0.50);
							
							part_smart=details.getPart_smart();
							
							
							//details.setPart_cp(part_smart*0.70);
							//partcp=details.getPart_cp();
							
					
							//details.setHTVA(partcp/1.19);
							
							//valeurHtva=details.getHTVA();
							details.setPart_artiste(part_smart/2);
						}
						
						else if(cp.equals("Smart"))
						{
							
							
							System.out.println("Qanawat---->"+cp);
							
							valeur_ttc= details.getTotalnetderedevance();
							//details.setTax_telecom(valeur_ttc*0.050);
							//valeurTax = details.getTax_telecom();
							
							
							
							details.setPart_cp(valeur_ttc*0.70);
							partcp = details.getPart_cp();
							
							details.setPart_smart(valeur_ttc * 0.30);
							
							part_smart=details.getPart_smart();
							
							
							//details.setPart_cp(part_smart*0.70);
							//partcp=details.getPart_cp();
							
					
							//details.setHTVA(partcp/1.19);
							
							//valeurHtva=details.getHTVA();
							details.setPart_artiste(partcp/2);
							/*System.out.println("Smart---->"+cp);
							
							
							Totalnetderedevance = details.getTotalnetderedevance();
							
							details.setPart_smart(Totalnetderedevance * 0.70);
							
							part_smart=details.getPart_smart();
							
							
							details.setPart_cp(Totalnetderedevance*0.30);
							partcp=details.getPart_cp();
							
							details.setHTVA(part_smart/1.19);
							valeurHtva=details.getHTVA();
							details.setPart_artiste(valeurHtva/2);*/
						
						}
						
						
						else {
							
							System.out.println("Qanawat---->"+cp);
							
							valeur_ttc= details.getTTC();
							details.setTax_telecom(valeur_ttc*0.050);
							valeurTax = details.getTax_telecom();
							
							
							details.setTotalnetderedevance(valeur_ttc - valeurTax);
							Totalnetderedevance = details.getTotalnetderedevance();
							
							details.setPart_smart(Totalnetderedevance * 0.35);
							
							part_smart=details.getPart_smart();
							
							
							details.setPart_cp(part_smart*0.70);
							partcp=details.getPart_cp();
							
						
							details.setHTVA(partcp/1.19);
							valeurHtva=details.getHTVA();
							details.setPart_artiste(valeurHtva/2);
							
						}
						
						
					
						
						
						details.setCdate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
						details.setFile("TT");
						
						details.setPlateforme("Tunisie telecom");
						
					}	
					String nomartiste = details.getNamea();
					
					if(nomartiste =="OGITREV FILMS" )
						
					{
						details.setContent("Awled El Ghoul");
					}
					
					//String nomarTesttiste = details.getNamea();
					//System.out.println("hhhhhhhhhhhhhhhhhheeeeeeeeethhhhhhhha"+nomarTesttiste);
					
					cellIdx++;
				}
				tutorials.add(details);
			
				//contentProviders.add(details);
				
				
					
				
				
				
				
			
		}
		workbook.close();
		
		return tutorials;
		//return detailss;
		
	}

	
	
}
