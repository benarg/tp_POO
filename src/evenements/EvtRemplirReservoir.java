/**
  * Evenement qui remplit le reservoir d'eau d'un robot
  */

package evenements;

import robots.*;
import outilsBase.*;

public class EvtRemplirReservoir extends Evenement {
	private Robot robot;

	public EvtRemplirReservoir(int d, Robot rob) {
		super(d);
		this.robot = rob;
	}

	public void execute() {
		this.robot.remplirReservoir();
	}
}
		
		


	

