package pokemon;

public class AttaqueBulle extends AttaqueSpeciale {

    public AttaqueBulle() {
        super("bulle", new String[]{"EAU"}, 40, 100, 30);
    }

    @Override
    public AttaqueBulle genererMemeAttaque(boolean generer) {
        if (generer) {
            return new AttaqueBulle();
        } else {
            return null;
        }
    }
}
