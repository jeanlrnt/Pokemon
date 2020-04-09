import java.util.Random;

public class AttaquePhysique extends Attaque {

	public AttaquePhysique(String nom, int puissance, int precision, int nombreRepetitions) {
		super(nom, Nourriture.tousLesTypesDePokemons, puissance, precision, nombreRepetitions);
	}
	
	@Override
	public void utiliserAttaque(Pokemon attaquant, Pokemon victime) {

		if (this.repetitionsRestantes > 0 && null != attaquant && null!= victime) {
			Random random = new Random();
			int aleatoireAttaquant = random.nextInt(attaquant.getNiveau() + 1);
			int aleatoireVictime = random.nextInt(victime.getNiveau() + 1);
			int precision = random.nextInt(101);
			
			if (attaquant.getAttaque()+aleatoireAttaquant > victime.getDefense() + aleatoireVictime && precision <= this.getPrecision()) {
				int dommage = random.nextInt(this.puissance);
				victime.blessure(dommage);
				this.baisserNombreRepetitions();
				System.out.println("Succes. Dommage au pokemon victime : " +dommage + " HP.");
			}
			
		}
	}

	@Override
	public boolean isCompatible(Pokemon pokemon) {

		if (null == pokemon) {
			return false;
		}
		
		return true;
	}

}
