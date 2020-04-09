public class Pokemon {
    private String nom;
    private String type;
    private int niveau;
    private boolean diurne;
    private String nomDonne;
    private Joueur monJoueur;
    private int appetit;
    private int loyaute;

    // ajoutes en TP 6

    private int attaque;
    private int defense;
    private int attaqueSpeciale;
    private int defenseSpeciale;
    private int hp;

    private Attaque[] attaques = new Attaque[4];

    public Pokemon(String nom, String type, int niveau,
                   boolean diurne, String nomDonne, Joueur monJoueur,
                   int attaque, int defense, int attaqueSpeciale,
                   int defenseSpeciale, Attaque[] attaques) {
        this.nom = nom;
        this.type = type;
        this.niveau = niveau;
        this.diurne = diurne;
        this.nomDonne = nomDonne;
        this.monJoueur = monJoueur;
        this.appetit = 50;
        this.loyaute = 0;

        // ajoutees en TP 6
        this.attaque = attaque;
        this.defense = defense;
        this.attaqueSpeciale = attaqueSpeciale;
        this.defenseSpeciale = defenseSpeciale;
        this.hp = 30;

        for (int i = 0; i < attaques.length; i++) {
            this.ajouterAttaque(attaques[i]);
            ;
        }
    }

    public Pokemon(String nom, String type, int age,
                   boolean diurne, int attaque, int defense,
                   int attaqueSpeciale, int defenseSpeciale,
                   Attaque[] attaques) {
        this(nom, type, age, diurne, null, null, attaque, defense, attaqueSpeciale, defenseSpeciale, attaques);
    }


    private int trouverAttaque(Attaque attaque) {

        for (int i = 0; i < this.attaques.length; i++) {
            if (this.attaques[i] == attaque) {
                return i;
            }
        }
        return -1;
    }

    public void ajouterAttaque(Attaque attaque) {
        if (attaque.isCompatible(this)) {
            int positionLibre = this.trouverAttaque(null);
            if (positionLibre != -1) {
                this.attaques[positionLibre] = attaque;
            }

        }
    }

    public void ajouterAttaque(Attaque attaque, int i) {
        if (i >= 0 && i < this.attaques.length) {
            if (attaque.isCompatible(this)) {
                this.attaques[i] = attaque;
            }
        }
    }

    public void rechargerAttaques() {
        for (int i = 0; i < this.attaques.length; i++) {
            if (null != this.attaques[i]) {
                this.attaques[i].resetNombreRepetitions();
            }
        }
    }

    public void blessure(int dommage) {
        this.hp -= dommage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    public boolean sEstEvanoui() {
        return (this.hp == 0);
    }

    public void utiliserAttaque(int index, Pokemon victime) {
        if (!this.sEstEvanoui() && index >= 0 && index < this.attaques.length && this.attaques[index] != null) {
            this.attaques[index].utiliserAttaque(this, victime);

        }
    }

    public void afficherEtatAttaques() {
        String attaques = "";
        for (int i = 0; i < this.attaques.length; i++) {
            if (null != this.attaques[i]) {
                attaques += i + " : " + this.attaques[i].getNom() + ", " + this.attaques[i].getRepetitionsRestantes() + "/" + this.attaques[i].getNombreRepetitions() + "\n";
            }
        }
        System.out.println(attaques);
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

    public int getNiveau() {
        return this.niveau;
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

    public int getAttaque() {
        return this.attaque;
    }

    public int getDefense() {
        return this.defense;
    }

    public int getAttaqueSpeciale() {
        return this.attaqueSpeciale;
    }

    public int getDefenseSpeciale() {
        return this.defenseSpeciale;
    }

    public int getHP() {
        return this.hp;
    }

    public Attaque[] getAttaques() {
        return this.attaques;
    }

    public String toString() {
        String attaques = "{";
        for (int i = 0; i < this.attaques.length - 1; i++) {
            if (this.attaques[i] != null) {
                attaques += this.attaques[i] + ", ";
            }

        }

        if (this.attaques[this.attaques.length - 1] != null) {
            attaques += this.attaques[this.attaques.length - 1];
        }

        attaques += "}";

        return ("[ Nom : " + this.nom + "; Type : " + this.type + "; Niveau : "
                + this.niveau + "; Diurne : " + this.diurne + "; nomDonne : "
                + this.nomDonne + "; monJoueur : " + this.monJoueur + "; Appetit :"
                + this.appetit + "; Loyaute :" + this.loyaute + "; Attaque" + this.attaque
                + "; Defense : " + this.defense + "; AttaqueSpeciale : "
                + "; DefenseSpeciale : " + this.defenseSpeciale + "; HP : " + this.hp
                + "; Attaques : " + attaques + "]");
    }

}
