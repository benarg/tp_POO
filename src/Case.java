/**
  * Case, definie par deux coordonnees entieres (ligne et colonne) et la nature du terrain qu'elle represente
  * @author lefoulj
  * 2016
  */

public class Case {
    int ligne, colonne;
    NatureTerrain nature;

    /**
      * Accesseur (get)
      * @return le numero de la ligne
      */
    public int getLigne() {
    	return this.ligne;
    }

    /**
      * Accesseur (get)
      * @return le numero de la colonne
      */
    public int getColonne() {
    	return this.colonne;
    }

    /**
      * Accesseur (get)
      * @return la nature du terrain de la case
      */
    public NatureTerrain getNature() {
    	return this.nature;
    }

}
