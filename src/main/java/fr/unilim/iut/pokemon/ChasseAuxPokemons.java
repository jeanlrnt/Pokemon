public class ChasseAuxPokemons {

    public static void main(String[] args) {

        Pokemon piplup = new Pokemon("Piplup", "EAU", 5, true);
        Pokemon rowlet = new Pokemon("Rowlet", "PLANTE", 10, false);
        Pokemon totodile = new Pokemon("Totodile", "EAU", 8, true);

        piplup.direBonjour(args[0]);
        rowlet.direBonjour(args[0]);
        totodile.direBonjour(args[0]);
    }
}