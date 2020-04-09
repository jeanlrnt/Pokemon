package pokemon;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    private  int numeroPokedex;
    private String nom;
    private String type;
    private int niveau;
    private boolean diurne;
    private String nomDonne;
    private Joueur monJoueur;
    private int appetit;
    private int loyaute;
    private int attaque;
    private int defense;
    private int attaqueSpeciale;
    private int defenseSpeciale;
    private int hp;
    private List<Attaque> attaques = new ArrayList<Attaque>(4);


    public Pokemon(int numeroPokedex, String nom, String type, int niveau, boolean diurne, String nomDonne, Joueur monJoueur, int attaque,
                   int defense, int attaqueSpeciale, int defenseSpeciale, Attaque[] attaques) {
        this.numeroPokedex = numeroPokedex;
        this.nom = nom;
        this.type = type;
        this.niveau = niveau;
        this.diurne = diurne;
        this.nomDonne = nomDonne;
        this.monJoueur = monJoueur;
        this.attaque = attaque;
        this.defense = defense;
        this.attaqueSpeciale = attaqueSpeciale;
        this.defenseSpeciale = defenseSpeciale;
        for (int i = 0; i < attaques.length; i++) {
            if (null != attaques[i]) {
                this.ajouterAttaque(attaques[i]);
            }
        }
        this.appetit = 50;
        this.loyaute = 0;
        this.hp = 300;
    }

    public Pokemon(int numeroPokedex, String nom, String type, int niveau, boolean diurne, int attaque, int defense, int attaqueSpeciale,
                   int defenseSpeciale, Attaque[] attaques) {
        this(numeroPokedex, nom, type, niveau, diurne, null, null, attaque, defense, attaqueSpeciale, defenseSpeciale, attaques);
    }

    public void utiliser(Utilisable item) {
        if (null != item) {
            if (null != this.monJoueur) {
                if (this.monJoueur.trouverPokemon(this) != -1) {
                    this.utiliser(item);
                    System.out.println(this.nom + " utilise " + item);
                }
            } else {
                System.out.println("Le pokemon n'a pas de maitre, il ne peut donc pas recevoir d'objet utilisable..");
            }
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

    private int trouverAttaque(Attaque attaque) {

        if (this.attaques.contains(attaque)) {
            return attaques.indexOf(attaque);
        }
        return -1;
    }

    public void ajouterAttaque(Attaque attaque) {
        if (attaque.isCompatible(this)) {
            if (attaques.size() <= 4) {
                attaques.add(attaque);
            } else {
                System.out.println("Vous n'avez plus de place pour cette attaque..");
            }
        } else {
            System.out.println("L'attaque n'est pas compatible..");
        }
    }

    public void ajouterAttaque(Attaque attaque, int i) {
        if (i >= 0 && i < 4 && attaque.isCompatible(this)) {
            attaques.add(i, attaque);
        } else {
            System.out.println("L'attaque n'est pas compatible..");
        }
    }

    public void rechargerAttaques() {
        Attaque temp = null;
        for (int i = 0; i < this.attaques.size(); i++) {
            if (null != this.attaques.get(i)) {
                temp = this.attaques.get(i);
                temp.resetNombreRepetitions();
                attaques.set(i, temp);
            }
        }
    }

    public void blessure(int dommage) {
        this.hp -= dommage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    public boolean etreEvanoui() {
        if (this.hp == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void utiliserAttaque(int index, Pokemon victime) {
        if (!victime.etreEvanoui()) {
            if (index < 0 || index >= this.attaques.size() || null == this.attaques.get(index)) {
                System.out.println("L'attaque n'existe pas vous passez votre tour..");
            } else {
                this.attaques.get(index).utiliserAttaque(this, victime);
            }
        }
    }

    public void afficherAttaques() {
        System.out.println("Attaques de " + this.getNom() + " :");
        for (int i = 0; i < attaques.size(); i++) {
            if (null != attaques.get(i)) {
                System.out.println(i + " : " + attaques.get(i).getNom() + ", " + attaques.get(i).getRepetitionsRestantes() + "/" + attaques.get(i).getNombreRepetitions());
            }
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

    public int getNiveau() {
        return niveau;
    }

    public int getAttaque() {
        return attaque;
    }

    public int getDefense() {
        return defense;
    }

    public int getAttaqueSpeciale() {
        return attaqueSpeciale;
    }

    public int getDefenseSpeciale() {
        return defenseSpeciale;
    }

    public int getHp() {
        return hp;
    }

    public List<Attaque> getAttaques() {
        return attaques;
    }

    public int getNumeroPokedex() {
        return numeroPokedex;
    }

    @Override
    public String toString() {
        String listeAttaques = "";
        for (Attaque atk: attaques) {
            if (listeAttaques == "") {
                listeAttaques += "{" + atk.getNom();
            } else {
                listeAttaques += ", " + atk.getNom();
            }
        }
        listeAttaques += "}";
        return "Pokemon [" + numeroPokedex + " : nom=" + nom + ", type=" + type + ", niveau=" + niveau + ", diurne=" + diurne + ", nomDonne="
                + nomDonne + ", monJoueur=\"" + monJoueur.getPrenom() + " " + monJoueur.getNom() + "\", appetit=" + appetit
                + ", loyaute=" + loyaute + ", attaque=" + attaque + ", defense=" + defense + ", attaqueSpeciale="
                + attaqueSpeciale + ", defenseSpeciale=" + defenseSpeciale + ", hp=" + hp + ", attaques=" + listeAttaques + "]";
    }

}
