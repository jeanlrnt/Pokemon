package pokemon;

public class AttaqueEnfer extends AttaqueSpeciale {

    public AttaqueEnfer() {
	super("enfer", new String[] {"FEU"}, 100, 50, 5);
    }

    @Override
    public AttaqueEnfer genererMemeAttaque(boolean generer) {
        if (generer) {
            return new AttaqueEnfer();
        } else {
            return null;
        }
    }
}
