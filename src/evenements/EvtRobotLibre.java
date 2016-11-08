/**
  * Evenement avertissant le chef qu'un robot est libre a une date donnee
  */

package evenements;

import strategies.*;

public class EvtRobotLibre extends Evenement {
	private int indiceRobot;
	private Strategie strategie;

	public EvtRobotLibre(int d, int rob, Strategie s) {
		super(d);
		this.indiceRobot = rob;
		this.strategie = s;
		}
	/**
	  * Execute l'evenement
	  */
	public void execute() {
		this.strategie.robotFini(this.indiceRobot);
	}
}
		