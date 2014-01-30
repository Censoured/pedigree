package mpr.proj;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import mpr.proj.pedigree.*;

public abstract class Menus {
	
	public static boolean intToBool(int x){
		if(x!=0) return true;
		else return false;
	}
	 	
	public static void mainMenu() throws ParseException	{
		int opis = 0;
		while(opis!=7)	{
			System.out.println("Menu glowne:");
			System.out.println("1. Podejrzyj tabele");
			System.out.println("2. Dodaj dane");
			System.out.println("3. Edytuj dane");
			System.out.println("4. Usun dane");
			System.out.println("5. Przodkowie konia");
			System.out.println("6. Potomstwo konia");
			System.out.println("7. Zakoncz program");
			System.out.print("Podaj operacje do wykonania: ");
			opis = EasyIn.getInt();
			if(opis==1) {
				showData();
			}
			if(opis==2) {
				addData();
			}
			if(opis==3) {
				updateData();
			}
			if(opis==4) {
				deleteData();
			}
			if(opis==5){
				showAncestors();
			}
			if(opis==6)	{
				showOffsprings();
			}
		}
	}
	
	public static void showData()	{
		System.out.println("Podglad tabeli.");
		System.out.println("1. Breeder");
		System.out.println("2. Color");
		System.out.println("3. Country");
		System.out.println("4. Horse");
		System.out.println("5. Sex");
		System.out.print("Wybierz tabele do podejrzenia: ");
		int podglad = EasyIn.getInt();
		if(podglad==1)	{
			CrudOperations.showBreeders();
		}
		if(podglad==2)	{
			CrudOperations.showColors();		
		}
		if(podglad==3)	{
			CrudOperations.showCountries();
		}
		if(podglad==4)	{
			CrudOperations.showHorses();
		}
		if(podglad==5)	{
			CrudOperations.showSex();
		}
	}
	public static void addData() throws ParseException	{
		System.out.println("Dodawanie danych.");
		System.out.println("1. Breeder");
		System.out.println("2. Color");
		System.out.println("3. Country");
		System.out.println("4. Horse");
		System.out.print("Wybierz tabele do powiekszenia: ");
		int podglad = EasyIn.getInt();
		if(podglad==1)	{
			System.out.print("Podaj godnosc: ");
			String godnosc = EasyIn.getString();
			System.out.print("Podaj id kraju: ");
			int kraj = EasyIn.getInt();
			CrudOperations.addBreeder(new Breeder(0, godnosc, Collections.countryID(kraj)));
		}
		if(podglad==2)	{
			System.out.print("Podaj dluga nazwe: ");
			String lnam = EasyIn.getString();
			System.out.print("Podaj krotka nazwe: ");
			String snam = EasyIn.getString();
			CrudOperations.addColor(new Color(0, lnam, snam));	
		}
		if(podglad==3)	{
			System.out.print("Podaj nazwe: ");
			String nazwa = EasyIn.getString();
			System.out.print("Podaj kod kraju: ");
			String kod = EasyIn.getString();
			CrudOperations.addCountry(new Country(0, nazwa, kod));
		}
		if(podglad==4)	{
			int matka, ojciec, bufor=0;
			System.out.print("Podaj imie: ");
			String godnosc = EasyIn.getString();
			System.out.print("Podaj id gatunku: ");
			int plec = EasyIn.getInt();
			System.out.print("Podaj id koloru: ");
			int kolor = EasyIn.getInt();
			System.out.print("Podaj date urodzenia (YYYY-MM-DD): ");
			String data = EasyIn.getString();
			System.out.print("Podaj czy tylko rok: ");
			int rok = EasyIn.getInt();
			do	{
				if(bufor>0)	{
					System.out.println("Podany kon nie jest klacza!");
				}
				System.out.print("Podaj id matki: ");
				matka = EasyIn.getInt();
				bufor++;
			} while(Collections.horseID(matka).getSex()!=Sex.MARE);
			bufor = 0;
			do	{	
				if(bufor>0)	{
					System.out.println("Podany kon nie jest klacza!");
				}
				System.out.print("Podaj id ojca: ");
				ojciec = EasyIn.getInt();
				bufor++;
			} while(Collections.horseID(ojciec).getSex()!=Sex.STALLION);
			bufor = 0;
			System.out.print("Podaj id wlasciciela: ");
			int wlasciciel = EasyIn.getInt();
			CrudOperations.addHorse(new Horse(0, godnosc, CrudOperations.getSex(plec), new DateOfBirth(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(data).getTime()), intToBool(rok)), 
					rok, Collections.colorID(kolor),Collections.horseID(matka), Collections.horseID(ojciec), 
					Collections.breederID(wlasciciel)));
		}
	}
	public static void updateData() throws ParseException	{
		System.out.println("Edycja danych.");
		System.out.println("1. Breeder");
		System.out.println("2. Color");
		System.out.println("3. Country");
		System.out.println("4. Horse");
		System.out.print("Wybierz tabele do edycji: ");
		int podglad = EasyIn.getInt();
		if(podglad==1)	{
			System.out.print("Podaj ID wpisu do edytowania: ");
			int id = EasyIn.getInt();
			System.out.print("Podaj godnosc: ");
			String godnosc = EasyIn.getString();
			System.out.print("Podaj id kraju: ");
			int kraj = EasyIn.getInt();
			CrudOperations.updateBreeder(new Breeder(0, godnosc, Collections.countryID(kraj)), id);
		}
		if(podglad==2)	{
			System.out.print("Podaj ID wpisu do edytowania: ");
			int id = EasyIn.getInt();
			System.out.print("Podaj dluga nazwe: ");
			String lnam = EasyIn.getString();
			System.out.print("Podaj krotka nazwe: ");
			String snam = EasyIn.getString();
			CrudOperations.updateColor(new Color(0, lnam, snam), id);		
		}
		if(podglad==3)	{
			System.out.print("Podaj ID wpisu do edytowania: ");
			int id = EasyIn.getInt();
			System.out.print("Podaj nazwe: ");
			String nazwa = EasyIn.getString();
			System.out.print("Podaj kod kraju: ");
			String kod = EasyIn.getString();
			CrudOperations.updateCountry(new Country(0, nazwa, kod), id);
		}
		if(podglad==4)	{
			System.out.print("Podaj ID wpisu do edytowania: ");
			int id = EasyIn.getInt();
			int matka, ojciec, bufor=0;
			System.out.print("Podaj imie: ");
			String godnosc = EasyIn.getString();
			System.out.print("Podaj id gatunku: ");
			int plec = EasyIn.getInt();
			System.out.print("Podaj id koloru: ");
			int kolor = EasyIn.getInt();
			System.out.print("Podaj date urodzenia (YYYY-MM-DD): ");
			String data = EasyIn.getString();
			System.out.print("Podaj czy tylko rok: ");
			int rok = EasyIn.getInt();
			do	{
				if(bufor>0)	{
					System.out.println("Podany kon nie jest klacza!");
				}
				System.out.print("Podaj id matki: ");
				matka = EasyIn.getInt();
				bufor++;
			} while(Collections.horseID(matka).getSex()!=Sex.MARE);
			bufor = 0;
			do	{	
				if(bufor>0)	{
					System.out.println("Podany kon nie jest klacza!");
				}
				System.out.print("Podaj id ojca: ");
				ojciec = EasyIn.getInt();
				bufor++;
			} while(Collections.horseID(ojciec).getSex()!=Sex.STALLION);
			bufor = 0;
			System.out.print("Podaj id wlasciciela: ");
			int wlasciciel = EasyIn.getInt();
			CrudOperations.updateHorse(new Horse(0, godnosc, CrudOperations.getSex(plec), new DateOfBirth(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(data).getTime()), intToBool(rok)), 
					rok, Collections.colorID(kolor),Collections.horseID(matka), Collections.horseID(ojciec), 
					Collections.breederID(wlasciciel)), id);
		}
	}
	public static void deleteData()	{
		System.out.println("Usuwanie danych");
		System.out.println("1. Breeder");
		System.out.println("2. Color");
		System.out.println("3. Country");
		System.out.println("4. Horse");
		System.out.print("Wybierz tabele do pomniejszenia: ");
		int podglad = EasyIn.getInt();
		if(podglad==1)	{
			System.out.print("Podaj id wpisu ktory chcesz usunac: ");
			int id = EasyIn.getInt();
			CrudOperations.deleteBreeder(id);
		}
		if(podglad==2)	{
			System.out.print("Podaj id wpisu ktory chcesz usunac: ");
			int id = EasyIn.getInt();
			CrudOperations.deleteColor(id);
		}
		if(podglad==3)	{
			System.out.print("Podaj id wpisu ktory chcesz usunac: ");
			int id = EasyIn.getInt();
			CrudOperations.deleteCountry(id);
		}
		if(podglad==4)	
			System.out.print("Podaj id wpisu ktory chcesz usunac: ");
			int id = EasyIn.getInt();{
			CrudOperations.deleteHorse(id);
		}
	}	
	
	public static void showAncestors(){
		System.out.print("Podaj ID konia, ktorego przodkow chcesz znalezc: ");
		int wybor = EasyIn.getInt();
		System.out.print("Podaj glebokosc drzewa do przeszukania (rodzice to drugie pokolenie): ");
		int deep = EasyIn.getInt();
		System.out.println("Imie: ");
		Family.getParents(Collections.horseID(wybor), deep, 1);
		PdfExport.exportAncestors(Collections.horseID(wybor), Collections.horseID(wybor).getName(), deep);
	}
	
	public static void showOffsprings()	{
		System.out.print("Podaj ID konia, ktorego potomkow chcesz znalezc: ");
		int wybor = EasyIn.getInt();
		System.out.println("Trwa przeszukiwanie bazy, prosze czekac...");
		Family.showHorseOffspring(Collections.horseID(wybor));
		PdfExport.exportOffsprings(Collections.horseID(wybor),Collections.horseID(wybor).getName());
	}
}
