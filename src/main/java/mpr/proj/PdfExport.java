package mpr.proj;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.Set;

import mpr.proj.pedigree.Horse;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public abstract class PdfExport {
	  private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
	      Font.BOLD);
	  private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	      Font.NORMAL, BaseColor.RED);
	  private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
	      Font.BOLD);
	  private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	      Font.BOLD);
	
	public static void exportAncestors(Horse horse, String filename)	{
		try {
			System.out.println("Prosze czekac, trwa tworzenie pliku...");
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(filename+"_ancestors.pdf"));
			document.open();
			addMetaData(document);
			addContentAncestors(document, horse);
			document.close();
			System.out.println("Wyeksportowano do pliku "+filename+"_ancestors.pdf");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void exportOffsprings(Horse horse, String filename)	{
		try {
			System.out.println("Prosze czekac, trwa tworzenie pliku...");
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(filename+"_offsprings.pdf"));
			document.open();
			addMetaData(document);
			addContentOffspring(document, horse);
			document.close();
			System.out.println("Wyeksportowano do pliku "+filename+"_ancestors.pdf");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void addMetaData(Document document) {
		document.addTitle("Drzewo rodowodowe konia");
		document.addAuthor("Damian Romiński");
		document.addCreator("Damian Romiński");
	}

	private static void addContentOffspring(Document document, Horse horse) throws DocumentException {
		Anchor anchor = new Anchor("Potomstwo konia "+horse.getName(), catFont);
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);
		createList(catPart, horse);
		document.add(catPart);
	}
	private static void addContentAncestors(Document document, Horse horse) throws DocumentException {
		Anchor anchor = new Anchor("Drzewo rodowodowe konia "+horse.getName(), catFont);
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);
		createTable(catPart, horse);
		document.add(catPart);
	}


	private static void createTable(Section subCatPart, Horse horse) throws BadElementException {
		PdfPTable table = new PdfPTable(7);
		//pierwszy wiersz
	    table.addCell(" ");
	    table.addCell(" ");
	    table.addCell(" ");
	    table.addCell(horse.getName());
	    table.addCell(" ");
	    table.addCell(" ");
	    table.addCell(" ");
	    //drugi wiersz
	    table.addCell(" ");
	    table.addCell(" ");
	    table.addCell(" ");
	    table.addCell(" ");
	    table.addCell(" ");
	    table.addCell(" ");
	    table.addCell(" ");
	    //trzeci wiersz
	    table.addCell(" ");
	    table.addCell(horse.getDam().getName());
	    table.addCell(" ");
	    table.addCell(" ");
	    table.addCell(" ");
	    table.addCell(horse.getSire().getName());
	    table.addCell(" ");
	    //czwarty wiersz
	    table.addCell(" ");
	    table.addCell(" ");
	    table.addCell(" ");
	    table.addCell(" ");
	    table.addCell(" ");
	    table.addCell(" ");
	    table.addCell(" ");
	    //piaty wiersz
	    table.addCell(horse.getDam().getDam().getName());
	    table.addCell(" ");
	    table.addCell(horse.getDam().getSire().getName());
	    table.addCell(" ");
	    table.addCell(horse.getSire().getDam().getName());
	    table.addCell(" ");
	    table.addCell(horse.getSire().getSire().getName());

	    subCatPart.add(table);

	}

	private static void createList(Section subCatPart, Horse horse) {
		Set<Horse> zbior = Collections.getHorseOffspring(horse.getID());
		List list = new List(true, false, 10);
		for(Horse a: zbior)	{
			list.add(new ListItem(a.getName()));
		}
	    subCatPart.add(list);
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
	    for (int i = 0; i < number; i++) {
	    	paragraph.add(new Paragraph(" "));
	    }
	}

}
