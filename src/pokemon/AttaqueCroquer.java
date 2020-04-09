package pokemon;

public class AttaqueCroquer extends AttaquePhysique {

    public AttaqueCroquer() {
        super("croquer", 80, 100, 15);
    }

    @Override
    public AttaqueCroquer genererMemeAttaque(boolean generer) {
        if (generer) {
            return new AttaqueCroquer();
        } else {
            return null;
        }
    }
}
