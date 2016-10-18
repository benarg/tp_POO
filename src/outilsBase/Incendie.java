/**
 * Incendie:
 * Contient une case et une quantiteEau qui correspond à la
 * quantité d'eau nécessaire pour éteindre l'incendie
 * @author pougetat
 */

package outilsBase;

public class Incendie {
    
    Case position;
    int quantiteEau;
    
    /**
      * Construit un Incendie sur la case position 
      * d'intensite quantiteEau
      */
    public Incendie(Case pos, int intensite) {
		this.position = pos;
		this.quantiteEau = intensite;
    }

    @Override
    public String toString() {
    	return "Incendie: \t"
    	+ "position = (" + this.position.getLigne() 
    	+ "," + this.position.getColonne() + ");\t"
    	+ "intensite = " + this.quantiteEau;
    }

}
