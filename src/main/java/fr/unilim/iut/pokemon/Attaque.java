package tp6;

public abstract class Attaque {
	protected String nom;
	protected String[] compatibilites;
	protected int puissance;
	protected int precision;
	protected int nombreRepetitions;
	protected int repetitionsRestantes;
	
	public Attaque(String nom, String[] compatibilites, int puissance, int precision, int nombreRepetitions) {
		this.nom = nom;
		this.compatibilites = compatibilites;
		this.puissance = puissance;
		this.precision = precision;
		this.nombreRepetitions = nombreRepetitions;
		this.repetitionsRestantes = nombreRepetitions;
	}
	
	public Attaque(String nom, int puissance, int precision, int nombreRepetitions) {
		this(nom, Nourriture.tousLesTypesDePokemons, puissance, precision, nombreRepetitions);
	}
	
	public void resetNombreRepetitions() {
		this.repetitionsRestantes = this.nombreRepetitions;
	}
	
	public void baisserNombreRepetitions() {
		this.repetitionsRestantes -= 1;
		if(this.repetitionsRestantes < 0) {
			this.repetitionsRestantes = 0;
		}
	}
	
	public abstract void utiliserAttaque(Pokemon attaquant, Pokemon victime);
	
	public abstract boolean isCompatible(Pokemon pokemon);
	
	public String getNom() {
		return this.nom;
	}
	
	public String[] getCompatibilites() {
		return this.compatibilites;
	}
	
	public int getPuissance() {
		return this.puissance;
	}
	
	public int getPrecision() {
		return this.precision;
	}
	
	public int getNombreRepetitions() {
		return this.nombreRepetitions;
	}
	
	public int getRepetitionsRestantes() {
		return this.repetitionsRestantes;
	}
	
	public String toString() {
		String compatibilites = "{";
		for(int i = 0; i<this.compatibilites.length-1; i++) {
			compatibilites += this.compatibilites[i] + ", ";
		}
		compatibilites += this.compatibilites[this.compatibilites.length-1] + "}";
		
		return (this.nom + " : " + this.puissance + ", " + this.precision + ", " + this.repetitionsRestantes + "/" + this.nombreRepetitions + ", " + compatibilites);
	}
	
	
}
