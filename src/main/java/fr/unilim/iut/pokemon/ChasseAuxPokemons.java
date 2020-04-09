import java.lang.Math;

public class ChasseAuxPokemons {

	public static void main(String[] args) {

		final Pokemon piplup = new Pokemon("Piplup", "EAU", 5, true);
		final Pokemon rowlet = new Pokemon("Rowlet", "PLANTE", 10, false);
		final Pokemon totodile = new Pokemon("Totodile", "EAU", 8, true);


		final Joueur jeanDupont = new Joueur("Dupont", "Jean", 14);

		final Nourriture tartiflette = new Nourriture(35, "tartiflette", new String[]{"DRAGON", "FEU", "COMBAT", "NORMAL", "EAU", "ELECTRIQUE"}, 20);
		final Nourriture ratatouille = new Nourriture(10, "ratatouille", new String[]{"PLANTE","EAU","VOL","FEU","NORMAL","ELECTRIQUE","COMBAT"}, 50);


		final Nourriture[] diversesNourritures = new Nourriture[] {tartiflette, ratatouille};
		for (int i = 0; i < diversesNourritures.length; i++) {
			System.out.println(diversesNourritures[i]);
			System.out.println("Compatible avec Piplup : " +diversesNourritures[i].isCompatible(piplup));
			System.out.println("Compatible avec Rowlet : " +diversesNourritures[i].isCompatible(rowlet));
		}


		// generer un nombre al�atoire

		double alea;
		int nombreDEssais = Integer.valueOf(args[0]);
		boolean generer;
		Nourriture[] nourritureGeneree = new Nourriture[diversesNourritures.length];

		for (int i = 0; i<nombreDEssais; i++) {
			alea = Math.random()*100;
			System.out.println(alea);
			for (int j = 0; j<diversesNourritures.length; j++) {
				if (alea < diversesNourritures[j].getFrequence()) {
					generer = true;
				}
				else {
					generer = false;
				}
				nourritureGeneree[j] = diversesNourritures[j].genererMemeNourriture(generer);
				System.out.println("i = " +i + "; j = " + j + "; " + nourritureGeneree[j]);
			}
		}


	}

}
