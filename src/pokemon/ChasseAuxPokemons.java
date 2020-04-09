package pokemon;

import java.util.Scanner;

public class ChasseAuxPokemons {

    public static void main(String[] args) {

        final Pokemon piplup = new Pokemon("Piplup", "EAU", 10, true, 51, 53, 61, 56, new Attaque[]{new AttaqueMorsure(), new AttaquePistoleEau(), new AttaqueEnfer(), null});
        final Pokemon rowlet = new Pokemon("Rowlet", "PLANTE", 10, false, 55, 55, 50, 50, new Attaque[]{new AttaqueMorsure(), new AttaqueTornadeFeuilles(), new AttaqueFeinte(), null});
        final Pokemon totodile = new Pokemon("Totodile", "EAU", 8, true, 65, 64, 44, 48, new Attaque[]{new AttaqueMorsure(), new AttaqueCoupDeTete(), new AttaqueCroquer(), null});

        final Nourriture tartiflette = new Nourriture("tartiflette", 35, 20, new String[]{"DRAGON", "FEU", "COMBAT", "NORMAL", "EAU", "ELECTRIQUE"});
        final Nourriture ratatouille = new Nourriture("ratatouille", 10, 50, new String[]{"PLANTE", "EAU", "VOL", "FEU", "NORMAL", "ELECTRIQUE", "COMBAT"});
        final Gourmandise barreChocolatee = new Gourmandise("Barre Chocolatee", 20, 10, new String[]{"DRAGON", "FEU", "COMBAT", "EAU", "ELECTRIQUE"}, 7);
        final PotionMagique mojito = new PotionMagique("mojito", 2);
        final Jouet balle = new Jouet("balle", 20, 10,10, 5);
        final Outil marteau = new Outil("Le Petit Marteau des Merveilles", 10, 2);


        final Item[] tableauDeNourriture = new Nourriture[]{tartiflette, ratatouille, barreChocolatee, mojito};


        final Joueur moi = new Joueur("Laurent", "Jean", 20, new Pokemon[]{piplup, rowlet, totodile});


        Scanner scanner = new Scanner(System.in);

        double alea;
        boolean generer = false;
        int reponseEntiere;
        Item[] nourritureGeneree = new Nourriture[tableauDeNourriture.length];
        String reponse = "ok";
        while (!reponse.equals("stop")) {
            System.out.println("1. Afficher les Pokemons \n2. Caresser un pokemon \n3. Regader le sac a Provisions \n4. Nourrir un pokemon\n5. Chasse a la nourriture\n6. main\n7. Fight\n Quel est votre choix? ");
            reponse = scanner.next();
            switch (reponse) {
                case "1":
                    moi.afficherPokemons();
                    break;
                case "2":
                    System.out.println("Quel pokemon voulez-vous caresser ? Donnez son index entre 0 et " + (moi.getPokemons().length - 1));
                    reponseEntiere = scanner.nextInt();
                    if (reponseEntiere >= 0 && reponseEntiere < moi.getPokemons().length) {
                        moi.caresserPokemon(moi.getPokemons()[reponseEntiere]);
                    } else {
                        System.out.println("Mauvaise valeur. Vous perdez votre tour.");
                    }
                    break;
                case "3":
                    moi.afficherProvisions();
                    break;
                case "4":
                    System.out.println("Quel pokemon voulez-vous nourrir ? Donnez son index entre 0 et " + (moi.getPokemons().length - 1));
                    reponseEntiere = scanner.nextInt();
                    if (reponseEntiere >= 0 && reponseEntiere < moi.getPokemons().length) {
                        System.out.println("Quelle provision voulez-vous utiliser ? Donnez son index entre 0 et " + (moi.getProvisions().length - 1));
                        int deuxiemeReponse = scanner.nextInt();
                        if (deuxiemeReponse >= 0 && deuxiemeReponse < moi.getProvisions().length) {
                            moi.nourrirPokemon(moi.getPokemons()[reponseEntiere], moi.getProvisions()[deuxiemeReponse]);
                        } else {
                            System.out.println("Mauvaise valeur. Vous perdez votre tour.");
                        }
                    }
                    break;
                case "5":
                    String num2 = "ok";
                    while (!num2.equals("stop")) {
                        alea = Math.random() * 100;
                        for (int j = 0; j < tableauDeNourriture.length; j++) {
                            if (alea < tableauDeNourriture[j].getFrequence()) {
                                generer = true;
                            } else {
                                generer = false;
                            }
                            nourritureGeneree[j] = tableauDeNourriture[j].genererMemeItem(generer);
                            if (generer) {
                                System.out.println("Vous avez trouve un.e/du/de la " + tableauDeNourriture[j].getNom() + ". Voulez-vous la prendre ? Ecrivez oui si c'est le cas.");
                                reponse = scanner.next();
                                if (reponse.equals("oui")) {
                                    moi.ajouterProvision((Nourriture) nourritureGeneree[j]);
                                }
                            }
                        }
                        System.out.println("Voulez vous continer ? ");
                        num2 = scanner.next();
                    }
                    System.out.println("Encore?");
                    break;
                case "6":
                    moi.ajouterItem(marteau);
                    moi.ajouterItem(balle);
                    moi.afficherSac();
                    moi.donnerItem(0,1);
                    moi.afficherSac();
                    moi.modifierItem(0, 1);
                    moi.afficherSac();
                    break;
                case "7":
                    int j = 0;
                    int k;
                    while (!piplup.etreEvanoui() || !rowlet.etreEvanoui()) {
                        System.out.println("Tour " + (j + 1) + " : ");
                        System.out.println(piplup.getNom() + " : " + piplup.getHp() + " hp");
                        System.out.println(rowlet.getNom() + " : " + rowlet.getHp() + " hp\n");
                        for (int i = 0; i < 2; i++) {
                            if (i == 0) {
                                k = 1;
                            } else {
                                k = 0;
                            }
                            moi.getPokemons()[i].afficherAttaques();
                            System.out.println("Quelle attaque voulez vous utiliser ?");
                            moi.getPokemons()[i].utiliserAttaque(scanner.nextInt(), moi.getPokemons()[k]);
                        }
                    }
                    for (int i = 0; i < 2; i++) {
                        moi.getPokemons()[i].rechargerAttaques();
                    }
                    break;
                case "stop":
                    reponse = "stop";
                    break;
                default:
                    System.out.println("mauvaise valeur");
                    break;
            }

        }
        scanner.close();

    }

}
