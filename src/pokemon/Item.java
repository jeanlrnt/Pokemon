package pokemon;

public abstract class Item {
    protected String nom;
    protected int frequence;
    protected int nombreUtilisations;
    protected int utilisationsRestantes;

    public Item(String nom, int frequence, int nombreUtilisations) {
        this.nom = nom;
        this.frequence = frequence;
        this.nombreUtilisations = nombreUtilisations;
        this.utilisationsRestantes = nombreUtilisations;
    }

    public void monterUtilisationsRestantes(int difference) {
        this.utilisationsRestantes += difference;
        if (utilisationsRestantes > nombreUtilisations) {
            utilisationsRestantes = nombreUtilisations;
        }
    }

    public void baisserUtilisationsRestantes(int difference) {
        this.utilisationsRestantes -= difference;
        if (utilisationsRestantes < 0) {
            utilisationsRestantes = 0;
        }
    }

    public void resetUtilisationsRestantes() {
        this.utilisationsRestantes = nombreUtilisations;
    }

    public abstract Item genererMemeItem(boolean generer);

    public String getNom() {
        return nom;
    }

    public int getFrequence() {
        return frequence;
    }

    public int getNombreUtilisations() {
        return nombreUtilisations;
    }

    public int getUtilisationsRestantes() {
        return utilisationsRestantes;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom + " : " + frequence + ", " + utilisationsRestantes + "/" + nombreUtilisations;
    }
}
