package strategies;

import io.*;
import outilsBase.*;
import robots.*;
import evenements.*;

public class StrategieTest extends Strategie{
	public StrategieTest(DonneesSimulation d, Simulateur s) {
		super(d,s);
		this.simu.ajouteEvenement(new EvtRobotLibre(125, 0, this));
		this.simu.ajouteEvenement(new EvtRobotLibre(120, 1, this));
	}

	public void step() {
		for (int i=0; i<incendies.length; i++) {
			if (!etatsIncendies[i]) {
				Incendie feu = incendies[i];
				int iRobot = this.robotPPIncendie(feu);
				if (iRobot > -1) {
					robots[iRobot].creerEvtsPCCACote(this.simu, feu.getPosition(), this.simu.getDateCour());
					this.etatsRobots[iRobot] = true;
					this.etatsIncendies[i] = true;
				}
			}
		}
	}
}