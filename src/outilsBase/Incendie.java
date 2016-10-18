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
    
    public Incendie(Case positon, int quantiteEau) {
	this.position = position;
	this.quantiteEau = quantiteEau;
    }

}
