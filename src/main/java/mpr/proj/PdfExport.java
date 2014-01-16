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
	private static String FILE = "file.pdf";
	  private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
	      Font.BOLD);
	  private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	      Font.NORMAL, BaseColor.RED);
	  private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
	      Font.BOLD);
	  private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	      Font.BOLD);
	
	public static void exportAncestors(Horse horse)	{
		try {
			System.out.println("Prosze czekac, trwa tworzenie pliku...");
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			addMetaData(document);
			addContentAncestors(document, horse);
			document.close();
			System.out.println("Wyeksportowano do pliku 'file.pdf'");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void exportOffsprings(Horse horse)	{
		try {
			System.out.println("Prosze czekac, trwa tworzenie pliku...");
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			addMetaData(document);
			addContentOffspring(document, horse);
			document.close();
			System.out.println("Wyeksportowano do pliku 'file.pdf'");
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
		Anchor anchor = new Anchor("First Chapter", catFont);
		anchor.setName("First Chapter");

		// Second parameter is the number of the chapter
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);

		// add a table
		createTable(catPart);

		// now add all this to the document
		document.add(catPart);
	}


	private static void createTable(Section subCatPart) throws BadElementException {
		PdfPTable table = new PdfPTable(4);

	    // t.setBorderColor(BaseColor.GRAY);
	    // t.setPadding(4);
	    // t.setSpacing(4);
	    // t.setBorderWidth(1);

	    PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("Table Header 2"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("Table Header 3"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);
	    table.setHeaderRows(1);

	    table.addCell("1.0");
	    table.addCell("1.1");
	    table.addCell("1.2");
	    table.addCell("1.3");
	    table.addCell("2.0");
	    table.addCell("2.1");
	    table.addCell("2.2");
	    table.addCell("2.3");

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
