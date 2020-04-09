package pokemon;

public class PotionMagique extends Nourriture {

    public PotionMagique(String nom, int frequence) {
        super(nom, frequence, 0, tousLesTypesDePokemons);
    }

    public PotionMagique() {
        this(null, 0);
    }

    @Override
    public Item genererMemeItem(boolean generer) {
        if (generer) {
            return new PotionMagique(this.nom, this.frequence);
        } else {
            return null;
        }
    }

    public void utiliser(Joueur joueur, int indexPokemon) {
        if (null != joueur) {
            if (indexPokemon >= 0 && indexPokemon < joueur.getPokemons().length && !joueur.getPokemons()[indexPokemon].equals(null)) {
                if (this.isCompatible(joueur.getPokemons()[indexPokemon]) && utilisationsRestantes > 0) {
                    joueur.getPokemons()[indexPokemon].miseANiveau();
                    this.baisserUtilisationsRestantes(1);
                }
            }
        }
    }
}
