/**
  * Sous-classe de la classe abstraite Robot
  * @author pougetat
  * 2016
  */


public class RobotARoues extends Robot {

    /** 
     * Construit un RobotARoues avec les valeurs par defauts 
     * sur la Case position de la Carte carte
     */
    public RobotARoues(Carte carte, Case positon) {
	super(carte, position, 5000, 0, 0, 0, 80, 10., 0, 100./5.);
    }

    /**
     * Construit un RobotAChenilles avec une vitesse imposee
     * sur la Case position de la Carte carte
     */
    public RobotAChenilles(Carte carte, Case position, int vitesse) {
	super(carte, position, 5000, 0, 0, 0, 60, 10., 0, 100./5.);
	if (vitesse > 80) {
	    vitesse = 80;
	    this.vitesseLibre;
	}
    }

    @Override
    public void setPosition(Case pos) {
        super.setPosition(pos);
    }	    

}
