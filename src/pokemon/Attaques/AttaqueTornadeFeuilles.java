package pokemon.Attaques;

import pokemon.AttaqueSpeciale;

public class AttaqueTornadeFeuilles extends AttaqueSpeciale {

    public AttaqueTornadeFeuilles() {
	super("tornade_de_feuilles", new String[] {"PLANTE"}, 65, 90, 10);
    }

    @Override
    public AttaqueTornadeFeuilles genererMemeAttaque(boolean generer) {
        if (generer) {
            return new AttaqueTornadeFeuilles();
        } else {
            return null;
        }
    }
}
