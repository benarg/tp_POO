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

	public void execute() {
		this.robot.deplacer(this.direction);
	}
}
		
		


	

