public class Pokemon {
	private String nom;
	private String type;
	private int niveau;
	private boolean diurne;
	
	public Pokemon(String nom, String type, int niveau, boolean diurne) {
		this.nom = nom;
		this.type = type;
		this.niveau = niveau;
		this.diurne = diurne;
	}
	
	public String toString() {
		return "Pokemon[" + this.nom + ", " + this.type + ", " + this.niveau + ", " + this.diurne + "]";
	}
	
	public void direBonjour(String periode) {
		if ((periode.equals("jour") && this.diurne) || (periode.equals("nuit") && !this.diurne)) {
			System.out.println(this.nom + " dit Bonjour !");
		} else { 
			if ((periode.equals("jour") && !this.diurne) || (periode.equals("nuit") && this.diurne))
				System.out.println(this.nom + " fait zzzzzz !");
		}
	}
}
