package pokemon;

public class Jouet extends Item implements Utilisable, Modifiable {
    private int apportAppetit;
    private int apportLoyaute;

    public Jouet(String nom, int frequence, int nombreUtilisations, int apportAppetit, int apportLoyaute) {
        super(nom, frequence, nombreUtilisations);
        this.apportAppetit = apportAppetit;
        this.apportLoyaute = apportLoyaute;
    }

    @Override
    public Item genererMemeItem(boolean generer) {
        if (generer) {
            return new Jouet(this.nom, this.frequence, this.nombreUtilisations, this.apportAppetit, this.apportLoyaute);
        } else {
            return null;
        }
    }

    @Override
    public void modifier() {
        this.apportAppetit += 10;
        this.apportLoyaute += 5;
        this.resetUtilisationsRestantes();
        System.out.print("Felicitation, votre " + this.nom);
        this.nom = this.nom + " magique";
        System.out.println(" devient un " + this.nom);
    }

    @Override
    public void utiliser(Joueur joueur, int indexPokemon) {
        if (null != joueur) {
            if (indexPokemon >= 0 && indexPokemon < joueur.getPokemons().length && !joueur.getPokemons()[indexPokemon].equals(null)) {
                if (utilisationsRestantes > 0){
                    joueur.getPokemons()[indexPokemon].monterAppetit(apportAppetit);
                    joueur.getPokemons()[indexPokemon].monterLoyaute(apportLoyaute);
                    this.baisserUtilisationsRestantes(1);
                }
            }
        }
    }

    public int getApportAppetit() {
        return apportAppetit;
    }

    public int getApportLoyaute() {
        return apportLoyaute;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + apportLoyaute + ", " + apportAppetit;
    }
}
