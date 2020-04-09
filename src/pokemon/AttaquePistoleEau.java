package pokemon;

public class AttaquePistoleEau extends AttaqueSpeciale {

    public AttaquePistoleEau() {
	super("pistolet_a_eau", new String[] {"EAU"}, 40, 100, 25);
    }

    @Override
    public AttaquePistoleEau genererMemeAttaque(boolean generer) {
        if (generer) {
            return new AttaquePistoleEau();
        } else {
            return null;
        }
    }
}
