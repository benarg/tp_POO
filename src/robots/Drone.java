/**
 * Sous-classe de la classe abstraite Robot
 * @author argensob
 * 2016
 */

package robots;

import outilsBase.*;

public class Drone extends Robot {

	/** 
         * Construit un Drone avec les valeurs par defauts 
         * sur la Case position de la Carte carte
         */
	public Drone(Carte carte, Case position) {
		super(carte, position, 10000, 100., 100., 100., 100., 30*60, 0, 10000./30.);
    }

	/**
	  * Construit un Drone avec une vitesse imposee
	  * sur la Case position de la Carte carte
	  */
	public Drone(Carte carte, Case position, int vitesse) {
		this(carte, position);
		if (vitesse > 150)
			vitesse = 150;
		this.vitesseForet = vitesse;
		this.vitesseEau = vitesse;
		this.vitesseRocher = vitesse;
		this.vitesseLibre = vitesse;
	}

	@Override
	public void remplirReservoir() {
		if (this.position.getNature() == NatureTerrain.EAU) {
			this.quantiteEau = this.reservoir;
			System.out.println("Remplissage en cours..."); }
		else
			System.out.println("Remplissage impossible !");
	}

	@Override
	public String getType() {
		return "DRONE";
	}

	@Override
	public String toString() {
		return super.toString() + "Drone";
	}
}

