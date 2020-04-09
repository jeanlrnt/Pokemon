public class Joueur {
    private String nom;
    private String prenom;
    private int age;
    private Pokemon[] pokemons = new Pokemon[5];


    public Joueur(String nom, String prenom, int age, Pokemon[] pokemons) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.pokemons = pokemons;
        for (int i = 0; i < pokemons.length; i++) {
            if (null != this.pokemons[i]) {
                this.pokemons[i].setMonJoueur(this);
            }

        }
    }

    public Joueur(String nom, String prenom, int age) {
        this(nom, prenom, age, new Pokemon[]{null, null, null, null, null});
    }


    private int trouverPokemon(Pokemon pokemon) {

        boolean pokemonTrouve = false;
        int iterateur = 0;

        while (iterateur < this.pokemons.length && !pokemonTrouve) {
            if (this.pokemons[iterateur] == pokemon) {
                return iterateur;
            }
            iterateur++;
        }
        return -1;
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
                pokemon.baisserLoyaute(100);
                pokemon.baisserAppetit(100);
                pokemon.monterAppetit(10);
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
            pokemon.baisserLoyaute(100);
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
                if (pokemon.getNomDonne().equals(null)) {
                    pokemon.monterLoyaute(10);
                } else {
                    pokemon.baisserLoyaute(10);
                }
                pokemon.setNomDonne(nomDonne);
            } else {
                System.out.println("Vous ne pouvez pas nommer ce pokemon car vous n'etes pas son maitre !");
            }
        }
    }


    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public int getAge() {
        return this.age;
    }

    public Pokemon[] getPokemons() {
        return this.pokemons;
    }

    public String toString() {
        return ("[ Nom : " + this.nom + "; prenom : " + this.prenom + "; age" + this.age + " ]");
    }


}
