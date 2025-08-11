package com.example.demo.dao;

import static com.itextpdf.text.Font.BOLD;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.pdfExceptionNoDataFound;

import com.example.demo.entities.Artiste;

import com.example.demo.repository.ArtisteRepo;
import com.example.demo.repository.BelieveRepo;
import com.example.demo.repository.DeezerRepo;
import com.example.demo.repository.OrangeRepo;
import com.example.demo.repository.TunisieTelecomeRepo;
import com.example.demo.repository.UserRepo;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class pdfService {

	
	@Autowired
	ArtisteRepo userRepository;
	
	@Autowired
	BelieveRepo believeRepo ;
	
	@Autowired
	DeezerRepo deezerRepo ;
	
	@Autowired
	OrangeRepo orangeRepo ;
	
	@Autowired
	TunisieTelecomeRepo tunisieTelecomeRepo ;
	
	
	private static double sommeRevenu;
	
	public ByteArrayInputStream toPDF(Integer idUSer,Date datedebut,Date datefin,Double retenue)
			throws pdfExceptionNoDataFound,IOException, DocumentException, com.example.demo.Exception.pdfExceptionDateFormat,
			MalformedURLException {
		
		System.out.println(datedebut);
		System.out.println(datefin);
		System.out.println(datedebut.getYear() + 1900);
		Optional<Artiste> u = userRepository.findById(idUSer);
		
		List<Double> details = orangeRepo.rapportStatTotalUsersById(idUSer, datedebut, datefin);
		System.out.println(details);
		List<Double> detailsBelieve = deezerRepo.rapportStatTotalBelieveUsersById(idUSer, datedebut, datefin);
		
		System.out.println(detailsBelieve);
		List<Double> detailsTT = tunisieTelecomeRepo.rapportStatTotalBelieveUsersById(idUSer, datedebut, datefin);
		
		System.out.println(detailsBelieve);
		List<Double> detailsDeezer = believeRepo.rapportStatTotalBelieveUsersById(idUSer, datedebut, datefin);
		
		java.util.Date d = new java.util.Date();
		SimpleDateFormat formater = null;
		SimpleDateFormat formaterr = null;
		SimpleDateFormat formaterrr = null;
		formater = new SimpleDateFormat("yyyyMMdd_HHmmss");
		formaterr = new SimpleDateFormat("dd/MM/yyyy");

		formaterrr = new SimpleDateFormat("MMM yyyy");
		
		Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, BOLD, BaseColor.GRAY);
		Font catFont15B = new Font(Font.FontFamily.TIMES_ROMAN, 15, BOLD, BaseColor.BLACK);
		Font catFont17Bcour = new Font(Font.FontFamily.COURIER, 17, BOLD, BaseColor.BLACK);
		Font subFontPara13B = new Font(Font.FontFamily.TIMES_ROMAN, 13, BOLD, BaseColor.BLACK);
		Font subFontPara13N = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.BLACK);
		Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 11, BOLD);
		
		
		Document my_pdf_report = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfWriter.getInstance(my_pdf_report, out);
		my_pdf_report.open();
		
		Image imgBank = null;

		imgBank = Image.getInstance("");
		
		imgBank.setAbsolutePosition(200, 700);
		imgBank.scaleAbsolute(160, 150);
		
		// Dateeee
				DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
				String dt = df.format(new java.util.Date());
				Paragraph dateStr = new Paragraph(dt, catFont15B);
				// separator
				Paragraph separator1 = new Paragraph("_______________________________________________________________________________", subFontPara13N);

				Paragraph titre = new Paragraph("RAPPORT" + u.get().getPrenom() +" "+ u.get().getNom() + "_" + formater.format(d));
				Paragraph titreBank = new Paragraph("Rapport " + u.get().getNom() + " " + u.get().getPrenom(), catFont15B);
				dateStr.setAlignment(Element.ALIGN_RIGHT);
				titreBank.setAlignment(Element.ALIGN_RIGHT);
				separator1.setAlignment(Element.ALIGN_RIGHT);
				titre.setAlignment(Element.ALIGN_RIGHT);

				// espaceee
				Paragraph espace = new Paragraph(" ", subFont);
				espace.setAlignment(Element.ALIGN_LEFT);

		////////////////////////////Paageeeeeeeeeeeeeeeeee 2

				PdfPTable table6 = new PdfPTable(1); // Create 2 columns in table.

				// Create cells
				PdfPCell cell7 = new PdfPCell(new Paragraph("Rapport " + u.get().getPrenom() + " " + u.get().getNom(), catFont15B));
				cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell7.setFixedHeight(30);
				table6.addCell(cell7);

				PdfPTable table4 = new PdfPTable(2); // Create 2 columns in table.

				DecimalFormat decimal = new DecimalFormat("0.000");
				DecimalFormat decimal2 = new DecimalFormat("###.#");

				// Create cells
				PdfPCell cell1 = new PdfPCell(
						new Paragraph("Orange " + formaterrr.format(datedebut) + "-" + formaterrr.format(datefin), catFont15B));
				PdfPCell cell2 = new PdfPCell(new Paragraph(
						"Believe " + formaterrr.format(datedebut) + "-" + formaterrr.format(datefin), catFont15B));
				PdfPCell cell9 = new PdfPCell(
						new Paragraph("Deezer " + formaterrr.format(datedebut) + "-" + formaterrr.format(datefin), catFont15B));
				PdfPCell cell10 = new PdfPCell(new Paragraph("S-Total", catFont15B));
				PdfPCell cell11 = new PdfPCell(new Paragraph("Retenue à source " + decimal2.format(retenue) + "%", catFont15B));
			

				PdfPCell cell14 = new PdfPCell(new Paragraph("Total à Payer", catFont15B));

				if (!datedebut.after(datefin)) {
				//	if ((details.get(0) != 0) && (detailsBelieve.get(0) != 0 ) && (detailsDeezer.get(0) != 0 )) {

						PdfPCell cell15 = new PdfPCell(new Paragraph("" + details.get(0), catFont15B));
						PdfPCell cell16 = new PdfPCell(new Paragraph("" + detailsBelieve.get(0), catFont15B));
						PdfPCell cell17 = new PdfPCell(new Paragraph("" + detailsDeezer.get(0), catFont15B));

						PdfPCell cell18 = new PdfPCell(new Paragraph(
								"" + decimal.format(details.get(0) + detailsBelieve.get(0) + detailsDeezer.get(0)),
								catFont15B));
						sommeRevenu = details.get(0) + detailsBelieve.get(0) + detailsDeezer.get(0);
						PdfPCell cell19 = new PdfPCell(
								new Paragraph("" + decimal.format((sommeRevenu * retenue) / 100), catFont15B));
						PdfPCell cell20 = new PdfPCell(
								new Paragraph("" + decimal.format(sommeRevenu - sommeRevenu * (retenue / 100)), catFont15B));

						cell1.setBackgroundColor(new BaseColor(190, 209, 220));
						cell2.setBackgroundColor(new BaseColor(190, 209, 220));
						cell9.setBackgroundColor(new BaseColor(190, 209, 220));
						cell10.setBackgroundColor(new BaseColor(190, 209, 220));
						cell11.setBackgroundColor(new BaseColor(190, 209, 220));

						cell14.setBackgroundColor(new BaseColor(190, 209, 220));

						cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
				

						cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell20.setHorizontalAlignment(Element.ALIGN_CENTER);

						// Add cells in table
						if  (details.get(0) != 0.0) {
							table4.addCell(cell1);
							table4.addCell(cell15);
						}
						
						if  (detailsBelieve.get(0) != 0.0) {
							table4.addCell(cell2);
							table4.addCell(cell16);
						}
						System.out.println(detailsDeezer.get(0));
						if  (detailsDeezer.get(0) != 0.0) {
							table4.addCell(cell9);
							table4.addCell(cell17);
						}
						
						table4.addCell(cell10);
						table4.addCell(cell18);

						table4.addCell(cell11);
						table4.addCell(cell19);

						table4.addCell(cell14);
						table4.addCell(cell20);

						PdfPTable table5 = new PdfPTable(3);

						PdfPCell cell4 = new PdfPCell(new Paragraph("Smart Technology"));
						PdfPCell cell5 = new PdfPCell(
								new Paragraph("L'artiste :" + u.get().getNom() + " " + u.get().getPrenom()));
						PdfPCell cell6 = new PdfPCell(new Paragraph("Reçu Le :" + formaterr.format(d)));

						cell4.setFixedHeight(150);
						cell5.setFixedHeight(150);
						cell6.setFixedHeight(150);
						cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table5.addCell(cell6);
						table5.addCell(cell4);
						table5.addCell(cell5);

						PdfPTable table7 = new PdfPTable(1);

						PdfPCell cell8 = new PdfPCell();
						cell4.setFixedHeight(200);
						Paragraph p = new Paragraph("Smart Technology", catFont15B);
						Paragraph pp = new Paragraph("7 Impasse n°3 Azzouz Rebaï El Manar 2 – Tunis – Tunisia");
						Paragraph ppp = new Paragraph(" TEL : +216 71 231 310 / 312 FAX : +216 71 231 035  Mobile: +216 98 74 74 70 contact@smartechnology.com.tn \n");

						p.setAlignment(Element.ALIGN_CENTER);
						pp.setAlignment(Element.ALIGN_CENTER);
						ppp.setAlignment(Element.ALIGN_CENTER);

						cell8.addElement(p);
						cell8.addElement(pp);
						cell8.addElement(ppp);

						table7.addCell(cell8);

						my_pdf_report.add(espace);
						my_pdf_report.add(espace);

						my_pdf_report.add(espace);

						my_pdf_report.add(espace);

						my_pdf_report.add(imgBank);
						my_pdf_report.add(espace);
						my_pdf_report.add(espace);
						my_pdf_report.add(table6);

						my_pdf_report.add(espace);

						my_pdf_report.add(espace);

						my_pdf_report.add(espace);

						my_pdf_report.add(table4);

						my_pdf_report.add(espace);

						my_pdf_report.add(espace);

						my_pdf_report.add(espace);

						my_pdf_report.add(table5);
						my_pdf_report.add(espace);
						my_pdf_report.add(espace);
						my_pdf_report.add(espace);
						my_pdf_report.add(espace);
						my_pdf_report.add(espace);

						my_pdf_report.add(espace);
						my_pdf_report.add(espace);
						my_pdf_report.add(espace);
						my_pdf_report.add(espace);

						my_pdf_report.add(table7);

						my_pdf_report.close();
						/* file.close(); */

						return new ByteArrayInputStream(out.toByteArray());
					} else
						throw new pdfExceptionNoDataFound(u.get().getNom() + " " + u.get().getPrenom()
								+ "n'a aucun revenue dans cette date !! verfier la date que vous avez choisir !");

		
	
	}
	
	
	


}
