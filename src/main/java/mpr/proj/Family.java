package mpr.proj;

import java.util.Set;

import mpr.proj.pedigree.Horse;

public abstract class Family {
	
	public static void showHorseFamily(Horse horse)	{
		System.out.println("Imie: "+horse.getName());
		System.out.println(" -> Matka:  "+horse.getDam().getName());
		System.out.println("    -> Babcia:  "+horse.getDam().getDam().getName());
		System.out.println("    -> Dziadek: "+horse.getDam().getSire().getName());
		System.out.println(" -> Ojciec: "+horse.getSire().getName());
		System.out.println("    -> Babcia:  "+horse.getSire().getDam().getName());
		System.out.println("    -> Dziadek: "+horse.getSire().getSire().getName());
	}

	public static void showHorseOffspring(Horse horse)	{
		System.out.println("Potomstwo konia "+horse.getName());
		Set<Horse> zbior = Collections.getHorseOffspring(horse.getID());
		for(Horse a: zbior)	{
			System.out.print(a.getName()+", ");
		}
	}
}
