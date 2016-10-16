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

    /**
      * Mutateur (set)
      * Impose la nature du terrain de la case
      */
    public void setNature(NatureTerrain nat) {
    	this.nature = nat;
    }


    /**
      * Construit une case de coordonnes (line, column) 
      * de nature de terrain natureT
      */
    public Case(int line, int column, NatureTerrain natureT) {
    	this.ligne = line;
    	this.colonne = column;
    	this.nature = natureT;
    }


    /** 
      * Donne une representation en String de la case
      */
    @Override
    public String toString() {
    	return this.nature 
    			+ " aux coordonnes (" + this.getLigne() 
    			+ "," + this.getColonne() + ")";
    }

}
