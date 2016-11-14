/**
 * Sous-classe de la classe abstraite Robot
 * @author pougetat
 * 2016
 */

package robots;

import outilsBase.*;

public class RobotARoues extends Robot {

    /** 
     * Construit un RobotARoues avec les valeurs par defauts 
     * sur la Case position de la Carte carte
     */
    public RobotARoues(Carte carte, Case position) {
	super(carte, position, 5000, 0, 0, 0, 80, 10., 5000, 100./5.);
    }

    /**
     * Construit un RobotAChenilles avec une vitesse imposee
     * sur la Case position de la Carte carte
     */
    public RobotARoues(Carte carte, Case position, int vitesse) {
	this(carte, position);
	if (vitesse > 80)
	    vitesse = 80;
	this.vitesseLibre = vitesse;
    }
    
    @Override
    public String getType() {
	return "ROUES";
    }
    
    @Override
    public String toString() {
	return super.toString() + "Roues";
    }
    
}
