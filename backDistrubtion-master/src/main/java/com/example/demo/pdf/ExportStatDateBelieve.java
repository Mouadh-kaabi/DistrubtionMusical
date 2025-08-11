package com.example.demo.pdf;

import static com.itextpdf.text.Font.BOLD;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.example.demo.Interface.BelieveExcel;
import com.example.demo.Interface.DeezerExcel;
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
public class ExportStatDateBelieve {

	
	
	public static ByteArrayInputStream StateDatePdfReport(List<BelieveExcel> details)
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
			Stream.of("Date Début","Date fin","Nombre d'ecoute","Revenu global Op","Revenu Global Smart","Part Artiste").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(BelieveExcel det : details)
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

	
	public static ByteArrayInputStream StateDatePdfReportByIdPdf(List<BelieveExcel> details)
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
			
			PdfPTable table = new PdfPTable(4);
			
			//make column titre 
			Stream.of("Date Début","Date fin","Nombre d'ecoute","Part Artiste").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(BelieveExcel det : details)
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
				
				
				
				
				//revenu smart
				/*PdfPCell revenusmart = new PdfPCell(new Phrase (String.valueOf(det.getPart_smart())));
				revenusmart.setPaddingLeft(3);
				revenusmart.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revenusmart.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revenusmart);*/
				
				
				
				
				
				
				
				
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

	
	public static ByteArrayInputStream StateDatePdfReportById(List<BelieveExcel> details)
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
			
			PdfPTable table = new PdfPTable(5);
			
			//make column titre 
			Stream.of("Date Début","Date fin","Nombre d'ecoute","Revenu Global Smart","Part Artiste").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(BelieveExcel det : details)
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


	public static ByteArrayInputStream StateArtistePdfReport(List<BelieveExcel> details)
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
			Stream.of("Nom Artiste","Nombre d'ecoute","Revenu global Op (Eur)","Revenu global Op (Tnd)","Revenu Global Smart","Part Artiste").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(BelieveExcel det : details)
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
				
				//Date fin 
				PdfPCell date2Cell = new PdfPCell( new Phrase (String.valueOf(det.getTTC_EUR())));
				date2Cell.setPaddingLeft(1);
				date2Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				date2Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(date2Cell);
				
