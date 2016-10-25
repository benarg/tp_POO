/**
  * Evenement imposer la position d'un robot a une date donnee
  */

package evenements;

import robots.*;
import outilsBase.*;

public class EvtRobotSetPos extends Evenement {
	private Robot robot;
	private Case dest;

	public EvtRobotSetPos(int d, Robot rob, Case c) {
		super(d);
		this.robot = rob;
		this.dest = c;
		}

	/**
	  * Execute l'evenement
	  * Arrete la simulation si une exception est rattrapee
	  */
	public void execute() {
		try {
			this.robot.setPosition(this.dest);
		}
		catch (IllegalArgumentException e) {
			System.out.println("Erreur : deplacement impossible.");
			System.exit(0);
		}	
	}
}
		
		


	

