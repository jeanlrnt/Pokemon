package pokemon;

import java.util.Random;

public class AttaqueSpeciale extends Attaque {

    public AttaqueSpeciale(String nom, String[] compatibilites, int puissance, int precision, int nombreRepetitions) {
        super(nom, compatibilites, puissance, precision, nombreRepetitions);
    }


    @Override
    public void utiliserAttaque(Pokemon attaquant, Pokemon victime) {
        if (null != attaquant && null != victime) {
            if (repetitionsRestantes > 0) {
                Random random = new Random();
                double aleatoireAttaquant = random.nextInt(attaquant.getNiveau());
                double aleatoireVictime = random.nextInt(victime.getNiveau());
                if (attaquant.getAttaqueSpeciale() + aleatoireAttaquant > victime.getDefenseSpeciale() + aleatoireVictime / 2) {
                    int temp = random.nextInt(this.puissance);
                    victime.blessure(temp);
                    System.out.println(attaquant.getNom() + " utilise " + this.nom + " sur " + victime.getNom() + " et lui inflige " + temp + " degats");
                } else {
                    System.out.println(attaquant.getNom() + " rate son attaque");
                }
                baisserNombreRepetitions();
            } else {
                System.out.println("La capacite " + this.nom + " n'a plus de PP, " + attaquant.getNom() + " rate son attaque..");
            }
        }
    }

    @Override
    public boolean isCompatible(Pokemon pokemon) {
        boolean trouve = false;
        if (null != pokemon) {
            int i = 0;
            while (i < this.compatibilites.length && !trouve) {
                if (this.compatibilites[i].equals(pokemon.getType())) {
                    trouve = true;
                }
                i++;
            }
        }
        return trouve;
    }

}
