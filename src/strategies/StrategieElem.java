package strategies;

import io.*;
import outilsBase.*;
import robots.*;
import evenements.*;

public class StrategieElem extends Strategie{
    public StrategieElem(DonneesSimulation d, Simulateur s) {
	super(d,s);
    }

    public void step() {

	/*
	  On détermine les robots à réservoir vide afin de leur affecter la tache de
	  remplir leurs réservoirs
	*/
	for (int i = 0; i < this.robots.length; i++) {
		
	    Robot robot = this.robots[i];
		
	    if (robot.getQuantiteEau() == 0) {
		
		this.etatsRobots[i] = false;
		Case eau = robot.getCaseACote(eauPPRobot(robot));
		robot.creerEvtsPCCACote(this.simu, eau, this.simu.getDateCour());
		int dureePCC = robot.getDureePCC(eau);
		simu.ajouteEvenement(new EvtRemplirReservoir(dureePCC + 1, robot));
		simu.ajouteEvenement(new EvtRobotLibre(dureePCC + (int) robot.getTempsRemplissage() + 1, i, this));
	    }
	}

	/*
	  On affecte des incendies non affectés à des robots libres et on crée les evts
	  permettant d'éteindre ces incendies
	*/
	while (incendiesLibres(this.etatsIncendies)
	       && (robotsLibres(this.etatsRobots))) {
	    
	    int indiceIncendie = getIncendieLibre();
	    Incendie incendie = this.incendies[indiceIncendie];
	    this.etatsIncendies[indiceIncendie] = false;
	    
	    int indiceRobot = getRobotCandidat(incendie);
	    Robot robot = this.robots[indiceRobot];
	    this.etatsRobots[indiceRobot] = false;

	    int dureePCC = robot.getDureePCC(incendie.getPosition());
	    robot.creerEvtsPCC(this.simu, incendie.getPosition(), this.simu.getDateCour());
	    
	    simu.ajouteEvenement(new EvtInterventionRobot(dureePCC + 1, robot, incendie));
	    simu.ajouteEvenement(new EvtRobotLibre(dureePCC + (int) robot.getExtinction() + 1, indiceRobot, this));

	}

    }

    // Cette méthode renvoit un incendie non affecté
    private int getIncendieLibre() {
	int indiceIncendie = 0;
	while (this.etatsIncendies[indiceIncendie] == false) {
	    indiceIncendie++;
	}
        return indiceIncendie;
    }

    /*
      Cette méthode renvoit un robot candidat pour étendre l'incendie
      1 => Ce robot n'est pas déjà sous ordre
      2 => Ce robot peut physiquement se déplacer jusqu'à l'incendie
    */
    private int getRobotCandidat(Incendie incendie) {
        for (int i = 0; i < this.robots.length; i++) {
	    if (this.etatsRobots[i]) {
		// if this.robots[i] peut aller à l'incendie (tester dijkstra
		// pour examiner le cas du chemin irréalisable
		// pour l'instant, on suppose cette condition satisfaite d
		return i;
	    }   
	}
	return -1;
    }

    private boolean incendiesLibres(boolean etatsIncendies[]) {
	for (int i = 0; i < etatsIncendies.length; i++) {
	    if (etatsIncendies[i]) {
		return true;
	    }
	}
	return false;
    }

    private boolean robotsLibres(boolean etatsRobots[]) {
	for (int i = 0; i < etatsRobots.length; i++) {
	    if (etatsRobots[i]) {
		return true;
	    }
	}
	return false;
    }
    
}
