/**
 * Sous-classe de la classe abstraite Robot
 * @author argensob
 * 2016
 */

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
		super(carte, position, 10000, 100., 100., 100., 100., 30*60, 0, 10000./30.);
		if (vitesse > 150)
			vitesse = 150;
	}

	@Override
	public void remplirReservoir() {
		if (this.position.getNature() == NatureTerrain.EAU) {
			this.quantiteEau = this.reservoir;
			System.out.println("Remplissage en cours..."); }
		else
			System.out.println("Remplissage impossible !");
	}
}

