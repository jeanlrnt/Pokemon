package pokemon;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import pokemon.Attaques.*;

public class ChasseAuxPokemons {

    public static void main(String[] args) throws FileNotFoundException {

        HashMap<String, Integer> mapPokemon = new HashMap<String, Integer>();

        try {
            FileReader lecteur = new FileReader("src/tp10/Data/pokedexComplet.txt");
            Scanner s = new Scanner(lecteur);
            while (!s.hasNext("END")) {
                int numero = s.nextInt();
                String nom = s.next();
                mapPokemon.put(nom, numero);
                if (s.hasNextLine()) s.nextLine();
            }
            lecteur.close();
            System.out.println(mapPokemon.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<String, Attaque> mappeAttaques = new HashMap<String, Attaque>();
        mappeAttaques.put("pistolet_a_eau", new AttaquePistoleEau());
        mappeAttaques.put("bulle", new AttaqueBulle());
        mappeAttaques.put("croquer", new AttaqueCroquer());
        mappeAttaques.put("enfer", new AttaqueEnfer());
        mappeAttaques.put("morsure", new AttaqueMorsure());
        mappeAttaques.put("feinte", new AttaqueFeinte());
        mappeAttaques.put("tornade_de_feuilles", new AttaqueTornadeFeuilles());
        mappeAttaques.put("coup_de_tete", new AttaqueCoupDeTete());

        ArrayList<Pokemon> pokemonsUtilises = new ArrayList<Pokemon>();

        try {
            FileReader lecteur = new FileReader("src/tp10/Data/PokemonData.txt");
            Scanner s = new Scanner(lecteur);
            while (s.hasNextLine()) {
                String nom = s.next();
                String type = s.next();
                int level = s.nextInt();
                boolean diurne = s.nextBoolean();
                int attaque = s.nextInt();
                int defense = s.nextInt();
                int attaqueSpe = s.nextInt();
                int defenseSpe = s.nextInt();
                Attaque[] attaques = new Attaque[4];
                for (int i = 0; i < 4; i++) {
                    if (i < 4) {
                        attaques[i] = mappeAttaques.get(s.next());
                    }
                }
                pokemonsUtilises.add(new Pokemon(mapPokemon.get(nom), nom, type, level, diurne, attaque, defense, attaqueSpe, defenseSpe, attaques));
                s.nextLine();
            }
            lecteur.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Item> itemsUtilises = new ArrayList<Item>();

        try {
            FileReader lecteur = new FileReader("src/tp10/Data/ItemData.txt");
            Scanner s = new Scanner(lecteur);
            while (s.hasNextLine()) {
                String classe = s.next();
                String nom;
                String END;
                int frequence;
                int apport;
                int apportLoyaute;
                int nombreUtilisations;
                int apportAppetit;
                String[] compatibilite;
                int i = 0;
                switch (classe) {
                    case "nourriture":
                        nom = s.next();
                        frequence = s.nextInt();
                        apport = s.nextInt();
                        compatibilite = new String[18];
                        i = 0;
                        while (!s.hasNext("END")) {
                            compatibilite[i] = s.next();
                            i++;
                        }
                        itemsUtilises.add(new Nourriture(nom, frequence, apport, compatibilite));
                        break;
                    case "gourmandise":
                        nom = s.next();
                        frequence = s.nextInt();
                        apport = s.nextInt();
                        compatibilite = new String[18];
                        i = 0;
                        while (!s.hasNext("END")) {
                            compatibilite[i] = s.next();
                            i++;
                        }
                        END = s.next();
                        apportLoyaute = s.nextInt();
                        itemsUtilises.add(new Gourmandise(nom, frequence, apport, compatibilite, apportLoyaute));
                        break;
                    case "potion_magique":
                        nom = s.next();
                        frequence = s.nextInt();
                        itemsUtilises.add(new PotionMagique(nom, frequence));
                        break;
                    case "jouet":
                        nom = s.next();
                        frequence = s.nextInt();
                        nombreUtilisations = s.nextInt();
                        apportAppetit = s.nextInt();
                        apportLoyaute = s.nextInt();
                        itemsUtilises.add(new Jouet(nom, frequence, nombreUtilisations, apportAppetit, apportLoyaute));
                        break;
                    case "outil":
                        nom = s.next();
                        frequence = s.nextInt();
                        nombreUtilisations = s.nextInt();
                        itemsUtilises.add(new Outil(nom, frequence, nombreUtilisations));
                        break;
                    default:
                        break;
                }
                if (s.hasNextLine()) s.nextLine();
            }
            lecteur.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        final Joueur moi = new Joueur("Laurent", "Jean", 20, new Pokemon[]{pokemonsUtilises.get(0), pokemonsUtilises.get(1), pokemonsUtilises.get(2)});

        for (Item item : itemsUtilises) {
            moi.ajouterItem(item);
        }

        try {
            moi.getPokedex().charger("src/tp10/Data/pokedexMoi.txt");
        } catch (InputMismatchException e) {
            System.out.println("On ne peut charger le fichier : " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        Scanner scanner = new Scanner(System.in);

        double alea;
        boolean generer;
        int reponseEntiere;
        ArrayList<Item> nourritureGeneree = new ArrayList<Item>();
        String reponse = "ok";
        while (!reponse.equals("stop")) {
            System.out.println("1. Afficher les Pokemons \n2. Caresser un pokemon \n3. Regader le sac a Provisions \n4. Nourrir un pokemon\n5. Chasse a la nourriture\n6. main\n7. Fight\n8. Afficher le sac\n Quel est votre choix? ");
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
                        for (int j = 0; j < itemsUtilises.size(); j++) {
                            generer = alea < itemsUtilises.get(j).getFrequence();
                            nourritureGeneree.add(itemsUtilises.get(j).genererMemeItem(generer));
                            if (generer) {
                                System.out.println("Vous avez trouve un.e/du/de la " + itemsUtilises.get(j).getNom() + ". Voulez-vous la prendre ? Ecrivez oui si c'est le cas.");
                                reponse = scanner.next();
                                if (reponse.equals("oui")) {
                                    moi.ajouterProvision((Nourriture) nourritureGeneree.get(j));
                                }
                            }
                        }
                        System.out.println("Voulez vous continer ? ");
                        num2 = scanner.next();
                    }
                    System.out.println("Encore?");
                    break;
                case "6":
                    moi.ajouterItem(itemsUtilises.get(6));
                    moi.ajouterItem(itemsUtilises.get(5));
                    moi.afficherSac();
                    moi.donnerItem(0, 1);
                    moi.afficherSac();
                    moi.modifierItem(0, 1);
                    moi.afficherSac();
                    break;
                case "7":
                    int j = 0;
                    int k;
                    while (!moi.getPokemons()[0].etreEvanoui() || !moi.getPokemons()[1].etreEvanoui()) {
                        System.out.println("Tour " + (j + 1) + " : ");
                        System.out.println(moi.getPokemons()[0].getNom() + " : " + moi.getPokemons()[0].getHp() + " hp");
                        System.out.println(moi.getPokemons()[1].getNom() + " : " + moi.getPokemons()[1].getHp() + " hp\n");
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
                case "8":
                    moi.afficherSac();
                    break;
                case "9" :
                    System.out.println(moi.getPokedex().getSetPokemons().toString());
                    break;
                case "stop":
                    reponse = "stop";
                    break;
                default:
                    System.out.println("mauvaise valeur");
                    break;
            }

        }

        moi.getPokedex().sauvegarder("src/tp10/Data/pokedexMoi.txt");
        scanner.close();

    }

}
