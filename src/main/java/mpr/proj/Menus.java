package mpr.proj;

public abstract class Menus {
	 	
	public static void mainMenu()	{
		System.out.println("Menu glowne:");
		System.out.println("1. Podejrzyj tabele");
		System.out.println("2. Dodaj dane");
		System.out.println("3. Edytuj dane");
		System.out.println("4. Usun dane");
		System.out.print("Podaj operacje do wykonania: ");
		int opis = EasyIn.getInt();
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
			DbOperations.showBreeders();
		}
		if(podglad==2)	{
			DbOperations.showColors();		
		}
		if(podglad==3)	{
			DbOperations.showCountries();
		}
		if(podglad==4)	{
			DbOperations.showHorses();
		}
		if(podglad==5)	{
			DbOperations.showSex();
		}
	}
	public static void addData()	{
		System.out.println("Dodawanie danych.");
		System.out.println("1. Breeder");
		System.out.println("2. Color");
		System.out.println("3. Country");
		System.out.println("4. Horse");
		System.out.println("5. Sex");
		System.out.print("Wybierz tabele do powiekszenia: ");
		int podglad = EasyIn.getInt();
		if(podglad==1)	{
			DbOperations.addBreeder();
		}
		if(podglad==2)	{
			DbOperations.addColor();	
		}
		if(podglad==3)	{
			DbOperations.addCountry();
		}
		if(podglad==4)	{
			DbOperations.addHorse();
		}
		if(podglad==5)	{
			DbOperations.addSex();
		}
	}
	public static void updateData()	{
		System.out.println("Edycja danych.");
		System.out.println("1. Breeder");
		System.out.println("2. Color");
		System.out.println("3. Country");
		System.out.println("4. Horse");
		System.out.println("5. Sex");
		System.out.print("Wybierz tabele do edycji: ");
		int podglad = EasyIn.getInt();
		if(podglad==1)	{
			DbOperations.updateBreeder();
		}
		if(podglad==2)	{
			DbOperations.updateColor();		
		}
		if(podglad==3)	{
			DbOperations.updateCountry();
		}
		if(podglad==4)	{
			DbOperations.updateHorse();
		}
		if(podglad==5)	{
			DbOperations.updateSex();
		}
	}
	public static void deleteData()	{
		System.out.println("Usuwanie danych");
		System.out.println("1. Breeder");
		System.out.println("2. Color");
		System.out.println("3. Country");
		System.out.println("4. Horse");
		System.out.println("5. Sex");
		System.out.print("Wybierz tabele do pomniejszenia: ");
		int podglad = EasyIn.getInt();
		if(podglad==1)	{
			DbOperations.deleteBreeder();
		}
		if(podglad==2)	{
			DbOperations.deleteColor();
		}
		if(podglad==3)	{
			DbOperations.deleteCountry();
		}
		if(podglad==4)	{
			DbOperations.deleteHorse();
		}
		if(podglad==5)	{
			DbOperations.deleteSex();
		}
	}	
}
