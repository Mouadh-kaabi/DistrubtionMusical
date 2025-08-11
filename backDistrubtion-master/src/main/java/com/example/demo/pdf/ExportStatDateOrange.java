package com.example.demo.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.example.demo.Interface.OrangeExcel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static com.itextpdf.text.Font.BOLD;
@Service

public class ExportStatDateOrange {

	public static ByteArrayInputStream StateDatePdfReport(List<OrangeExcel> details)
	{
		
		Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, BOLD, BaseColor.GRAY);
		Font catFont15B = new Font(Font.FontFamily.TIMES_ROMAN, 15, BOLD, BaseColor.RED);
		Font catFont17Bcour = new Font(Font.FontFamily.COURIER, 17, BOLD, BaseColor.RED);
		Font subFontPara13B = new Font(Font.FontFamily.TIMES_ROMAN, 13, BOLD, BaseColor.BLACK);
		Font subFontPara13N = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.BLACK);
		Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 11, BOLD);

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, out);
			document.open();
			//add Text to pdf file
			com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER,18,BaseColor.GREEN);
			Paragraph para = new Paragraph("Statistiques total par mois",font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(9);
			
			//make column titre 
			Stream.of("Date Début","Date fin","Nombre d'ecoute","Revenu global Op","Tax telecome","Total net de redavance","Revenu Global Smart","HTva","Part Artiste").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(OrangeExcel det : details)
			{
				//date1
				PdfPCell dateCell = new PdfPCell (new Phrase (String.valueOf(det.getDate1())));
				dateCell.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell);
				
				//Date fin 
				PdfPCell date2Cell = new PdfPCell( new Phrase (String.valueOf(det.getDate2())));
				date2Cell.setPaddingLeft(1);
				date2Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				date2Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(date2Cell);
				
				
				//Nombre d'ecout
				PdfPCell nmbecout = new PdfPCell(new Phrase(String.valueOf( det.getQuantite())));
				nmbecout.setPaddingLeft(2);
				nmbecout.setVerticalAlignment(Element.ALIGN_MIDDLE);
				nmbecout.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(nmbecout);
				
				
				//revenu global
				PdfPCell revnuglobal = new PdfPCell(new Phrase (String.valueOf(det.getTtc())));
				revnuglobal.setPaddingLeft(2);
				revnuglobal.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revnuglobal.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revnuglobal);
				
				//tax telecome
				PdfPCell taxTel = new PdfPCell(new Phrase(String.valueOf(det.getTax_telecom())));
				taxTel.setPaddingLeft(3);
				taxTel.setVerticalAlignment(Element.ALIGN_MIDDLE);
				taxTel.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(taxTel);
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getPart_TTC())));
				partttc.setPaddingLeft(3);
				partttc.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partttc.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partttc);
				
				
				//revenu smart
				PdfPCell revenusmart = new PdfPCell(new Phrase (String.valueOf(det.getPart_smart())));
				revenusmart.setPaddingLeft(3);
				revenusmart.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revenusmart.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revenusmart);
				
				
				
				
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_artiste())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}
	
	
	public static ByteArrayInputStream StateDatePdfReportBYid(List<OrangeExcel> details)
	{
		
		Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, BOLD, BaseColor.GRAY);
		Font catFont15B = new Font(Font.FontFamily.TIMES_ROMAN, 15, BOLD, BaseColor.RED);
		Font catFont17Bcour = new Font(Font.FontFamily.COURIER, 17, BOLD, BaseColor.RED);
		Font subFontPara13B = new Font(Font.FontFamily.TIMES_ROMAN, 13, BOLD, BaseColor.BLACK);
		Font subFontPara13N = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.BLACK);
		Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 11, BOLD);

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, out);
			document.open();
			//add Text to pdf file
			com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER,18,BaseColor.GREEN);
			Paragraph para = new Paragraph("Statistiques total par mois",font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(8);
			
			//make column titre 
			Stream.of("Date Début","Date fin","Nombre d'ecoute","Tax telecome","Total net de redavance","Revenu Global Smart","HTva","Part Artiste").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(OrangeExcel det : details)
			{
				//date1
				PdfPCell dateCell = new PdfPCell (new Phrase (String.valueOf(det.getDate1())));
				dateCell.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell);
				
				//Date fin 
				PdfPCell date2Cell = new PdfPCell( new Phrase (String.valueOf(det.getDate2())));
				date2Cell.setPaddingLeft(1);
				date2Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				date2Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(date2Cell);
				
				
				//Nombre d'ecout
				PdfPCell nmbecout = new PdfPCell(new Phrase(String.valueOf( det.getQuantite())));
				nmbecout.setPaddingLeft(2);
				nmbecout.setVerticalAlignment(Element.ALIGN_MIDDLE);
				nmbecout.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(nmbecout);
				
				
				//revenu global
			/*	PdfPCell revnuglobal = new PdfPCell(new Phrase (String.valueOf(det.getTtc())));
				revnuglobal.setPaddingLeft(2);
				revnuglobal.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revnuglobal.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revnuglobal);*/
				
				//tax telecome
				PdfPCell taxTel = new PdfPCell(new Phrase(String.valueOf(det.getTax_telecom())));
				taxTel.setPaddingLeft(3);
				taxTel.setVerticalAlignment(Element.ALIGN_MIDDLE);
				taxTel.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(taxTel);
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getPart_TTC())));
				partttc.setPaddingLeft(3);
				partttc.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partttc.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partttc);
				
				
				//revenu smart
				PdfPCell revenusmart = new PdfPCell(new Phrase (String.valueOf(det.getPart_smart())));
				revenusmart.setPaddingLeft(3);
				revenusmart.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revenusmart.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revenusmart);
				
				
				
				
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_artiste())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}

	
	
	
	public static ByteArrayInputStream StateArtistePdfReport(List<OrangeExcel> details)
	{
		
		Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, BOLD, BaseColor.GRAY);
		Font catFont15B = new Font(Font.FontFamily.TIMES_ROMAN, 15, BOLD, BaseColor.RED);
		Font catFont17Bcour = new Font(Font.FontFamily.COURIER, 17, BOLD, BaseColor.RED);
		Font subFontPara13B = new Font(Font.FontFamily.TIMES_ROMAN, 13, BOLD, BaseColor.BLACK);
		Font subFontPara13N = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.BLACK);
		Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 11, BOLD);

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, out);
			document.open();
			//add Text to pdf file
			com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER,18,BaseColor.GREEN);
			Paragraph para = new Paragraph("Statistiques total par mois",font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(10);
			
			//make column titre 
			Stream.of("Date","Nom Artiste","Nom CHanson","Nombre d'ecoute","Revenu global Op","Tax telecome","Total net de redavance","Revenu Global Smart","HTva","Part Artiste").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.RED);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(OrangeExcel det : details)
			{
				
				//date1
				PdfPCell dateCell3 = new PdfPCell (new Phrase (String.valueOf(det.getDate1())));
				dateCell3.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell3.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell3.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell3);
				
				//date1
				PdfPCell dateCell = new PdfPCell (new Phrase (String.valueOf(det.getNamea())));
				dateCell.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell);
				
				
				//date1
				PdfPCell dateCell4 = new PdfPCell (new Phrase (String.valueOf(det.getContent())));
				dateCell4.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell4.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell4.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell4);
				
				//Nombre d'ecout
				PdfPCell nmbecout = new PdfPCell(new Phrase(String.valueOf( det.getQuantite())));
				nmbecout.setPaddingLeft(2);
				nmbecout.setVerticalAlignment(Element.ALIGN_MIDDLE);
				nmbecout.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(nmbecout);
				
				
				//revenu global
				PdfPCell revnuglobal = new PdfPCell(new Phrase (String.valueOf(det.getTtc())));
				revnuglobal.setPaddingLeft(2);
				revnuglobal.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revnuglobal.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revnuglobal);
				
				//tax telecome
				PdfPCell taxTel = new PdfPCell(new Phrase(String.valueOf(det.getTax_telecom())));
				taxTel.setPaddingLeft(3);
				taxTel.setVerticalAlignment(Element.ALIGN_MIDDLE);
				taxTel.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(taxTel);
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getPart_TTC())));
				partttc.setPaddingLeft(3);
				partttc.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partttc.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partttc);
				
				
				//revenu smart
				PdfPCell revenusmart = new PdfPCell(new Phrase (String.valueOf(det.getPart_smart())));
				revenusmart.setPaddingLeft(3);
				revenusmart.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revenusmart.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revenusmart);
				
				
				
				
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_artiste())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}
	
	
	
	public static ByteArrayInputStream StateArtistePdfReportById(List<OrangeExcel> details)
	{
		
		Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, BOLD, BaseColor.GRAY);
		Font catFont15B = new Font(Font.FontFamily.TIMES_ROMAN, 15, BOLD, BaseColor.RED);
		Font catFont17Bcour = new Font(Font.FontFamily.COURIER, 17, BOLD, BaseColor.RED);
		Font subFontPara13B = new Font(Font.FontFamily.TIMES_ROMAN, 13, BOLD, BaseColor.BLACK);
		Font subFontPara13N = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.BLACK);
		Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 11, BOLD);

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, out);
			document.open();
			//add Text to pdf file
			com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER,18,BaseColor.GREEN);
			Paragraph para = new Paragraph("Statistiques total par mois",font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(7);
			
			//make column titre 
			Stream.of("Nom Artiste","Nombre d'ecoute","Tax telecome","Total net de redavance","Revenu Global Smart","HTva","Part Artiste").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(OrangeExcel det : details)
			{
				//date1
				PdfPCell dateCell = new PdfPCell (new Phrase (String.valueOf(det.getNamea())));
				dateCell.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell);
				
				
				
				
				//Nombre d'ecout
				PdfPCell nmbecout = new PdfPCell(new Phrase(String.valueOf( det.getQuantite())));
				nmbecout.setPaddingLeft(2);
				nmbecout.setVerticalAlignment(Element.ALIGN_MIDDLE);
				nmbecout.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(nmbecout);
				
				
				//revenu global
				/*PdfPCell revnuglobal = new PdfPCell(new Phrase (String.valueOf(det.getTtc())));
				revnuglobal.setPaddingLeft(2);
				revnuglobal.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revnuglobal.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revnuglobal);*/
				
				//tax telecome
				PdfPCell taxTel = new PdfPCell(new Phrase(String.valueOf(det.getTax_telecom())));
				taxTel.setPaddingLeft(3);
				taxTel.setVerticalAlignment(Element.ALIGN_MIDDLE);
				taxTel.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(taxTel);
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getPart_TTC())));
				partttc.setPaddingLeft(3);
				partttc.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partttc.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partttc);
				
				
				//revenu smart
				PdfPCell revenusmart = new PdfPCell(new Phrase (String.valueOf(det.getPart_smart())));
				revenusmart.setPaddingLeft(3);
				revenusmart.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revenusmart.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revenusmart);
				
				
				
				
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_artiste())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}
	
	public static ByteArrayInputStream StateChansonPdfReport(List<OrangeExcel> details)
	{
		
		Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, BOLD, BaseColor.GRAY);
		Font catFont15B = new Font(Font.FontFamily.TIMES_ROMAN, 15, BOLD, BaseColor.RED);
		Font catFont17Bcour = new Font(Font.FontFamily.COURIER, 17, BOLD, BaseColor.RED);
		Font subFontPara13B = new Font(Font.FontFamily.TIMES_ROMAN, 13, BOLD, BaseColor.BLACK);
		Font subFontPara13N = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.BLACK);
		Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 11, BOLD);

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, out);
			document.open();
			//add Text to pdf file
			com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER,18,BaseColor.GREEN);
			Paragraph para = new Paragraph("Statistiques total par mois",font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(9);
			
			//make column titre 
			Stream.of("Nom Chanson","Nom Artiste","Nombre d'ecoute","Revenu global Op","Tax telecome","Total net de redavance","Revenu Global Smart","HTva","Part Artiste").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(OrangeExcel det : details)
			{
				
				PdfPCell dateCell1 = new PdfPCell (new Phrase (String.valueOf(det.getContent())));
				dateCell1.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell1.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell1.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell1);
				//date1
				PdfPCell dateCell = new PdfPCell (new Phrase (String.valueOf(det.getNamea())));
				dateCell.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell);
				
				
				
				
				//Nombre d'ecout
				PdfPCell nmbecout = new PdfPCell(new Phrase(String.valueOf( det.getQuantite())));
				nmbecout.setPaddingLeft(2);
				nmbecout.setVerticalAlignment(Element.ALIGN_MIDDLE);
				nmbecout.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(nmbecout);
				
				
				//revenu global
				PdfPCell revnuglobal = new PdfPCell(new Phrase (String.valueOf(det.getTtc())));
				revnuglobal.setPaddingLeft(2);
				revnuglobal.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revnuglobal.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revnuglobal);
				
				//tax telecome
				PdfPCell taxTel = new PdfPCell(new Phrase(String.valueOf(det.getTax_telecom())));
				taxTel.setPaddingLeft(3);
				taxTel.setVerticalAlignment(Element.ALIGN_MIDDLE);
				taxTel.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(taxTel);
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getPart_TTC())));
				partttc.setPaddingLeft(3);
				partttc.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partttc.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partttc);
				
				
				//revenu smart
				PdfPCell revenusmart = new PdfPCell(new Phrase (String.valueOf(det.getPart_smart())));
				revenusmart.setPaddingLeft(3);
				revenusmart.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revenusmart.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revenusmart);
				
				
				
				
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_artiste())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}
	
	
	
	public static ByteArrayInputStream StateChansonPdfReportById(List<OrangeExcel> details)
	{
		
		Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, BOLD, BaseColor.GRAY);
		Font catFont15B = new Font(Font.FontFamily.TIMES_ROMAN, 15, BOLD, BaseColor.RED);
		Font catFont17Bcour = new Font(Font.FontFamily.COURIER, 17, BOLD, BaseColor.RED);
		Font subFontPara13B = new Font(Font.FontFamily.TIMES_ROMAN, 13, BOLD, BaseColor.BLACK);
		Font subFontPara13N = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.BLACK);
		Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 11, BOLD);

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, out);
			document.open();
			//add Text to pdf file
			com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER,18,BaseColor.GREEN);
			Paragraph para = new Paragraph("Statistiques total par mois",font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(9);
			
			//make column titre 
			Stream.of("Date","Nom Chanson","Nom Artiste","Nombre d'ecoute","Tax telecome","Total net de redavance","Revenu Global Smart","HTva","Part Artiste").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(OrangeExcel det : details)
			{
					
				//date1
				PdfPCell dateCe = new PdfPCell (new Phrase (String.valueOf(det.getDate1())));
				dateCe.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCe.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCe.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCe);
				
				PdfPCell dateCell1 = new PdfPCell (new Phrase (String.valueOf(det.getContent())));
				dateCell1.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell1.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell1.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell1);
				//date1
				PdfPCell dateCell = new PdfPCell (new Phrase (String.valueOf(det.getNamea())));
				dateCell.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell);
				
				
				
				
				//Nombre d'ecout
				PdfPCell nmbecout = new PdfPCell(new Phrase(String.valueOf( det.getQuantite())));
				nmbecout.setPaddingLeft(2);
				nmbecout.setVerticalAlignment(Element.ALIGN_MIDDLE);
				nmbecout.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(nmbecout);
				
				
				//revenu global
				/*PdfPCell revnuglobal = new PdfPCell(new Phrase (String.valueOf(det.getTtc())));
				revnuglobal.setPaddingLeft(2);
				revnuglobal.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revnuglobal.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revnuglobal);*/
				
				//tax telecome
				PdfPCell taxTel = new PdfPCell(new Phrase(String.valueOf(det.getTax_telecom())));
				taxTel.setPaddingLeft(3);
				taxTel.setVerticalAlignment(Element.ALIGN_MIDDLE);
				taxTel.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(taxTel);
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getPart_TTC())));
				partttc.setPaddingLeft(3);
				partttc.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partttc.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partttc);
				
				
				//revenu smart
				PdfPCell revenusmart = new PdfPCell(new Phrase (String.valueOf(det.getPart_smart())));
				revenusmart.setPaddingLeft(3);
				revenusmart.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revenusmart.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revenusmart);
				
				
				
				
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_artiste())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}
	
	
	public static ByteArrayInputStream StatePlatformePdfReportById(List<OrangeExcel> details)
	{
		
		Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, BOLD, BaseColor.GRAY);
		Font catFont15B = new Font(Font.FontFamily.TIMES_ROMAN, 15, BOLD, BaseColor.RED);
		Font catFont17Bcour = new Font(Font.FontFamily.COURIER, 17, BOLD, BaseColor.RED);
		Font subFontPara13B = new Font(Font.FontFamily.TIMES_ROMAN, 13, BOLD, BaseColor.BLACK);
		Font subFontPara13N = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.BLACK);
		Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 11, BOLD);

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, out);
			document.open();
			//add Text to pdf file
			com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER,18,BaseColor.GREEN);
			Paragraph para = new Paragraph("Statistiques total par mois",font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(7);
			
			//make column titre 
			Stream.of("Nom Platforme","Nombre d'ecoute","Tax telecome","Total net de redavance","Revenu Global Smart","HTva","Part Artiste").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(OrangeExcel det : details)
			{
				
				PdfPCell dateCell1 = new PdfPCell (new Phrase (String.valueOf(det.getPlateforme())));
				dateCell1.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell1.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell1.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell1);
				//date1
			
				
				
				
				
				//Nombre d'ecout
				PdfPCell nmbecout = new PdfPCell(new Phrase(String.valueOf( det.getQuantite())));
				nmbecout.setPaddingLeft(2);
				nmbecout.setVerticalAlignment(Element.ALIGN_MIDDLE);
				nmbecout.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(nmbecout);
				
				
				//revenu global
				/*PdfPCell revnuglobal = new PdfPCell(new Phrase (String.valueOf(det.getTtc())));
				revnuglobal.setPaddingLeft(2);
				revnuglobal.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revnuglobal.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revnuglobal);*/
				
				//tax telecome
				PdfPCell taxTel = new PdfPCell(new Phrase(String.valueOf(det.getTax_telecom())));
				taxTel.setPaddingLeft(3);
				taxTel.setVerticalAlignment(Element.ALIGN_MIDDLE);
				taxTel.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(taxTel);
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getPart_TTC())));
				partttc.setPaddingLeft(3);
				partttc.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partttc.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partttc);
				
				
				//revenu smart
				PdfPCell revenusmart = new PdfPCell(new Phrase (String.valueOf(det.getPart_smart())));
				revenusmart.setPaddingLeft(3);
				revenusmart.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revenusmart.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revenusmart);
				
				
				
				
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_artiste())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}

	
	public static ByteArrayInputStream StatePlatformePdfReport(List<OrangeExcel> details)
	{
		
		Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, BOLD, BaseColor.GRAY);
		Font catFont15B = new Font(Font.FontFamily.TIMES_ROMAN, 15, BOLD, BaseColor.RED);
		Font catFont17Bcour = new Font(Font.FontFamily.COURIER, 17, BOLD, BaseColor.RED);
		Font subFontPara13B = new Font(Font.FontFamily.TIMES_ROMAN, 13, BOLD, BaseColor.BLACK);
		Font subFontPara13N = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.BLACK);
		Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 11, BOLD);

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, out);
			document.open();
			//add Text to pdf file
			com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER,18,BaseColor.GREEN);
			Paragraph para = new Paragraph("Statistiques total par mois",font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(8);
			
			//make column titre 
			Stream.of("Nom Platforme","Nombre d'ecoute","Revenu global Op","Tax telecome","Total net de redavance","Revenu Global Smart","HTva","Part Artiste").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(OrangeExcel det : details)
			{
				
				PdfPCell dateCell1 = new PdfPCell (new Phrase (String.valueOf(det.getPlateforme())));
				dateCell1.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell1.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell1.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell1);
				//date1
			
				
				
				
				
				//Nombre d'ecout
				PdfPCell nmbecout = new PdfPCell(new Phrase(String.valueOf( det.getQuantite())));
				nmbecout.setPaddingLeft(2);
				nmbecout.setVerticalAlignment(Element.ALIGN_MIDDLE);
				nmbecout.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(nmbecout);
				
				
				//revenu global
				PdfPCell revnuglobal = new PdfPCell(new Phrase (String.valueOf(det.getTtc())));
				revnuglobal.setPaddingLeft(2);
				revnuglobal.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revnuglobal.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revnuglobal);
				
				//tax telecome
				PdfPCell taxTel = new PdfPCell(new Phrase(String.valueOf(det.getTax_telecom())));
				taxTel.setPaddingLeft(3);
				taxTel.setVerticalAlignment(Element.ALIGN_MIDDLE);
				taxTel.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(taxTel);
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getPart_TTC())));
				partttc.setPaddingLeft(3);
				partttc.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partttc.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partttc);
				
				
				//revenu smart
				PdfPCell revenusmart = new PdfPCell(new Phrase (String.valueOf(det.getPart_smart())));
				revenusmart.setPaddingLeft(3);
				revenusmart.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revenusmart.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revenusmart);
				
				
				
				
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_artiste())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}
	
	
	
	public static ByteArrayInputStream StateCategoriePdfReport(List<OrangeExcel> details)
	{
		
		Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, BOLD, BaseColor.GRAY);
		Font catFont15B = new Font(Font.FontFamily.TIMES_ROMAN, 15, BOLD, BaseColor.RED);
		Font catFont17Bcour = new Font(Font.FontFamily.COURIER, 17, BOLD, BaseColor.RED);
		Font subFontPara13B = new Font(Font.FontFamily.TIMES_ROMAN, 13, BOLD, BaseColor.BLACK);
		Font subFontPara13N = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.BLACK);
		Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 11, BOLD);

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, out);
			document.open();
			//add Text to pdf file
			com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER,18,BaseColor.GREEN);
			Paragraph para = new Paragraph("Statistiques total par mois",font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(8);
			
			//make column titre 
			Stream.of("Nom Categorie","Nombre d'ecoute","Revenu global Op","Tax telecome","Total net de redavance","Revenu Global Smart","HTva","Part Artiste").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(OrangeExcel det : details)
			{
				
				PdfPCell dateCell1 = new PdfPCell (new Phrase (String.valueOf(det.getCategory())));
				dateCell1.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell1.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell1.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell1);
				//date1
			
				
				
				
				
				//Nombre d'ecout
				PdfPCell nmbecout = new PdfPCell(new Phrase(String.valueOf( det.getQuantite())));
				nmbecout.setPaddingLeft(2);
				nmbecout.setVerticalAlignment(Element.ALIGN_MIDDLE);
				nmbecout.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(nmbecout);
				
				
				//revenu global
				PdfPCell revnuglobal = new PdfPCell(new Phrase (String.valueOf(det.getTtc())));
				revnuglobal.setPaddingLeft(2);
				revnuglobal.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revnuglobal.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revnuglobal);
				
				//tax telecome
				PdfPCell taxTel = new PdfPCell(new Phrase(String.valueOf(det.getTax_telecom())));
				taxTel.setPaddingLeft(3);
				taxTel.setVerticalAlignment(Element.ALIGN_MIDDLE);
				taxTel.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(taxTel);
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getPart_TTC())));
				partttc.setPaddingLeft(3);
				partttc.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partttc.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partttc);
				
				
				//revenu smart
				PdfPCell revenusmart = new PdfPCell(new Phrase (String.valueOf(det.getPart_smart())));
				revenusmart.setPaddingLeft(3);
				revenusmart.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revenusmart.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revenusmart);
				
				
				
				
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_artiste())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}
	
	
	
	
	
	
	public static ByteArrayInputStream StateCategoriePdfReportByID(List<OrangeExcel> details)
	{
		
		Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, BOLD, BaseColor.GRAY);
		Font catFont15B = new Font(Font.FontFamily.TIMES_ROMAN, 15, BOLD, BaseColor.RED);
		Font catFont17Bcour = new Font(Font.FontFamily.COURIER, 17, BOLD, BaseColor.RED);
		Font subFontPara13B = new Font(Font.FontFamily.TIMES_ROMAN, 13, BOLD, BaseColor.BLACK);
		Font subFontPara13N = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.BLACK);
		Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 11, BOLD);

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, out);
			document.open();
			//add Text to pdf file
			com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER,18,BaseColor.GREEN);
			Paragraph para = new Paragraph("Statistiques total par mois",font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(7);
			
			//make column titre 
			Stream.of("Nom Categorie","Nombre d'ecoute","Tax telecome","Total net de redavance","Revenu Global Smart","HTva","Part Artiste").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(OrangeExcel det : details)
			{
				
				PdfPCell dateCell1 = new PdfPCell (new Phrase (String.valueOf(det.getCategory())));
				dateCell1.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell1.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell1.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell1);
				//date1
			
				
				
				
				
				//Nombre d'ecout
				PdfPCell nmbecout = new PdfPCell(new Phrase(String.valueOf( det.getQuantite())));
				nmbecout.setPaddingLeft(2);
				nmbecout.setVerticalAlignment(Element.ALIGN_MIDDLE);
				nmbecout.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(nmbecout);
				
				
				//revenu global
			/*	PdfPCell revnuglobal = new PdfPCell(new Phrase (String.valueOf(det.getTtc())));
				revnuglobal.setPaddingLeft(2);
				revnuglobal.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revnuglobal.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revnuglobal);*/
				
				//tax telecome
				PdfPCell taxTel = new PdfPCell(new Phrase(String.valueOf(det.getTax_telecom())));
				taxTel.setPaddingLeft(3);
				taxTel.setVerticalAlignment(Element.ALIGN_MIDDLE);
				taxTel.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(taxTel);
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getPart_TTC())));
				partttc.setPaddingLeft(3);
				partttc.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partttc.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partttc);
				
				
				//revenu smart
				PdfPCell revenusmart = new PdfPCell(new Phrase (String.valueOf(det.getPart_smart())));
				revenusmart.setPaddingLeft(3);
				revenusmart.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revenusmart.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revenusmart);
				
				
				
				
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_artiste())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}
	
	

		public ByteArrayInputStream StateRevenuPdfReport(List<OrangeExcel> details)
		{
			
			Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, BOLD, BaseColor.GRAY);
			Font catFont15B = new Font(Font.FontFamily.TIMES_ROMAN, 15, BOLD, BaseColor.RED);
			Font catFont17Bcour = new Font(Font.FontFamily.COURIER, 17, BOLD, BaseColor.RED);
			Font subFontPara13B = new Font(Font.FontFamily.TIMES_ROMAN, 13, BOLD, BaseColor.BLACK);
			Font subFontPara13N = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.BLACK);
			Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 11, BOLD);

			Document document = new Document();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			try {
				PdfWriter.getInstance(document, out);
				document.open();
				//add Text to pdf file
				com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER,18,BaseColor.GREEN);
				Paragraph para = new Paragraph("Statistiques total par mois",font);
				para.setAlignment(Element.ALIGN_CENTER);
				document.add(para);
				document.add(Chunk.NEWLINE);
				
				PdfPTable table = new PdfPTable(10);
				
				//make column titre 
				Stream.of("Nom Artiste","Date Début","Date fin","Nombre d'ecoute","Revenu global Op","Tax telecome","Total net de redavance","Revenu Global Smart","HTva","Part Artiste").forEach(headerTitle->{
					PdfPCell header = new PdfPCell();
					com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
					
					header.setBackgroundColor(BaseColor.DARK_GRAY);
					header.setHorizontalAlignment(Element.ALIGN_BASELINE);
					header.setBorder(5);
					header.setPhrase(new Phrase(headerTitle,headfont));
					table.addCell(header);
				});
				// List<details> details = new ArrayList<>();
				for(OrangeExcel det : details)
				{
					PdfPCell nomArt = new PdfPCell (new Phrase (String.valueOf(det.getNamea())));
					nomArt.setPadding(-1);
					//dateCell.setPaddingRight(0);
					nomArt.setVerticalAlignment(Element.ALIGN_CENTER);
					nomArt.setHorizontalAlignment(Element.ALIGN_TOP);
					table.addCell(nomArt);
				
					//date1
					PdfPCell dateCell = new PdfPCell (new Phrase (String.valueOf(det.getDate1())));
					dateCell.setPadding(-1);
					//dateCell.setPaddingRight(0);
					dateCell.setVerticalAlignment(Element.ALIGN_CENTER);
					dateCell.setHorizontalAlignment(Element.ALIGN_TOP);
					table.addCell(dateCell);
					
					//Date fin 
					PdfPCell date2Cell = new PdfPCell( new Phrase (String.valueOf(det.getDate2())));
					date2Cell.setPaddingLeft(1);
					date2Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					date2Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(date2Cell);
					
					
					//Nombre d'ecout
					PdfPCell nmbecout = new PdfPCell(new Phrase(String.valueOf( det.getQuantite())));
					nmbecout.setPaddingLeft(2);
					nmbecout.setVerticalAlignment(Element.ALIGN_MIDDLE);
					nmbecout.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(nmbecout);
					
					
					//revenu global
					PdfPCell revnuglobal = new PdfPCell(new Phrase (String.valueOf(det.getTtc())));
					revnuglobal.setPaddingLeft(2);
					revnuglobal.setVerticalAlignment(Element.ALIGN_MIDDLE);
					revnuglobal.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(revnuglobal);
					
					//tax telecome
					PdfPCell taxTel = new PdfPCell(new Phrase(String.valueOf(det.getTax_telecom())));
					taxTel.setPaddingLeft(3);
					taxTel.setVerticalAlignment(Element.ALIGN_MIDDLE);
					taxTel.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(taxTel);
					
					PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getPart_TTC())));
					partttc.setPaddingLeft(3);
					partttc.setVerticalAlignment(Element.ALIGN_MIDDLE);
					partttc.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(partttc);
					
					
					//revenu smart
					PdfPCell revenusmart = new PdfPCell(new Phrase (String.valueOf(det.getPart_smart())));
					revenusmart.setPaddingLeft(3);
					revenusmart.setVerticalAlignment(Element.ALIGN_MIDDLE);
					revenusmart.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(revenusmart);
					
					
					
					
					
					//Tva
					PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
					tva.setPaddingLeft(1);
					tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
					tva.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(tva);
					
					
					//Part artiste
					PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_artiste())));
					partArt.setPaddingLeft(1);
					partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
					partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(partArt);
				}
				
				document.add(table);
				document.close();
				
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return new ByteArrayInputStream(out.toByteArray());
	}
	
		
		
		public ByteArrayInputStream StateRevenuPdfReportById(List<OrangeExcel> details)
		{
			
			Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, BOLD, BaseColor.GRAY);
			Font catFont15B = new Font(Font.FontFamily.TIMES_ROMAN, 15, BOLD, BaseColor.RED);
			Font catFont17Bcour = new Font(Font.FontFamily.COURIER, 17, BOLD, BaseColor.RED);
			Font subFontPara13B = new Font(Font.FontFamily.TIMES_ROMAN, 13, BOLD, BaseColor.BLACK);
			Font subFontPara13N = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.BLACK);
			Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 11, BOLD);

			Document document = new Document();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			try {
				PdfWriter.getInstance(document, out);
				document.open();
				//add Text to pdf file
				com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER,18,BaseColor.GREEN);
				Paragraph para = new Paragraph("Statistiques total par mois",font);
				para.setAlignment(Element.ALIGN_CENTER);
				document.add(para);
				document.add(Chunk.NEWLINE);
				
				PdfPTable table = new PdfPTable(6);
				
				//make column titre 
				Stream.of("Nom Artiste","Date Début","Date fin","Nombre d'ecoute","Revenu Global Smart","Part Artiste").forEach(headerTitle->{
					PdfPCell header = new PdfPCell();
					com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
					
					header.setBackgroundColor(BaseColor.DARK_GRAY);
					header.setHorizontalAlignment(Element.ALIGN_BASELINE);
					header.setBorder(5);
					header.setPhrase(new Phrase(headerTitle,headfont));
					table.addCell(header);
				});
				// List<details> details = new ArrayList<>();
				for(OrangeExcel det : details)
				{
					PdfPCell nomArt = new PdfPCell (new Phrase (String.valueOf(det.getNamea())));
					nomArt.setPadding(-1);
					//dateCell.setPaddingRight(0);
					nomArt.setVerticalAlignment(Element.ALIGN_CENTER);
					nomArt.setHorizontalAlignment(Element.ALIGN_TOP);
					table.addCell(nomArt);
				
					//date1
					PdfPCell dateCell = new PdfPCell (new Phrase (String.valueOf(det.getDate1())));
					dateCell.setPadding(-1);
					//dateCell.setPaddingRight(0);
					dateCell.setVerticalAlignment(Element.ALIGN_CENTER);
					dateCell.setHorizontalAlignment(Element.ALIGN_TOP);
					table.addCell(dateCell);
					
					//Date fin 
					PdfPCell date2Cell = new PdfPCell( new Phrase (String.valueOf(det.getDate2())));
					date2Cell.setPaddingLeft(1);
					date2Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					date2Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(date2Cell);
					
					
					//Nombre d'ecout
					PdfPCell nmbecout = new PdfPCell(new Phrase(String.valueOf( det.getQuantite())));
					nmbecout.setPaddingLeft(2);
					nmbecout.setVerticalAlignment(Element.ALIGN_MIDDLE);
					nmbecout.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(nmbecout);
					
					
					//revenu global
					/*PdfPCell revnuglobal = new PdfPCell(new Phrase (String.valueOf(det.getTtc())));
					revnuglobal.setPaddingLeft(2);
					revnuglobal.setVerticalAlignment(Element.ALIGN_MIDDLE);
					revnuglobal.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(revnuglobal);*/
					
					
					
					
					//revenu smart
					PdfPCell revenusmart = new PdfPCell(new Phrase (String.valueOf(det.getPart_smart())));
					revenusmart.setPaddingLeft(3);
					revenusmart.setVerticalAlignment(Element.ALIGN_MIDDLE);
					revenusmart.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(revenusmart);
					
					
					
					
					
					
					
					
					//Part artiste
					PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_artiste())));
					partArt.setPaddingLeft(1);
					partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
					partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(partArt);
				}
				
				document.add(table);
				document.close();
				
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return new ByteArrayInputStream(out.toByteArray());
	}
}
