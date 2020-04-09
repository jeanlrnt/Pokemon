package pokemon;

public class Joueur {
    private String nom;
    private String prenom;
    private int age;
    private Pokemon[] pokemons = new Pokemon[5];
    private Nourriture[] provisions;
    private Item[] sac;
    private Pokedex pokedex;

    public Joueur(String nom, String prenom, int age, Pokemon[] pokemons) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.pokemons = pokemons;
        this.provisions = new Nourriture[10];
        for (int i = 0; i < pokemons.length; i++) {
            if (null != this.pokemons[i]) {
                this.pokemons[i].setMonJoueur(this);
            }
        }
        this.sac = new Item[15];
        this.pokedex = new Pokedex();
        this.pokedex.rencontrer(pokemons);
    }

    public Joueur(String nom, String prenom, int age) {
        this(nom, prenom, age, new Pokemon[]{null, null, null, null, null});
    }


    public int trouverPokemon(Pokemon pokemon) {

        int iterateur = 0;

        while (iterateur < this.pokemons.length) {
            if (this.pokemons[iterateur] == pokemon) {
                return iterateur;
            }
            iterateur++;
        }
        return -1;
    }

    private int trouverProvision(Nourriture nourriture) {

        int iterateur = 0;

        while (iterateur < this.provisions.length) {
            if (this.provisions[iterateur] == nourriture) {
                return iterateur;
            }
            iterateur++;
        }
        return -1;
    }

    public void ajouterProvision(Nourriture nourriture) {
        int positionLibre = this.trouverProvision(null);
        if (positionLibre != -1) {
            this.provisions[positionLibre] = nourriture;
        } else {
            System.out.println("Vous n'avez plus de place pour cette nourriture.");
        }
    }

    public void nourrirPokemon(Pokemon pokemon, Nourriture nourriture) {
        if (pokemon == null || nourriture == null || this.trouverPokemon(pokemon) == -1 || this.trouverProvision(nourriture) == -1) {
            System.out.println("Parametres non valides.");
        } else {
            pokemon.utiliser(nourriture);
            this.provisions[this.trouverProvision(nourriture)] = null;
        }
    }

    public void afficherProvisions() {
        for (int i = 0; i < this.provisions.length; i++) {
            if (null != this.provisions[i]) {
                System.out.println(i + " : " + this.provisions[i]);
            }
        }
    }

    public void afficherPokemons() {
        int iter = 0;
        for (Pokemon pokemon : pokemons) {
            if (null != pokemon) {
                System.out.println("Index " + iter + " : " + pokemon);
                iter++;
            }
        }
    }

    public void caresserPokemon(Pokemon pokemon) {
        if (null != pokemon && this == pokemon.getMonJoueur()) {
            pokemon.monterLoyaute(1);
            if (pokemon.getLoyaute() == 100) {
                System.out.println(pokemon.getNom() + " dit : Oui, moi je t'aime aussi !");
            } else {
                System.out.println(pokemon.getNom() + " dit : Mmmm, ca sent bien ! Et sous mon oreille gauche ?");
            }
        } else {
            if (null == pokemon) {
                System.out.println("Il faut bien specifier un pokemon valide !");
            } else {
                System.out.println("Vous ne pouvez pas caresser le pokemon d'un autre joueur !");
            }
        }
    }

    public void capturerPokemon(Pokemon pokemon) {
        if (pokemon.getMonJoueur() != null) {
            System.out.println("Vous ne pouvez pas capturer le pokemon d'un autre joueur.");
        } else {
            int premierePlaceDisponible = this.trouverPokemon(null);
            if (premierePlaceDisponible != -1) {
                this.pokemons[premierePlaceDisponible] = pokemon;
                System.out.println("Ce pokemon devient la propriete du joueur " + this.nom);
                pokemon.setMonJoueur(this);
                pokemon.baisserLoyaute(100); // car la methode en question remet la valeur a zero si le resultat est negatif
                pokemon.baisserAppetit(100);
                pokemon.monterAppetit(10);
                rencontrer(pokemon);
            } else {
                System.out.println("Vous n'avez pas la place pour accueillir ce pokemon ! Vous devrez renoncer a un autre pokemon pour capturer celui-ci.");
            }
        }

    }

    public void libererPokemon(Pokemon pokemon) {
        int positionPokemon = this.trouverPokemon(pokemon);
        if (positionPokemon != -1) {
            this.pokemons[positionPokemon] = null;
            System.out.println("Ce pokemon n'est plus la propriete du joueur " + this.nom);
            pokemon.setMonJoueur(null);
            pokemon.setNomDonne(null);
            pokemon.baisserLoyaute(100); // car la methode en question remet la valeur a zero si le resultat est negative
            pokemon.baisserAppetit(100);
            pokemon.monterAppetit(10);
        } else {
            System.out.println("Vous n'etes pas le maitre de ce pokemon.");
        }
    }

    public void donnerNom(Pokemon pokemon, String nomDonne) {
        if (pokemon != null) {
            int positionPokemon = this.trouverPokemon(pokemon);
            if (positionPokemon != -1) {
                if (null == pokemon.getNomDonne()) {
                    pokemon.monterLoyaute(10);
                } else {
                    pokemon.baisserLoyaute(10);
                }
                pokemon.setNomDonne(nomDonne);
            } else {
                System.out.println("Vous ne pouvez pas nomme ce pokemon car vous n'etes pas son maitre !");
            }
        }
    }

    public void donnerItem(int indexPokemon, int indexItem) {
        if (indexItem >= 0 && indexItem < this.sac.length && indexPokemon >= 0 && indexPokemon < this.pokemons.length) {
            if (null != this.sac[indexItem] && null != this.pokemons[indexPokemon]) {
                if (this.sac[indexItem] instanceof Utilisable) {
                    Utilisable item = (Utilisable) this.sac[indexItem];
                    item.utiliser(this, indexPokemon);
                    if (this.sac[indexItem].getUtilisationsRestantes() == 0) {
                        this.sac[indexItem] = null;
                    }
                }
            }
        }
    }

    public void modifierItem(int indexChangeur, int indexAModifier) {
        if (indexAModifier >= 0 && indexAModifier < this.sac.length && indexChangeur >= 0 && indexChangeur < this.sac.length) {
            if (null != this.sac[indexAModifier] && null != this.pokemons[indexChangeur]) {
                if (this.sac[indexChangeur] instanceof ChangerItems && this.sac[indexAModifier] instanceof Modifiable) {
                    Modifiable itemAModifier = (Modifiable) this.sac[indexAModifier];
                    ChangerItems itemChangeur = (ChangerItems) this.sac[indexChangeur];
                    itemChangeur.utiliser(itemAModifier);
                    if (this.sac[indexChangeur].getUtilisationsRestantes() == 0) {
                        this.sac[indexChangeur] = null;
                    }
                }
            }
        }
    }

    public int trouverItem(Item item) {
        int iterateur = 0;

        for (Item objet: this.sac) {
            if (objet == item) {
                return iterateur;
            }
            iterateur++;
        }
        return -1;
    }

    public void ajouterItem(Item item) {
        int positionLibre = this.trouverItem(null);
        if (positionLibre != -1) {
            this.sac[positionLibre] = item;
            System.out.println(item.getNom() + " a ete ajoute a votre sac");
        } else {
            System.out.println("Il n'y a plus de places libre pour " + item.getNom());
        }
    }

    public void lacherItem(Item item) {
        int positionItem = this.trouverItem(item);
        if (positionItem != -1) {
            this.sac[positionItem] = null;
            System.out.println(item.getNom() + " a ete supprime de votre sac");
        } else {
            System.out.println("Il n'y a pas de " + item.getNom() + " dans votre sac");
        }
    }

    public void afficherSac() {
        for (Item item: this.sac) {
            if (item != null) {
                System.out.println(item);
            }
        }
    }

    public void rencontrer(Pokemon pokemon) {
        pokedex.rencontrer(pokemon);
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public int getAge() {
        return age;
    }

    public Pokemon[] getPokemons() {
        return this.pokemons;
    }

    public Nourriture[] getProvisions() {
        return this.provisions;
    }

    public Item[] getSac() {
        return sac;
    }

    public Pokedex getPokedex() {
        return pokedex;
    }

    public String toString() {
        return ("[ Nom : " + this.nom + "; prenom : " + this.prenom + "; age" + this.age + " ]");
    }

}

