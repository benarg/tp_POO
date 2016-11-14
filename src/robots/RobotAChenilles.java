/**
  * Sous-classe de la classe abstraite Robot
  * @author 
  * 2016
  */

package robots;

import outilsBase.*;

public class RobotAChenilles extends Robot {

	/** 
	  * Construit un RobotAChenilles avec les valeurs par defauts 
	  * sur la Case position de la Carte carte
	  */
	public RobotAChenilles(Carte carte, Case position) {
		super(carte, position, 2000, 60/2, 0, 0, 60, 5*60, 2000, 100./8.);
	}

	/**
	  * Construit un RobotAChenilles avec une vitesse imposee
	  * sur la Case position de la Carte carte
	  */
	public RobotAChenilles(Carte carte, Case position, int vitesse) {
		this(carte, position);
		if (vitesse > 80)
			vitesse = 80;
		this.vitesseForet = vitesse/2.;
		this.vitesseLibre = vitesse;
	}

	@Override
	public void setPosition(Case pos) {
		if ((pos.getNature() == NatureTerrain.EAU) 
			|| (pos.getNature() == NatureTerrain.ROCHE))
			throw new IllegalArgumentException("Le robot ne peut pas etre sur "
												+ "cette case");
		else
			super.setPosition(pos);
	}

	@Override
	public String getType() {
		return "CHENILLES";
	}

	@Override
	public String toString() {
		return super.toString() + "Chenilles";
	}

}
