package pokemon;

public class Gourmandise extends Nourriture {
    private int apportLoyaute;

    public Gourmandise(String nom, int frequence, int apport, String[] compatibilites, int apportLoyaute) {
        super(nom, frequence, apport, compatibilites);
        this.apportLoyaute = apportLoyaute;
    }

    @Override
    public Item genererMemeItem(boolean generer) {
        if (generer) {
            return new Gourmandise(this.nom, this.frequence, this.apport, this.compatibilites, this.apportLoyaute);
        } else {
            return null;
        }
    }

    @Override
    public void utiliser(Joueur joueur, int indexPokemon) {
        if (null != joueur) {
            if (indexPokemon >= 0 && indexPokemon < joueur.getPokemons().length && joueur.getPokemons()[indexPokemon] != null) {
                if (this.isCompatible(joueur.getPokemons()[indexPokemon]) && utilisationsRestantes > 0) {
                    joueur.getPokemons()[indexPokemon].baisserAppetit(this.apport);
                    if (null != joueur.getPokemons()[indexPokemon].getMonJoueur()) {
                        joueur.getPokemons()[indexPokemon].monterLoyaute(this.apportLoyaute);
                    }
                    this.baisserUtilisationsRestantes(1);
                }
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", " + apportLoyaute;
    }
}
