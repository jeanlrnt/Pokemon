public class ChasseAuxPokemons {

    public static void main(String[] args) {

        Pokemon piplup = new Pokemon("Piplup", "EAU", 5, true);
        Pokemon rowlet = new Pokemon("Rowlet", "PLANTE", 10, false);
        Pokemon totodile = new Pokemon("Totodile", "EAU", 8, true);

        System.out.println(piplup);
        piplup.sePresenter();
        System.out.println(rowlet);
        System.out.println(totodile);
        Joueur sacha = new Joueur("laurent", "jean", 20);
        sacha.capturerPokemon(piplup);
        piplup.sePresenter();
    }
}