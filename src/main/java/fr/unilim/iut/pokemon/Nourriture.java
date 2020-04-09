public class Nourriture {
    protected int apport;
    protected String nom;
    protected String[] compatibilites;
    protected int frequence;
    protected static String[] tousLesTypesDePokemons = {"PLANTE", "POISON", "FEU", "DRAGON", "VOL", "EAU", "INSECTE", "NORMAL", "FONCE", "ELECTRIQUE", "TELEPATIQUE", "GLACE", "ACIER", "TERRE", "FEE", "COMBAT", "ROCHE", "FANTOME"};

    public Nourriture(int apport, String nom, String[] compatibilites, int frequence) {
        this.apport = apport;
        this.nom = nom;
        this.compatibilites = compatibilites;
        this.frequence = frequence;
    }

    public boolean isCompatible(Pokemon pokemon) {
        boolean trouve = false;
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
        return trouve;
    }


    public Nourriture genererMemeNourriture(boolean generer) {
        if (generer) {
            return new Nourriture(this.apport, this.nom, this.compatibilites, this.frequence);
        } else {
            return null;
        }
    }

    public void estMangee(Pokemon pokemon) {
        if (null != pokemon) {
            pokemon.baisserAppetit(this.apport);
        }
    }

    public int getApport() {
        return this.apport;
    }

    public String getNom() {
        return this.nom;
    }

    public String[] getCompatibilites() {
        return this.compatibilites;
    }

    public int getFrequence() {
        return this.frequence;
    }

    public String toString() {
        String compatibilites = "{";
        for (int i = 0; i < this.compatibilites.length - 1; i++) {
            compatibilites += this.compatibilites[i] + ", ";
        }
        compatibilites += this.compatibilites[this.compatibilites.length - 1];
        return (this.nom + "; " + this.apport + "; " + this.frequence + "/100; " + compatibilites + "}");
    }

}
