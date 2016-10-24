/**
  * Evenement qui fait intervenir un robot sur un incendie a une date donnee.
  * Le robot deverse alors la quantite d'eau necessaire pour eteindre ou attenuer au maximum l'incendie.
  */

package evenements;

import robots.*;
import outilsBase.*;

public class EvtInterventionRobot extends Evenement {
	private Robot robot;
	private Incendie incendie;

	public EvtInterventionRobot(int t, Robot rob, Incendie incendie) {
		super(t);
		this.robot = rob;
		this.incendie = incendie;
	}

	/**
	  * Methode qui execute l'action associee a l'evenement si le robot se trouve bien sur la case correspondant a l'incendie.
	  * Le robot deverse alors soit la quantite d'eau necessaire pour eteindre l'incendie si celle-ci est suffisante,
          * ou toute son eau dans le cas contraire.
	  */
	public void execute() {
		if (this.robot.getPosition() == this.incendie.getPosition()) {
			if (incendie.getIntensite() > robot.getQuantiteEau()) {
				incendie.setIntensite(incendie.getIntensite() - robot.getQuantiteEau());
				robot.deverserEau(robot.getQuantiteEau());
			}
			else {
				robot.deverserEau(incendie.getIntensite());
				incendie.setIntensite(0);                      // Faut'il alors supprimer l'incendie de donnees simulation ?
			}
		} 
		else
			System.out.println("Pas d'intervention possible.");
	}
}

				
				
				
				

