package pokemon;

import java.util.Random;

public class AttaquePhysique extends Attaque {

    public AttaquePhysique(String nom, int puissance, int precision, int nombreRepetitions) {
        super(nom, nombreRepetitions, nombreRepetitions, nombreRepetitions);
    }

    @Override
    public void utiliserAttaque(Pokemon attaquant, Pokemon victime) {
        if (null != attaquant && null != victime) {
            if (this.repetitionsRestantes > 0) {
                Random random = new Random();
                double aleatoireAttaquant = random.nextInt(attaquant.getNiveau());
                double aleatoireVictime = random.nextInt(victime.getNiveau());
                if (attaquant.getAttaque() + aleatoireAttaquant > victime.getDefense() + aleatoireVictime / 2) {
                    int temp = random.nextInt(this.puissance);
                    victime.blessure(temp);
                    System.out.println(attaquant.getNom() + " utilise " + this.nom + " sur " + victime.getNom() + " et lui inflige " + temp + " degats");
                } else {
                    System.out.println(attaquant.getNom() + " rate son attaque..");
                }
                baisserNombreRepetitions();
            } else {
                System.out.println("La capacite " + this.nom + " n'a plus de PP, " + attaquant.getNom() + " rate son attaque..");
            }
        }
    }

    @Override
    public boolean isCompatible(Pokemon pokemon) {
        if (null == pokemon) {
            return false;
        } else {
            return true;
        }
    }

}
