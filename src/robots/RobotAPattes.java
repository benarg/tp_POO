/**
 * Sous-classe de la classe abstraite Robot
 * @author pougetat
 * 2016
 */

package robots;

import outilsBase.*;

public class RobotAPattes extends Robot {

    /** 
     * Construit un RobotAPattes avec les valeurs par defauts 
     * sur la Case position de la Carte carte
     */
    public RobotAPattes(Carte carte, Case position) {
	super(carte, position, Integer.MAX_VALUE, 30, 0, 10, 30, 0., Integer.MAX_VALUE, 100./8.);
    }


    /**
     * Construit un RobotAPattes avec une vitesse imposee
     * sur la Case position de la Carte carte
     */
    public RobotAPattes(Carte carte, Case position, int vitesse) {
        this(carte, position);
    }
	
    @Override
    public void setPosition(Case pos) {
	if (pos.getNature() == NatureTerrain.EAU)
	    throw new IllegalArgumentException("Le robot ne peut pas etre sur "
					       + "cette case");
	else
	    super.setPosition(pos);
    }

    @Override
    public String getType() {
        return "PATTES";
    }

    @Override
    public String toString() {
        return super.toString() + "Pattes";
    }

}
