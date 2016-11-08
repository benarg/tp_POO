package strategies;

import io.*;
import outilsBase.*;
import robots.*;

public class StrategieTest extends Strategie{
	public StrategieTest(DonneesSimulation d, Simulateur s) {
		super(d,s);
	}

	public void step() {
		for (int i=0; i<incendies.length; i++) {
			if (!etatsIncendies[i]) {
				Incendie feu = incendies[i];
				int iRobot = this.robotPPIncendie(feu);
				if (iRobot > -1) {
					robots[iRobot].creerEvtsPCC(this.simu, feu.getPosition(), this.simu.getDateCour());
					this.etatsRobots[iRobot] = true;
					this.etatsIncendies[i] = true;
				}
			}
		}
	}
}