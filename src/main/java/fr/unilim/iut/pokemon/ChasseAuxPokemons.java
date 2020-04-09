public class ChasseAuxPokemons {

    public static void main(String[] args) {

        final Pokemon piplup = new Pokemon("Piplup", "EAU", 5, true);
        final Pokemon rowlet = new Pokemon("Rowlet", "PLANTE", 10, false);
        final Pokemon totodile = new Pokemon("Totodile", "EAU", 8, true);


        final Joueur jeanDupont = new Joueur("Dupont", "Jean", 14);

        final Nourriture tartiflette = new Nourriture(35, "tartiflette", new String[]{"DRAGON", "FEU", "COMBAT", "NORMAL", "EAU", "ELECTRIQUE"}, 20);
        final Nourriture ratatouille = new Nourriture(10, "ratatouille", new String[]{"PLANTE", "EAU", "VOL", "FEU", "NORMAL", "ELECTRIQUE", "COMBAT"}, 50);


        final Nourriture[] diversesNourritures = new Nourriture[]{tartiflette, ratatouille};


        final Gourmandise barreChocolatee = new Gourmandise(20, "Barre Chocolatee", new String[]{"DRAGON", "FEU", "COMBAT", "EAU", "ELECTRIQUE"}, 10, 7);
        final PotionMagique mojito = new PotionMagique("mojito", 2);

        // Affichage exos 2 et 3
        //System.out.println(barreChocolatee);
        //System.out.println(mojito);

        jeanDupont.capturerPokemon(piplup);
        System.out.println(piplup);

        final Nourriture[] tableauDeNourriture = new Nourriture[]{tartiflette, ratatouille, barreChocolatee, mojito};
        for (int i = 0; i < tableauDeNourriture.length; i++) {
            piplup.manger(tableauDeNourriture[i]);
            System.out.println(piplup);
            System.out.println(tableauDeNourriture[i].genererMemeNourriture(true));
        }

		/*
		double alea;
		int nombreDEssais = Integer.valueOf(args[0]);
		boolean generer;
		Nourriture[] nourritureGeneree = new Nourriture[tableauNourriture.length];

		for (int i = 0; i<nombreDEssais; i++) {
			alea = Math.random()*100;
			System.out.println(alea);
			for (int j = 0; j<tableauNourriture.length; j++) {
				if (alea < tableauNourriture[j].getFrequence()) {
					generer = true;
				} else {
					generer = false;
				}
				nourritureGeneree[j] = tableauNourriture[j].genererMemeNourriture(generer);
				System.out.println("i = " +i + "; j = " + j + "; " + nourritureGeneree[j]);
			}
		}
		*/

    }

}
