package pokemon;

public class Nourriture extends Item implements Utilisable {
    protected int apport;
    protected String[] compatibilites;
    public static String[] tousLesTypesDePokemons = {"PLANTE", "POISON", "FEU", "DRAGON", "VOL", "EAU", "INSECTE", "NORMAL", "FONCE", "ELECTRIQUE", "TELEPATIQUE", "GLACE", "ACIER", "TERRE", "FEE", "COMBAT", "ROCHE", "FANTOME"};

    public Nourriture(String nom, int frequence, int apport, String[] compatibilites) {
        super(nom, frequence, 1);
        this.apport = apport;
        this.compatibilites = compatibilites;
    }

    @Override
    public Item genererMemeItem(boolean generer) {
        if (generer) {
            return new Nourriture(this.nom, this.frequence, this.apport, this.compatibilites);
        } else {
            return null;
        }
    }

    @Override
    public void utiliser(Joueur joueur, int indexPokemon) {
        if (null != joueur) {
            if (indexPokemon >= 0 && indexPokemon < joueur.getPokemons().length && joueur.getPokemons()[indexPokemon] != null) {
                if (this.isCompatible(joueur.getPokemons()[indexPokemon]) && utilisationsRestantes > 0){
                    joueur.getPokemons()[indexPokemon].baisserAppetit(apport);
                    this.baisserUtilisationsRestantes(1);
                    System.out.println(joueur.getPokemons()[indexPokemon].getNom() + " utilise " + this);
                    joueur.getProvisions()[joueur.trouverProvision(this)] = null;
                }
            }
        }
    }

    public boolean isCompatible(Pokemon pokemon) {
        boolean trouve = false;
        if (null != pokemon) {
            int i = 0;
            while (i < this.compatibilites.length && !trouve) {
                if (this.compatibilites[i].equals(pokemon.getType())) {
                    trouve = true;
                }
                i++;
            }

            if (!trouve) {
                System.out.println("Un pokemon de type " + pokemon.getType() + " n'est pas compatible avec une nourriture de type " + this.nom + ".");
            }
        }
        return trouve;
    }

    public int getApport() {
        return apport;
    }

    public String[] getCompatibilites() {
        return compatibilites;
    }

    @Override
    public String toString() {
        StringBuilder compatibilite = new StringBuilder("{");
        for (String type: compatibilites) {
            if (compatibilite.toString().equals("{") && type != null) {
                compatibilite.append(type);
            } else if (type != null) {
                compatibilite.append(", ").append(type);
            }
        }
        compatibilite.append("}");
        return super.toString() + ", " + apport + ", " + compatibilite;
    }
}
