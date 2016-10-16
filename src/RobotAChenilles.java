/**
  * Sous-classe de la classe abstraite Robot
  * @author lefoulj
  * 2016
  */


public class RobotAChenilles extends Robot {

	/** 
	  * Construit un RobotAChenilles avec les valeurs par defauts 
	  * sur la Case position de la Carte carte
	  */
	public RobotAChenilles(Carte carte, Case position) {
		super(carte, position, 2000, 60/2, 0, 0, 60, 10., 0, 100./8.);
	}

	/**
	  * Construit un RobotAChenilles avec une vitesse imposee
	  * sur la Case position de la Carte carte
	  */
	public RobotAChenilles(Carte carte, Case position, int vitesse) {
		super(carte, position, 2000, 60/2, 0, 0, 60, 10., 0, 100./8.);
		if (vitesse > 80)
			vitesse = 80;
		this.vitesseForet = vitesse/2;
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


}