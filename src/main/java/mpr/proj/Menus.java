package mpr.proj;

public abstract class Menus {
	 	
	public static void mainMenu()	{
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
	public static void addData()	{
		System.out.println("Dodawanie danych.");
		System.out.println("1. Breeder");
		System.out.println("2. Color");
		System.out.println("3. Country");
		System.out.println("4. Horse");
		System.out.print("Wybierz tabele do powiekszenia: ");
		int podglad = EasyIn.getInt();
		if(podglad==1)	{
			CrudOperations.addBreeder();
		}
		if(podglad==2)	{
			CrudOperations.addColor();	
		}
		if(podglad==3)	{
			CrudOperations.addCountry();
		}
		if(podglad==4)	{
			CrudOperations.addHorse();
		}
	}
	public static void updateData()	{
		System.out.println("Edycja danych.");
		System.out.println("1. Breeder");
		System.out.println("2. Color");
		System.out.println("3. Country");
		System.out.println("4. Horse");
		System.out.print("Wybierz tabele do edycji: ");
		int podglad = EasyIn.getInt();
		if(podglad==1)	{
			CrudOperations.updateBreeder();
		}
		if(podglad==2)	{
			CrudOperations.updateColor();		
		}
		if(podglad==3)	{
			CrudOperations.updateCountry();
		}
		if(podglad==4)	{
			CrudOperations.updateHorse();
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
			CrudOperations.deleteBreeder();
		}
		if(podglad==2)	{
			CrudOperations.deleteColor();
		}
		if(podglad==3)	{
			CrudOperations.deleteCountry();
		}
		if(podglad==4)	{
			CrudOperations.deleteHorse();
		}
	}	
	
	public static void showAncestors(){
		System.out.print("Podaj ID konia, ktorego przodkow chcesz znalezc: ");
		int wybor = EasyIn.getInt();
		System.out.println("Imie: ");
		Family.getParents(Collections.horseID(wybor), 3, 1);
		PdfExport.exportAncestors(Collections.horseID(wybor), Collections.horseID(wybor).getName());
	}
	
	public static void showOffsprings()	{
		System.out.print("Podaj ID konia, ktorego potomkow chcesz znalezc: ");
		int wybor = EasyIn.getInt();
		System.out.println("Trwa przeszukiwanie bazy, prosze czekac...");
		Family.showHorseOffspring(Collections.horseID(wybor));
		PdfExport.exportOffsprings(Collections.horseID(wybor),Collections.horseID(wybor).getName());
	}
}
