public class Gourmandise extends Nourriture {
    private int apportLoyaute;

    public Gourmandise(int apport, String nom, String[] compatibilites, int frequence, int apportLoyaute) {
        super(apport, nom, compatibilites, frequence);
        this.apportLoyaute = apportLoyaute;
    }

    @Override
    public Gourmandise genererMemeNourriture(boolean generer) {
        if (generer) {
            return new Gourmandise(this.getApport(), this.getNom(), this.getCompatibilites(), this.getFrequence(), this.apportLoyaute);
        } else {
            return null;
        }
    }


    @Override
    public void estMangee(Pokemon pokemon) {
        if (null != pokemon) {
            pokemon.baisserAppetit(this.getApport());
            if (null != pokemon.getMonJoueur()) {
                pokemon.monterLoyaute(this.apportLoyaute);
            }

        }
    }

    public int getApportLoyaute() {
        return this.apportLoyaute;
    }


    @Override
    public String toString() {
        return "Gourmandise : " + this.apportLoyaute + "; " + super.toString();
    }
}
