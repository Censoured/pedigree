package mpr.proj;

import java.io.FileOutputStream;
import java.util.Set;

import mpr.proj.pedigree.Horse;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public abstract class PdfExport {
	  
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	
	public static void exportAncestors(Horse horse, String filename, int deep)	{
		try {
			System.out.println("Prosze czekac, trwa tworzenie pliku...");
			Document document = new Document(PageSize.A4.rotate());
			PdfWriter.getInstance(document, new FileOutputStream(filename+"_ancestors.pdf"));
			document.open();
			addMetaData(document);
			addContentAncestors(document, horse, deep);
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
	private static void addContentAncestors(Document document, Horse horse, int deep) throws DocumentException {
		Anchor anchor = new Anchor("Drzewo rodowodowe konia "+horse.getName(), catFont);
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);
		createTable(catPart, horse, deep);
		document.add(catPart);
	}
	
	public static void getParents(Horse horse, int deep, PdfPTable table){
		//przeciazenie metody dla domyslnego parametru
		getParents(horse, deep, 1, table);
	}

	public static void getParents(Horse horse, int deep, int tab, PdfPTable table)	{
		PdfPCell cell;
        String name;
        if (horse.getName() != null) {
                name = horse.getName();
        } else {
                name = "Nieznane";
        }
        cell = new PdfPCell(new Phrase(name));
        cell.setRowspan((int) Math.pow(2,deep));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        if (horse.getSire() != null && deep > 0) {
                getParents(horse.getSire(), deep-1, table);
        } else if (horse.getSire() == null && deep > 0) {
                getParents(new Horse(), deep-1,table);
        }
        if (horse.getDam() != null && deep > 0) {
        		getParents(horse.getDam(), deep-1, table);
        } else if (horse.getDam() == null && deep > 0) {
        		getParents(new Horse(), deep-1, table);
        }
	}
	
	private static void createTable(Section subCatPart, Horse horse, int deep) throws BadElementException {
		PdfPTable table = new PdfPTable(deep+1);
		getParents(horse, deep, table);
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
}
