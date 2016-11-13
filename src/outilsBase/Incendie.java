/**
 * Incendie:
 * Contient une case et une quantiteEau qui correspond à la
 * quantité d'eau nécessaire pour éteindre l'incendie
 * @author 
 */

package outilsBase;

public class Incendie {
    
    Case position;

    /**
      * Accesseur (get)
      * @return la position de l'incendie
      */
    public Case getPosition() {
        return this.position;
    }

    int quantiteEau;

    /**
      * Accesseur (get)
      * @return la quantite d'eau necessaire pour eteindre l'incendie
      */
    public int getIntensite() {
        return this.quantiteEau;
    }
    
    /**
      * Mutateur (set)
      * Impose l'intensite du feu a la valeur entree en parametre
      */
    public void setIntensite(int quantite) {
          this.quantiteEau = quantite;
    }

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
