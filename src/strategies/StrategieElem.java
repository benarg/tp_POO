package strategies;

import io.*;
import outilsBase.*;
import robots.*;
import evenements.*;

public class StrategieElem extends Strategie{

    int currentDate = 0;
    
    public StrategieElem(DonneesSimulation d, Simulateur s) {
	super(d,s);
    }

    public void step() {


	for (int i = 0; i < this.robots.length; i++) {

	    Robot robot = this.robots[i];

	    System.out.println(robot);
	    if (robot.getNumIncendie() != -1) {
		System.out.println("ROBOT FIRE TARGET IS : " +
				   incendies[robot.getNumIncendie()].getPosition());
	    } else {
		System.out.println("ROBOT FIRE TARGET IS : -1");
	    }
	    if (robot.getEau() != null) {
		System.out.println("ROBOT WATER TARGET IS : " +
				   robot.getEau());
	    } else {
		System.out.println("ROBOT WATER TARET IS : NULL");
	    }
	    System.out.println("ROBOT STATUS IS " + this.etatsRobots[i]);
	    System.out.println("ROBOT WATER LEVEL IS : " +
			       robot.getQuantiteEau());
	    System.out.println("----------------------------");

	}
	

	for (int i = 0; i < this.robots.length; i++) {
	    
	    Robot robot = this.robots[i];

	    if (robot.getQuantiteEau() == 0) {

		if (robot.getEau() == null) {

		    Case dest = robot.getCaseACote(this.eauPPRobot(robot));
		    int dureePCC = robot.getDureePCC(dest);
		    robot.setEau(dest);
		    robot.creerEvtsPCCACote(this.simu, this.eauPPRobot(robot), simu.getDateCour());
		    
		    if (robot.getNumIncendie() != -1) {
			etatsIncendies[robot.getNumIncendie()] = false;
			if (incendies[robot.getNumIncendie()].getIntensite() == 0) {
			    etatsIncendies[robot.getNumIncendie()] = true;
			}
			robot.setNumIncendie(-1);
		    }
		    
		    etatsRobots[i] = true;
		    simu.ajouteEvenement(new EvtRobotLibre(simu.getDateCour() + dureePCC + 10, i, this));
		}

		else if (robot.getPosition() == robot.getEau()) {

		    if (etatsRobots[i]) {

			// robot is currently filling up

			System.out.println(robot + " IS WAITING TO FILL UP");
			
		    } else {

			System.out.println(robot + " WILL FILL UP IN " +
					   (int) robot.getTempsRemplissage());

					   
			simu.ajouteEvenement(new EvtRemplirReservoir(simu.getDateCour() + (int) robot.getTempsRemplissage() + 10, robot));
			etatsRobots[i] = true;
			simu.ajouteEvenement(new EvtRobotLibre(simu.getDateCour() + (int) robot.getTempsRemplissage() + 20, i, this));

		    }
		}
		
		else {
		    
		    // robot is heading to a water area
		    
		}
		
	    }

	    else if (robot.getNumIncendie() == -1) {

		robot.setEau(null);
		int incendieIndice = this.getIncendieLibre();

		if (incendieIndice == -1) {

		    // No fires are available

		}

		else {

		    if (etatsRobots[i]) {

			// robot is heading to the fire
			// this subcase should theoretically not exist

		    } else {

			Incendie incendie = incendies[incendieIndice];
			int dureePCC = robot.getDureePCC(incendie.getPosition());
			
			if (dureePCC < Integer.MAX_VALUE) {
			    
			    robot.creerEvtsPCC(this.simu, incendie.getPosition(),
					       this.simu.getDateCour());
			    robot.setNumIncendie(incendieIndice);
			    this.etatsIncendies[incendieIndice] = true;
			    this.etatsRobots[i] = true;
			    simu.ajouteEvenement(new EvtRobotLibre(simu.getDateCour() + dureePCC + 10, i, this));
			} else {

			    System.out.println(robot + " CANNOT GET TO "
					       + incendies[incendieIndice].getPosition());

			}

		    }
		}
		
	    } else if (robot.getNumIncendie() != -1) {

		if (robot.getPosition() == incendies[robot.getNumIncendie()].getPosition()) {
		    if (incendies[robot.getNumIncendie()].getIntensite() != 0) {

			if (!etatsRobots[i]) {

			    System.out.println(robot + " IS ABOUT TO LAUNCH WATER CMD "
					       + (int) robot.getExtinction());
			    
			    this.etatsRobots[i] = true;
			    simu.ajouteEvenement(new EvtInterventionRobot(simu.getDateCour() + (int) robot.getExtinction() + 10, robot, this.incendies[robot.getNumIncendie()]));
			    simu.ajouteEvenement(new EvtRobotLibre(simu.getDateCour() + (int) robot.getExtinction() + 10, i, this));

			} else {

			    System.out.println(robot + " IS WAITING TO WATER ");
			    
			    // robot is waiting to water

			}

		    } else {

			this.etatsIncendies[robot.getNumIncendie()] = true;
			robot.setNumIncendie(-1);

		    }
		} else {

		    // robot is on the way to the fire
		    System.out.println(robot + " IS ON THE WAY");

		}
	    }
	}
    }
    
    private boolean robotLibres() {
	for (int i = 0; i < this.robots.length; i++) {
	    if (!this.etatsRobots[i]) {
		return true;
	    }
	}
	return false;
    }
    
    private int getIncendieLibre() {
	for (int i = 0; i < this.incendies.length; i++) {
	    if (!this.etatsIncendies[i]) {
		return i;
	    }
	}
	return -1;
    }
}

	
