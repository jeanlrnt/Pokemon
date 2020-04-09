public class Pokemon {
    private String nom;
    private String type;
    private int niveau;
    private boolean diurne;
    private String nomDonne;
    private Joueur monJoueur;
    private int appetit;
    private int loyaute;

    public Pokemon(String nom, String type, int niveau, boolean diurne, String nomDonne, Joueur monJoueur) {
        this.nom = nom;
        this.type = type;
        this.niveau = niveau;
        this.diurne = diurne;
        this.nomDonne = nomDonne;
        this.monJoueur = monJoueur;
        this.appetit = 50;
        this.loyaute = 0;
    }

    public Pokemon(String nom, String type, int age, boolean diurne) {
        this(nom, type, age, diurne, null, null);
    }

    public void manger(Nourriture nourriture) {
        if (null != nourriture && nourriture.isCompatible(this)) {
            nourriture.estMangee(this);
            System.out.println("Ce pokemon a bien mange ! Miam miam ! ");
        }
    }

    public void miseANiveau() {
        this.niveau += 1;
    }

    public String sePresente() {
        if (null != this.monJoueur) {
            if (null != this.nomDonne) {
                return (this.nomDonne + "est un pokemon de genre " + this.nom + ", du type" + this.type + ", qui a le niveau  " + this.niveau);
            } else {
                return ("Voici un pokemon du genre " + this.nom + ", du type " + this.type + ", qui a le niveau " + this.niveau + ". Ce pokemon appartient a " + this.monJoueur.getNom() + this.monJoueur.getPrenom());
            }
        }
        return ("Voici un pokemon du genre " + this.nom + ", du type " + this.type + ", qui a le niveau " + this.niveau + ". Ce pokemon n'a pas encore de maitre.");
    }

    public void direBonjour(String periode) {
        if (periode.equals("jour")) {
            if (this.diurne) {
                System.out.println(this.nom + " dit bonjour !");
            } else {
                System.out.println(this.nom + " dit zzzzzzzzzzzzz !");
            }
        } else {
            if (this.diurne) {
                System.out.println(this.nom + " dit zzzzzzzzzzzzz !");
            } else {
                System.out.println(this.nom + " dit bonsoir !");
            }
        }

    }

    public void monterAppetit(int difference) {
        this.appetit += difference;
        if (this.appetit > 100) {
            this.appetit = 100;
        }
    }

    public void baisserAppetit(int difference) {
        this.appetit -= difference;
        if (this.appetit < 0) {
            this.appetit = 0;
        }
    }

    public void monterLoyaute(int difference) {
        this.loyaute += difference;
        if (this.loyaute > 100) {
            this.loyaute = 100;
        }
    }

    public void baisserLoyaute(int difference) {
        this.loyaute -= difference;
        if (this.loyaute < 0) {
            this.loyaute = 0;
        }
    }

    public String getNomDonne() {
        return this.nomDonne;
    }

    public String getNom() {
        return this.nom;
    }

    public String getType() {
        return this.type;
    }

    public Joueur getMonJoueur() {
        return this.monJoueur;
    }

    public void setNomDonne(String nomDonne) {
        this.nomDonne = nomDonne;
    }

    public boolean isDiurne() {
        return this.diurne;
    }

    public void setMonJoueur(Joueur monJoueur) {
        this.monJoueur = monJoueur;
    }

    public int getAppetit() {
        return this.appetit;
    }

    public int getLoyaute() {
        return this.loyaute;
    }

    public String toString() {
        return ("[ Nom : " + this.nom + "; Type : " + this.type + "; Niveau : "
                + this.niveau + "; Diurne : " + this.diurne + "; nomDonne : "
                + this.nomDonne + "; monJoueur : " + this.monJoueur + "; Appetit :"
                + this.appetit + "; Loyaute :" + this.loyaute + "]");
    }

}
