package mpr.proj;

import java.util.Set;

import mpr.proj.pedigree.Horse;

public abstract class Family {
	//rekurencja
	public static void getParents(Horse horse, int deep, int tab)	{
		if(deep>0){
			if(horse.getDam()!=null)	{
				for(int i=1;i<=tab;i++)	{
					System.out.print("   ");
				}
				System.out.println("Matka: "+horse.getDam().getName());
				getParents(horse.getDam(), deep -1, tab+1);
			}
			else	{
				for(int i=1;i<=tab;i++)	{
					System.out.print("   ");
				}
				System.out.println("Matka: Nieznana");
			}
			if(horse.getSire()!=null)	{
				for(int i=1;i<=tab;i++)	{
					System.out.print("   ");
				}
				System.out.println("Ojciec: "+horse.getSire().getName());
				getParents(horse.getSire(), deep -1, tab+1);
			}
			else	{
				for(int i=1;i<=tab;i++)	{
					System.out.print("   ");
				}
				System.out.println("Ojciec: Nieznany");
			}
		}
	}
	
	public static void showHorseOffspring(Horse horse)	{
		System.out.println("Potomstwo konia "+horse.getName());
		System.out.println("Trwa przeszukiwanie bazy, prosze czekac...");
		Set<Horse> zbior = Collections.getHorseOffspring(horse.getID());
		for(Horse a: zbior)	{
			System.out.print(a.getName()+", ");
		}
	}
}
