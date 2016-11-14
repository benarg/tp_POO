package strategies;

import io.*;
import evenements.*;
import outilsBase.*;
import robots.*;
import java.util.*;

public class StrategieEvolue extends Strategie{
	public StrategieEvolue(DonneesSimulation d, Simulateur s) {
		super(d,s);
	}

	public void step() {
	
		// le chef robot propose à tous les robots un incendie a eteindre
		for (int i=0; i<incendies.length; i++) {
			if (!etatsIncendies[i]) {
				Incendie feu = incendies[i];

				// il selectionne pour chaques incendies le robot libre le plus proche

				int iRobot = this.robotPPIncendie(feu);
				if (iRobot > -1) {
					robots[iRobot].creerEvtsPCC(this.simu, feu.getPosition(), this.simu.getDateCour());
					this.etatsRobots[iRobot] = true;
					robots[iRobot].setNumIncendie(i);
				}
			}
		}
		for (int j=0 ; j<etatsRobots.length; j++) {

			// Pour chaque robot non libre...

			if (etatsRobots[j]) {
				Robot r = robots[j];

				// On regarde si leurs reservoirs sont vides...

				if ((r.getQuantiteEau() == 0)) {

					// si c'est le cas et que le robot n'a pas encore programme son deplacement vers un point d'eau on le fait ici

					if (r.getEau() == null) {
						etatsIncendies[r.getNumIncendie()] = false;
						r.setNumIncendie(-1);
						Case dest = r.getCaseACote(this.eauPPRobot(r));
						r.setEau(dest);
						r.creerEvtsPCCACote(this.simu, this.eauPPRobot(r), simu.getDateCour());
					}

					// sinon on regarde si le robot est arrive vers le point d'eau vers lequel on a programme son deplacement

					else {
						if (r.getPosition() == r.getEau()) {

					// si c'est le cas on remplit son reservoir et le robot se declarera libre a la fin de son remplissage 		

							simu.ajouteEvenement(new EvtRemplirReservoir(simu.getDateCour() + 1, r));
							simu.ajouteEvenement(new EvtRobotLibre(simu.getDateCour() + (int) r.getTempsRemplissage() + 1, j, this));
							r.setEau(null);
						}
					}
				}
				
				// si le reservoir du robot n'est pas vide, on regarde si le robot est associe a un incendie sur lequel il est cense intervenir...
							
				else if (r.getNumIncendie() != -1) {
					Incendie incendie = incendies[r.getNumIncendie()];

					// si le robot a atteint cet incendie...

					if (incendie.getPosition() == r.getPosition()) {

						// il regarde si personne n'a eteint cet incendie entre temps ou si aucun robot n'est en train d'intervenir dessus

						if (incendie.getIntensite() > 0 && etatsIncendies[r.getNumIncendie()] == false) {

							// si c'est le cas alors il intervient sur l'incendie					
	
							etatsIncendies[r.getNumIncendie()] = true;						
							simu.ajouteEvenement(new EvtInterventionRobot(simu.getDateCour() + 1 + (int) Math.ceil(Math.min(r.getQuantiteEau(), incendie.getIntensite())/ (r.getExtinction())), r, incendie));
						}
						
						// si son intervention a permis d' eteindre l'incendie alors le robot devient 'libre'
	
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
