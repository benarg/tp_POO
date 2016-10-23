/**
  * Evenement deplacant un robot a une date donnee
  */

package evenements;

import robots.*;
import outilsBase.*;

public class EvtDeplacerRobot extends Evenement {
	private Robot robot;
	private Direction direction;

	public EvtDeplacerRobot(int d, Robot rob, Direction dir) {
		super(d);
		this.robot = rob;
		this.direction = dir;
		}
	/**
	  * Execute l'evenement
	  * Arrete la simulation si une exception est rattrapee
	  */
	public void execute() {
		try {
			this.robot.deplacer(this.direction);
		}
		catch (IllegalArgumentException e) {
			System.out.println("Erreur : deplacement impossible.");
			System.exit(0);
		}	
	}
}
		
		


	

