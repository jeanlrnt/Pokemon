package pokemon;

public abstract class Attaque {
    protected String nom;
    protected String[] compatibilites;
    protected int puissance;
    protected int precision;
    protected int nombreRepetitions;
    protected int repetitionsRestantes;


    public Attaque(String nom, String[] compatibilites, int puissance, int precision, int nombreRepetitions) {
        this.nom = nom;
        this.compatibilites = compatibilites;
        this.puissance = puissance;
        this.precision = precision;
        this.nombreRepetitions = nombreRepetitions;
        this.repetitionsRestantes = nombreRepetitions;
    }


    public Attaque(String nom, int puissance, int precision, int nombreRepetitions) {
        this(nom, Nourriture.tousLesTypesDePokemons, puissance, precision, nombreRepetitions);
    }


    public void resetNombreRepetitions() {
        this.repetitionsRestantes = this.nombreRepetitions;
    }

    public void baisserNombreRepetitions() {
        this.repetitionsRestantes--;
        if (this.repetitionsRestantes < 0) {
            this.repetitionsRestantes = 0;
        }
    }

    public abstract void utiliserAttaque(Pokemon attaquant, Pokemon victime);

    public abstract boolean isCompatible(Pokemon pokemon);

    public abstract Attaque genererMemeAttaque(boolean generer);


    public String getNom() {
        return nom;
    }


    public String[] getCompatibilites() {
        return compatibilites;
    }


    public int getPuissance() {
        return puissance;
    }


    public int getPrecision() {
        return precision;
    }


    public int getNombreRepetitions() {
        return nombreRepetitions;
    }


    public int getRepetitionsRestantes() {
        return repetitionsRestantes;
    }


    @Override
    public String toString() {
        String compatibilite = "{";
        for (String type: compatibilites) {
            if (compatibilite == "{" && type != null) {
                compatibilite += type;
            } else if (type != null) {
                compatibilite += ", " + type;
            }
        }
        compatibilite += "}";
        return nom + " : " + puissance + ", " + precision + ", " + repetitionsRestantes + "/"
                + nombreRepetitions + ", " + compatibilite;
    }

}
