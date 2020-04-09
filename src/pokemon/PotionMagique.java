public class PotionMagique extends Nourriture {

    public PotionMagique(String nom, int frequence) {
        super(0, nom, Nourriture.tousLesTypesDePokemons, frequence);
    }


    @Override
    public PotionMagique genererMemeNourriture(boolean generer) {
        if (generer) {
            return new PotionMagique(this.getNom(), this.getFrequence());
        } else {
            return null;
        }
    }

    @Override
    public void estMangee(Pokemon pokemon) {
        if (null != pokemon) {
            pokemon.miseANiveau();
        }
    }

    @Override
    public String toString() {
        return ("Potion Magique : " + super.toString());
    }
}
