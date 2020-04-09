package pokemon;

import java.io.*;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Pokedex {
    private Set<Integer> setPokemons;

    public Pokedex() {
        this.setPokemons = new TreeSet<>();
    }

    public void rencontrer(Pokemon pokemon) {
        if (pokemon != null) {
            if (!setPokemons.contains(pokemon.getNumeroPokedex())) {
                setPokemons.add(pokemon.getNumeroPokedex());
            }
        }
    }

    public void rencontrer(Pokemon[] pokemons) {
        for (Pokemon pokemon : pokemons) {
            rencontrer(pokemon);
        }
    }

    public void charger(String chemin) {
        try {
            FileReader lecteur = new FileReader(chemin);
            Scanner s = new Scanner(lecteur);
            while (!s.hasNext("END")) {
                int numero = s.nextInt();
                setPokemons.add(numero);
                if (s.hasNextLine()) s.nextLine();
            }
            System.out.println("Chargement du pokedex effectuée");
            lecteur.close();
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier n'existe pas encore, il sera bientot créé");

        } catch (IOException e) {
            System.out.println("On ne peut charger le fichier : " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void sauvegarder(String chemin) {
        try {
            FileWriter scribe = new FileWriter(chemin);
            PrintWriter afficheur = new PrintWriter(scribe);
            for (int numero : setPokemons) {
                afficheur.println(numero);
            }
            afficheur.print("END");
            System.out.println("Sauvegarde du pokedex effectuée");
            scribe.close();
            afficheur.close();
        } catch (IOException e) {
            System.out.println("On ne peut charger le fichier : " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    public Set<Integer> getSetPokemons() {
        return setPokemons;
    }
}
