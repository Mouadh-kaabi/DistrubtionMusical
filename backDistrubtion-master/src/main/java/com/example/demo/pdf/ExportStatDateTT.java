package com.example.demo.pdf;

import static com.itextpdf.text.Font.BOLD;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.example.demo.Interface.OrangeExcel;
import com.example.demo.Interface.TTExcel;
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


@Service
public class ExportStatDateTT {

	
	public static ByteArrayInputStream StateDatePdfReport(List<TTExcel> details)
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
			Stream.of("Date Début","Date fin","Nombre d'ecoute","Revenu global Op","Tax telecome","Total net de redavance","Revenu Global Smart","Revenu Global Cp","HTVA").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(TTExcel det : details)
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
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getTotalnetderedevance())));
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
				
				
				
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_cp())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}
	
	
	
	public static ByteArrayInputStream StateDatePdfReportById(List<TTExcel> details)
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
			Stream.of("Date Début","Date fin","Nombre d'ecoute","Tax telecome","Total net de redavance","Revenu Global Smart","Revenu Global Cp","HTVA").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(TTExcel det : details)
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
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getTotalnetderedevance())));
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
				
				
				
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_cp())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}
	
	public static ByteArrayInputStream StateArtistePdfReport(List<TTExcel> details)
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
			Stream.of("Nom Artiste","Nombre d'ecoute","Revenu global Op","Tax telecome","Total net de redavance","Revenu Global Smart","Revenu Global Cp","HTVA").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(TTExcel det : details)
			{
				//date1
				PdfPCell dateCell = new PdfPCell (new Phrase (String.valueOf(det.getNamea())));
				dateCell.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell);
				
				//Date fin 
			/*	PdfPCell date2Cell = new PdfPCell( new Phrase (String.valueOf(det.getDate2())));
				date2Cell.setPaddingLeft(1);
				date2Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				date2Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(date2Cell);*/
				
				
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
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getTotalnetderedevance())));
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
				
				
				
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_cp())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}
	
	
	public static ByteArrayInputStream StateArtistePdfReportByID(List<TTExcel> details)
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
			Stream.of("Nom Artiste","Nombre d'ecoute","Tax telecome","Total net de redavance","Revenu Global Smart","Revenu Global Cp","HTVA").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(TTExcel det : details)
			{
				//date1
				PdfPCell dateCell = new PdfPCell (new Phrase (String.valueOf(det.getNamea())));
				dateCell.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell);
				
				//Date fin 
			/*	PdfPCell date2Cell = new PdfPCell( new Phrase (String.valueOf(det.getDate2())));
				date2Cell.setPaddingLeft(1);
				date2Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				date2Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(date2Cell);*/
				
				
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
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getTotalnetderedevance())));
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
				
				
				
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_cp())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}


	public static ByteArrayInputStream StateChansonPdfReport(List<TTExcel> details)
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
			Stream.of("Nom Chanson","Nom Artiste","Nombre d'ecoute","Revenu global Op","Tax telecome","Total net de redavance","Revenu Global Smart","Revenu Global Cp","HTVA").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(TTExcel det : details)
			{
				
				PdfPCell dateCell2 = new PdfPCell (new Phrase (String.valueOf(det.getContent())));
				dateCell2.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell2.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell2.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell2);
				
				//date1
				PdfPCell dateCell = new PdfPCell (new Phrase (String.valueOf(det.getNamea())));
				dateCell.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell);
				
				//Date fin 
			/*	PdfPCell date2Cell = new PdfPCell( new Phrase (String.valueOf(det.getDate2())));
				date2Cell.setPaddingLeft(1);
				date2Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				date2Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(date2Cell);*/
				
				
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
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getTotalnetderedevance())));
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
				
				
				
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_cp())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}
	
	
	
	public static ByteArrayInputStream StateChansonPdfReportById(List<TTExcel> details)
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
			Stream.of("Date","Nom Chanson","Nom Artiste","Nombre d'ecoute","Tax telecome","Total net de redavance","Revenu Global Smart","Revenu Global Cp","HTVA").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(TTExcel det : details)
			{
				PdfPCell dateCell3 = new PdfPCell (new Phrase (String.valueOf(det.getDate1())));
				dateCell3.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell3.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell3.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell3); 
				
				PdfPCell dateCell2 = new PdfPCell (new Phrase (String.valueOf(det.getContent())));
				dateCell2.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell2.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell2.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell2);
				
				//date1
				PdfPCell dateCell = new PdfPCell (new Phrase (String.valueOf(det.getNamea())));
				dateCell.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell);
				
				//Date fin 
			/*	PdfPCell date2Cell = new PdfPCell( new Phrase (String.valueOf(det.getDate2())));
				date2Cell.setPaddingLeft(1);
				date2Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				date2Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(date2Cell);*/
				
				
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
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getTotalnetderedevance())));
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
				
				
				
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_cp())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}

	
	
	
	
	public static ByteArrayInputStream StateCatPdfReport(List<TTExcel> details)
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
			Stream.of("Categorie","Sub Categorie","Nom Artiste","Nombre d'ecoute","Revenu global Op","Tax telecome","Total net de redavance","Revenu Global Smart","Revenu Global Cp","HTVA").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(TTExcel det : details)
			{
				
				PdfPCell dateCell2 = new PdfPCell (new Phrase (String.valueOf(det.getCategory())));
				dateCell2.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell2.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell2.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell2);
				
				PdfPCell dateCell1 = new PdfPCell (new Phrase (String.valueOf(det.getSubCategory())));
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
				
				//Date fin 
			/*	PdfPCell date2Cell = new PdfPCell( new Phrase (String.valueOf(det.getDate2())));
				date2Cell.setPaddingLeft(1);
				date2Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				date2Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(date2Cell);*/
				
				
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
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getTotalnetderedevance())));
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
				
				
				
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_cp())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}
	
	public static ByteArrayInputStream StateRevenuPdfReport(List<TTExcel> details)
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
			Stream.of("Date","Nom Artiste","Revenu Global ","Nombre d'ecoute","Tax telecome","Total net de redavance","Revenu Global Smart","Revenu Global Cp","HTVA").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(TTExcel det : details)
			{
				
				
				
				PdfPCell dateCell2 = new PdfPCell (new Phrase (String.valueOf(det.getDate1())));
				dateCell2.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell2.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell2.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell2);
				
				
				
				//date1
				PdfPCell dateCell = new PdfPCell (new Phrase (String.valueOf(det.getNamea())));
				dateCell.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell);
				
				
				//revenu global
				PdfPCell revnuglobal = new PdfPCell(new Phrase (String.valueOf(det.getTtc())));
				revnuglobal.setPaddingLeft(2);
				revnuglobal.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revnuglobal.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revnuglobal);
				
				
				//Date fin 
			/*	PdfPCell date2Cell = new PdfPCell( new Phrase (String.valueOf(det.getDate2())));
				date2Cell.setPaddingLeft(1);
				date2Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				date2Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(date2Cell);*/
				
				
				//Nombre d'ecout
				PdfPCell nmbecout = new PdfPCell(new Phrase(String.valueOf( det.getQuantite())));
				nmbecout.setPaddingLeft(2);
				nmbecout.setVerticalAlignment(Element.ALIGN_MIDDLE);
				nmbecout.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(nmbecout);
				
				
			
				
				//tax telecome
				PdfPCell taxTel = new PdfPCell(new Phrase(String.valueOf(det.getTax_telecom())));
				taxTel.setPaddingLeft(3);
				taxTel.setVerticalAlignment(Element.ALIGN_MIDDLE);
				taxTel.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(taxTel);
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getTotalnetderedevance())));
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
				
				
				
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_cp())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}
	
	
	
	public static ByteArrayInputStream StateCatPdfReportById(List<TTExcel> details)
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
			Stream.of("Categorie","Sub Categorie","Nom Artiste","Nombre d'ecoute","Tax telecome","Total net de redavance","Revenu Global Smart","Revenu Global Cp","HTVA").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(TTExcel det : details)
			{
				
				PdfPCell dateCell2 = new PdfPCell (new Phrase (String.valueOf(det.getCategory())));
				dateCell2.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell2.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell2.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell2);
				
				PdfPCell dateCell1 = new PdfPCell (new Phrase (String.valueOf(det.getSubCategory())));
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
				
				//Date fin 
			/*	PdfPCell date2Cell = new PdfPCell( new Phrase (String.valueOf(det.getDate2())));
				date2Cell.setPaddingLeft(1);
				date2Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				date2Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(date2Cell);*/
				
				
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
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getTotalnetderedevance())));
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
				
				
				
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_cp())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}
	
	
	
	public static ByteArrayInputStream StateRevenuPdfReportById(List<TTExcel> details)
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
			Stream.of("Date","Nom Artiste","Nombre d'ecoute","Tax telecome","Total net de redavance","Revenu Global Smart","Revenu Global Cp","HTVA").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(TTExcel det : details)
			{
				
				
				
				PdfPCell dateCell2 = new PdfPCell (new Phrase (String.valueOf(det.getDate1())));
				dateCell2.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell2.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell2.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell2);
				
			/*PdfPCell dateCell1 = new PdfPCell (new Phrase (String.valueOf(det.getContentProvider())));
				dateCell1.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell1.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell1.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell1);*/
				
				//date1
				PdfPCell dateCell = new PdfPCell (new Phrase (String.valueOf(det.getNamea())));
				dateCell.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell);
				
				//Date fin 
			/*	PdfPCell date2Cell = new PdfPCell( new Phrase (String.valueOf(det.getDate2())));
				date2Cell.setPaddingLeft(1);
				date2Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				date2Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(date2Cell);*/
				
				
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
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getTotalnetderedevance())));
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
				
				
				
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_cp())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}
	public static ByteArrayInputStream StateDateCPPdfReportById(List<TTExcel> details)
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
			Stream.of("Date Début","Date fin","Nombre d'ecoute","Tax telecome","Total net de redavance","Revenu Global Smart","Revenu Global Cp","HTVA").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(TTExcel det : details)
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
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getTotalnetderedevance())));
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
				
				
				
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_cp())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}
	
	
	public static ByteArrayInputStream StateChansonPdfReportCpById(List<TTExcel> details)
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
			Stream.of("Date debut","Date fin","Nom Chanson","Nom Artiste","Nombre d'ecoute","Tax telecome","Total net de redavance","Revenu Global Smart","Revenu Global Cp","HTVA").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(TTExcel det : details)
			{
				PdfPCell dateCell3 = new PdfPCell (new Phrase (String.valueOf(det.getDate1())));
				dateCell3.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell3.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell3.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell3); 
				
				
				PdfPCell dateCell4 = new PdfPCell (new Phrase (String.valueOf(det.getDate2())));
				dateCell4.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell4.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell4.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell4);
				
				
				PdfPCell dateCell2 = new PdfPCell (new Phrase (String.valueOf(det.getContent())));
				dateCell2.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell2.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell2.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell2);
				
				//date1
				PdfPCell dateCell = new PdfPCell (new Phrase (String.valueOf(det.getNamea())));
				dateCell.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell);
				
				//Date fin 
			/*	PdfPCell date2Cell = new PdfPCell( new Phrase (String.valueOf(det.getDate2())));
				date2Cell.setPaddingLeft(1);
				date2Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				date2Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(date2Cell);*/
				
				
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
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getTotalnetderedevance())));
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
				
				
				
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_cp())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}

	
	public static ByteArrayInputStream StateArtistePdfReportCpById(List<TTExcel> details)
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
			Stream.of("Date debut","Date fin","Nom Chanson","Nom Artiste","Nombre d'ecoute","Tax telecome","Total net de redavance","Revenu Global Smart","Revenu Global Cp","HTVA").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(TTExcel det : details)
			{
				PdfPCell dateCell3 = new PdfPCell (new Phrase (String.valueOf(det.getDate1())));
				dateCell3.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell3.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell3.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell3); 
				
				
				PdfPCell dateCell4 = new PdfPCell (new Phrase (String.valueOf(det.getDate2())));
				dateCell4.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell4.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell4.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell4);
				
				
				PdfPCell dateCell2 = new PdfPCell (new Phrase (String.valueOf(det.getContent())));
				dateCell2.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell2.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell2.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell2);
				
				//date1
				PdfPCell dateCell = new PdfPCell (new Phrase (String.valueOf(det.getNamea())));
				dateCell.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell);
				
				//Date fin 
			/*	PdfPCell date2Cell = new PdfPCell( new Phrase (String.valueOf(det.getDate2())));
				date2Cell.setPaddingLeft(1);
				date2Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				date2Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(date2Cell);*/
				
				
				//Nombre d'ecout
				PdfPCell nmbecout = new PdfPCell(new Phrase(String.valueOf( det.getQuantite())));
				nmbecout.setPaddingLeft(2);
				nmbecout.setVerticalAlignment(Element.ALIGN_MIDDLE);
				nmbecout.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(nmbecout);
				
				
				
				
				//tax telecome
				PdfPCell taxTel = new PdfPCell(new Phrase(String.valueOf(det.getTax_telecom())));
				taxTel.setPaddingLeft(3);
				taxTel.setVerticalAlignment(Element.ALIGN_MIDDLE);
				taxTel.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(taxTel);
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getTotalnetderedevance())));
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
				
				
				
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_cp())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				
			}
			
			document.add(table);
			document.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
}
	
	
	public static ByteArrayInputStream StateCatCpPdfReportById(List<TTExcel> details)
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
			Stream.of("Date","Categorie","Sub Categorie","Nom Artiste","Nombre d'ecoute","Tax telecome","Total net de redavance","Revenu Global Smart","Revenu Global Cp","HTVA").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(TTExcel det : details)
			{
				
				PdfPCell dateCell4 = new PdfPCell (new Phrase (String.valueOf(det.getDate1())));
				dateCell4.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell4.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell4.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell4);
				
				PdfPCell dateCell2 = new PdfPCell (new Phrase (String.valueOf(det.getCategory())));
				dateCell2.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell2.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell2.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell2);
				
				PdfPCell dateCell1 = new PdfPCell (new Phrase (String.valueOf(det.getSubCategory())));
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
				
				//Date fin 
			/*	PdfPCell date2Cell = new PdfPCell( new Phrase (String.valueOf(det.getDate2())));
				date2Cell.setPaddingLeft(1);
				date2Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				date2Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(date2Cell);*/
				
				
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
				
				PdfPCell partttc = new PdfPCell(new Phrase(String.valueOf(det.getTotalnetderedevance())));
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
				
				
				
				PdfPCell partArt = new PdfPCell(new Phrase(String.valueOf(det.getPart_cp())));
				partArt.setPaddingLeft(1);
				partArt.setVerticalAlignment(Element.ALIGN_MIDDLE);
				partArt.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(partArt);
				
				//Tva
				PdfPCell tva = new PdfPCell(new Phrase(String.valueOf(det.getHTVA())));
				tva.setPaddingLeft(1);
				tva.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tva.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(tva);
				
				
				//Part artiste
				
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
