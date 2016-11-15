/**
  * Evenement qui fait intervenir un robot sur un incendie a une date donnee.
  * Le robot deverse alors la quantite d'eau maximum qu'il peut deverser en une seconde
  */

package evenements;

import robots.*;
import outilsBase.*;


public class EvtVerserEau extends Evenement {
	private Robot robot;
	private Incendie incendie;

	public EvtVerserEau(int t, Robot rob, Incendie incendie) {
		super(t);
		this.robot = rob;
		this.incendie = incendie;
	}

	/**
	  * Methode qui execute l'action associee a l'evenement si le robot se trouve bien sur la case correspondant a l'incendie.
	  * Le robot deverse alors soit la quantite d'eau necessaire pour eteindre l'incendie si celle-ci est suffisante,
          * ou juste l'eau qu'il peut au maximum deverser en une seconde
	  */
	public void execute() {
		if (this.robot.getPosition() == this.incendie.getPosition()) {
			if (incendie.getIntensite() > (int)robot.getExtinction()) {
				if ((int)robot.getExtinction() < robot.getQuantiteEau()) {
					incendie.setIntensite(incendie.getIntensite() - (int) robot.getExtinction());
					robot.deverserEau((int)robot.getExtinction());
					System.out.println("Il faut encore," + this.incendie.getIntensite() + " litres d'eau pour eteindre l'incendie.");
				}
				else {
					incendie.setIntensite(incendie.getIntensite() - robot.getQuantiteEau());
					robot.deverserEau(robot.getQuantiteEau());
					System.out.println("Il faut encore," + this.incendie.getIntensite() + " litres d'eau pour eteindre l'incendie.");
				}
			}
			else {
				robot.deverserEau(incendie.getIntensite());
				incendie.setIntensite(0);                     
			}
		} 
		else
			System.out.println("Pas d'intervention possible.");
	}
}

