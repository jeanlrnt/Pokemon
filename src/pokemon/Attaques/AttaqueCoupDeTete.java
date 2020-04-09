package pokemon.Attaques;

import pokemon.AttaquePhysique;

public class AttaqueCoupDeTete extends AttaquePhysique {

    public AttaqueCoupDeTete() {
        super("coup_de_tete", 70, 100, 15);
    }

    @Override
    public AttaqueCoupDeTete genererMemeAttaque(boolean generer) {
        if (generer) {
            return new AttaqueCoupDeTete();
        } else {
            return null;
        }
    }
}
