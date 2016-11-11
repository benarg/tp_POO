package strategies;

import io.*;
import evenements.*;
import outilsBase.*;
import robots.*;

public class StrategieEvolue extends Strategie{
	public StrategieEvolue(DonneesSimulation d, Simulateur s) {
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
					robots[iRobot].setNumIncendie(i);
				}
			}
		}
		for (int j=0 ; j<etatsRobots.length; j++) {
			if (etatsRobots[j]) {
				Robot r = robots[j];

				if ((r.getQuantiteEau() == 0)) {

					if (r.getEau() == null) {
						r.setNumIncendie(-1);
						Case dest = r.getCaseACote(this.eauPPRobot(r));
						r.setEau(dest);
						r.creerEvtsPCCACote(this.simu, this.eauPPRobot(r), simu.getDateCour());
					}
					else {
						if (r.getPosition() == r.getEau()) {
							simu.ajouteEvenement(new EvtRemplirReservoir(simu.getDateCour() + 1, r));
							simu.ajouteEvenement(new EvtRobotLibre(simu.getDateCour() + 1, j, this));
							r.setEau(null);
						}
					}
				}
							
				else if (r.getNumIncendie() != -1) {
					Incendie incendie = incendies[r.getNumIncendie()];
					if (incendie.getPosition() == r.getPosition()) {

						if (incendie.getIntensite() > 0) 
							simu.ajouteEvenement(new EvtInterventionRobot(simu.getDateCour() + 1, r, incendie));

							
						if (incendie.getIntensite() == 0) {
							etatsRobots[j] = false;
							etatsIncendies[r.getNumIncendie()] = true;
			 				r.setNumIncendie(-1);
						}
					}
				}
			}
		}
		
	}

}
