package strategies;

import io.*;
import outilsBase.*;
import robots.*;
import java.util.ArrayList;

public abstract class Strategie {
	protected Robot robots[];
	protected boolean etatsRobots[];
	protected Incendie incendies[];
	protected boolean etatsIncendies[];
	protected Case casesEau[];
	protected Carte carte;
	protected Simulateur simu;

	public Strategie(DonneesSimulation d, Simulateur s) {
		this.simu = s;
		this.carte = d.getCarte();

		this.incendies = new Incendie[d.getNbIncendies()];
		this.etatsIncendies = new boolean[d.getNbIncendies()];
		for (int i=0; i < d.getNbIncendies(); i++) {
			this.incendies[i] = d.getIncendie(i);
			this.etatsIncendies[i] = false;
		}

		this.robots = new Robot[d.getNbRobots()];
		this.etatsRobots = new boolean[d.getNbRobots()];
		for (int i=0; i < d.getNbRobots(); i++) {
			this.robots[i] = d.getRobot(i);
			this.etatsRobots[i] = false;
		}

		ArrayList<Case> cases = new ArrayList<Case>();
		for (int i=0; i<this.carte.getNbLignes(); i++) {
			for (int j=0; j<this.carte.getNbColonnes(); j++) {
				Case c = this.carte.getCase(i,j);
				if (c.getNature() == NatureTerrain.EAU)
					cases.add(c);
			}
		}
		this.casesEau = cases.toArray(new Case[0]);
	}

	public abstract void step();

	public void robotFini(int indiceRobot) {
		etatsRobots[indiceRobot] = false;
	}

	public int robotPPIncendie(Incendie i) {
		Case dest = i.getPosition();
		int bestRobot = -1;
		int dureeMin = Integer.MAX_VALUE;
		for (int j=0; j<robots.length; j++) {
			if(!etatsRobots[j]) {
				Robot r = robots[j];
				int duree = r.getDureePCCACote(dest);
				if(duree < dureeMin) {
					bestRobot = j;
					dureeMin = duree;
				}
			}
		}
		return bestRobot;
	}

	public Case eauPPRobot(Robot r) {
		Case bestCase = null;
		int dureeMin = Integer.MAX_VALUE;
		for (Case c:casesEau) {
			int duree = r.getDureePCCACote(c);
			if(duree < dureeMin) {
				bestCase = c;
				dureeMin = duree;
			}
		}
		return bestCase;
	}

}