				//revenu global
				PdfPCell revnuglobal = new PdfPCell(new Phrase (String.valueOf(det.getTtc())));
				revnuglobal.setPaddingLeft(2);
				revnuglobal.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revnuglobal.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revnuglobal);
				
				
				
				
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



	public static ByteArrayInputStream StateChansonPdfReport(List<BelieveExcel> details)
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
			Stream.of("Nom Chanson","Nom Artiste","Nombre d'ecoute","Revenu global Op (Eur)","Revenu global Op (Tnd)","Revenu Global Smart","Part Artiste").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(BelieveExcel det : details)
			{
				
				PdfPCell dateCell12 = new PdfPCell (new Phrase (String.valueOf(det.getContent())));
				dateCell12.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell12.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell12.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell12);
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
				
				//Date fin 
				PdfPCell date2Cell = new PdfPCell( new Phrase (String.valueOf(det.getTTC_EUR())));
				date2Cell.setPaddingLeft(1);
				date2Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				date2Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(date2Cell);
				
				//revenu global
				PdfPCell revnuglobal = new PdfPCell(new Phrase (String.valueOf(det.getTtc())));
				revnuglobal.setPaddingLeft(2);
				revnuglobal.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revnuglobal.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revnuglobal);
				
				
				
				
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

	
	
	
	
	public static ByteArrayInputStream StateChansonPdfReportById(List<BelieveExcel> details)
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
			
			PdfPTable table = new PdfPTable(4);
			
			//make column titre 
			Stream.of("Nom Chanson","Nom Artiste","Nombre d'ecoute","Part Artiste").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(BelieveExcel det : details)
			{
				
				PdfPCell dateCell12 = new PdfPCell (new Phrase (String.valueOf(det.getContent())));
				dateCell12.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell12.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell12.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell12);
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
				
				//Date fin 
				/*PdfPCell date2Cell = new PdfPCell( new Phrase (String.valueOf(det.getTTC_EUR())));
				date2Cell.setPaddingLeft(1);
				date2Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				date2Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(date2Cell);*/
				
				//revenu global
			/*	PdfPCell revnuglobal = new PdfPCell(new Phrase (String.valueOf(det.getTtc())));
				revnuglobal.setPaddingLeft(2);
				revnuglobal.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revnuglobal.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revnuglobal);*/
				
				
				
				
				//revenu smart
				/*PdfPCell revenusmart = new PdfPCell(new Phrase (String.valueOf(det.getPart_smart())));
				revenusmart.setPaddingLeft(3);
				revenusmart.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revenusmart.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revenusmart);*/
				
				
				
				
				
				
				
				
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
	
	
	public static ByteArrayInputStream StatePlatformePdfReport(List<BelieveExcel> details)
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
			
			PdfPTable table = new PdfPTable(5);
			
			//make column titre 
			Stream.of("Platfomre","Nombre d'ecoute","Revenu global Op","Revenu Global Smart","Part Artiste").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(BelieveExcel det : details)
			{
				
				PdfPCell dateCell12 = new PdfPCell (new Phrase (String.valueOf(det.getPlateforme())));
				dateCell12.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell12.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell12.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell12);
				
				
				
				
				
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
	
	
	
	public static ByteArrayInputStream StatePlatformePdfReportById(List<BelieveExcel> details)
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
			
			PdfPTable table = new PdfPTable(3);
			
			//make column titre 
			Stream.of("Platfomre","Nombre d'ecoute","Part Artiste").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(BelieveExcel det : details)
			{
				
				PdfPCell dateCell12 = new PdfPCell (new Phrase (String.valueOf(det.getPlateforme())));
				dateCell12.setPadding(-1);
				//dateCell.setPaddingRight(0);
				dateCell12.setVerticalAlignment(Element.ALIGN_CENTER);
				dateCell12.setHorizontalAlignment(Element.ALIGN_TOP);
				table.addCell(dateCell12);
				
				
				
				
				
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
			/*	PdfPCell revenusmart = new PdfPCell(new Phrase (String.valueOf(det.getPart_smart())));
				revenusmart.setPaddingLeft(3);
				revenusmart.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revenusmart.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revenusmart);*/
				
				
				
				
				
				
				
				
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
	
	public static ByteArrayInputStream StateRevenuPdfReport(List<BelieveExcel> details)
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
			Stream.of("Nom Artiste","Date Début","Date fin","Nombre d'ecoute","Revenu global Op","Revenu Global Eur","Revenu Global Smart","Part Artiste").forEach(headerTitle->{
				PdfPCell header = new PdfPCell();
				com.itextpdf.text.Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
				
				header.setBackgroundColor(BaseColor.DARK_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_BASELINE);
				header.setBorder(5);
				header.setPhrase(new Phrase(headerTitle,headfont));
				table.addCell(header);
			});
			// List<details> details = new ArrayList<>();
			for(BelieveExcel det : details)
			{
				//date1
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
				
				//revenu global eur
				PdfPCell revnuglobalEur = new PdfPCell(new Phrase (String.valueOf(det.getTTC_EUR())));
				revnuglobalEur.setPaddingLeft(2);
				revnuglobalEur.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revnuglobalEur.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revnuglobalEur);
				
				
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
	
	
	
	
	
	
	
	public static ByteArrayInputStream StateRevenuPdfReportById(List<BelieveExcel> details)
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
			for(BelieveExcel det : details)
			{
				//date1
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
				
				//revenu global eur
				/*PdfPCell revnuglobalEur = new PdfPCell(new Phrase (String.valueOf(det.getTTC_EUR())));
				revnuglobalEur.setPaddingLeft(2);
				revnuglobalEur.setVerticalAlignment(Element.ALIGN_MIDDLE);
				revnuglobalEur.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(revnuglobalEur);*/
				
				
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
